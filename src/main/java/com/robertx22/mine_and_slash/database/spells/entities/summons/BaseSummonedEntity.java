package com.robertx22.mine_and_slash.database.spells.entities.summons;

import com.robertx22.mine_and_slash.database.spells.entities.bases.ISpellEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.saveclasses.EntitySpellData;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.saveclasses.spells.calc.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.datasaving.EntitySpellDataSaving;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TeamUtils;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class BaseSummonedEntity extends TameableEntity implements ISpellEntity {
    EntitySpellData spellData;

    public BaseSummonedEntity(EntityType<? extends TameableEntity> type, World world) {
        super(type, world);
    }
    /*
    public PreCalcSpellConfigs getConfig(LivingEntity caster) {
        return getCtx(caster).getConfigFor(this.getSpellData().getSpell().geta);
    }

    public SpellCalcData getCalc(LivingEntity caster) {
        return getConfig(caster).getCalc(Load.spells(caster), getAbilityThatDeterminesLevel());
    }

    public SpellCastContext getCtx(LivingEntity caster) {
        return new SpellCastContext(caster, 0, getAbilityThatDeterminesLevel());
    }

    public IAbility getAbilityThatDeterminesLevel() {
        if (getSpell() != null) {
            return getSpell();
        }
        return this;
    }*/


    @Nullable
    @Override
    public AgeableEntity createChild(AgeableEntity ageableEntity) {
        return null;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void writeSpawnData(PacketBuffer buf) {
        CompoundNBT nbt = new CompoundNBT();
        writeAdditional(nbt);
        buf.writeCompoundTag(nbt);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED)
            .setBaseValue((double) 0.5F);

        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH)
            .setBaseValue(20.0D);

        this.getAttributes()
            .registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE)
            .setBaseValue(0.5D); // atk only with a tiny bit of damage to proc the vanilla stuff like knockback
    }

    @Override
    public void readSpawnData(PacketBuffer buf) {
        CompoundNBT nbt = buf.readCompoundTag();
        this.readAdditional(nbt);
    }

    @Override
    public void tick() {

        if (this.spellData == null) {
            remove();
            return;
        }
        else if (this.spellData.getInit() == false){ // hacky init solution
            this.getAttribute(SharedMonsterAttributes.MAX_HEALTH)
                    .setBaseValue(this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() * (1 + this.spellData.bonusHealth)); // sets bonus health from spell stat
            this.setHealth(this.getMaxHealth());
            this.setTamedBy((PlayerEntity) this.getSpellData().getCaster(world)); // sets tamed
            Load.Unit(this).setLevel(Load.Unit(this.getSpellData().getCaster(world)).getLevel(), this); // sets level
            Load.Unit(this).setRarity(2);
            Load.Unit(this).setTier(1);
            this.spellData.setInit(true);
        }

        super.tick();

        if (this.ticksExisted > this.durationInTicks()) {
            this.remove();
        }

    }

    @Override
    public boolean canAttack(LivingEntity target) {
        return !this.isOwner(target) && !this.isOnSameTeam(target) && super.canAttack(target);
    }

    @Override
    public EntitySpellData getSpellData() {
        return spellData;
    }

    @Override
    public void setSpellData(EntitySpellData data) {
        this.spellData = data;
    }

    @Override
    public void readAdditional(CompoundNBT nbt) {
        this.spellData = EntitySpellDataSaving.Load(nbt);
        super.readAdditional(nbt);
    }

    @Override
    public void writeAdditional(CompoundNBT nbt) {
        EntitySpellDataSaving.Save(nbt, spellData);
        super.writeAdditional(nbt);
    }

    @Override
    public boolean isOnSameTeam(Entity entityIn) {
        if (this.spellData.getInit() == true) {
            if (this.isTamed()) {
                LivingEntity livingentity = this.getOwner();
                if (entityIn == livingentity) {
                    return true;
                }

                if (livingentity != null && entityIn instanceof PlayerEntity) {
                    return livingentity.isOnSameTeam(entityIn) || TeamUtils.areOnSameTeam((ServerPlayerEntity) livingentity, (ServerPlayerEntity) entityIn);
                }
            }
        }

        return super.isOnSameTeam(entityIn);
    }

    @Override
    public boolean attackEntityAsMob(Entity en) {

        if (super.attackEntityAsMob(en)) {
            if (en instanceof LivingEntity) {
                if (this.world.getDifficulty() != Difficulty.PEACEFUL) {

                    dealSummonDamageTo((LivingEntity) en);
                    if (this != null && en != null) {
                        ((LivingEntity) en).setRevengeTarget(this);
                        if (en instanceof MobEntity) {
                            ((MobEntity) en).setAttackTarget(this);
                        }
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public boolean shouldAttackEntity(LivingEntity target, LivingEntity owner) {
        if (target instanceof SpiritWolfPetEntity) {
            SpiritWolfPetEntity wolfentity = (SpiritWolfPetEntity) target;
            return !wolfentity.isTamed() || wolfentity.getOwner() != owner;
        } else if (target instanceof ZombiePetEntity){
            ZombiePetEntity zombieentity = (ZombiePetEntity) target;
            return !zombieentity.isTamed() || zombieentity.getOwner() != owner;
        } else if (target instanceof SkeletonPetEntity) {
            SkeletonPetEntity skeletonentity = (SkeletonPetEntity) target;
            return !skeletonentity.isTamed() || skeletonentity.getOwner() != owner;
        } else if (target instanceof PlayerEntity && owner instanceof PlayerEntity && !((PlayerEntity)owner).canAttackPlayer((PlayerEntity)target)) {
            return false;
        } else if (this.isOnSameTeam(target)) {
            return false;
        }  else if (target instanceof AbstractHorseEntity && ((AbstractHorseEntity)target).isTame()) {
            return false;
        } else {
            return !(target instanceof TameableEntity) || !((TameableEntity)target).isTamed();
        }
    }

    @Override
    public void onDeath(DamageSource cause) {
        //this.spellData.currentEntities--;
        super.onDeath(cause);
    }

}
