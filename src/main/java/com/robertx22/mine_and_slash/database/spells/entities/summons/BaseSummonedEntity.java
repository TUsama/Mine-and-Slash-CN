package com.robertx22.mine_and_slash.database.spells.entities.summons;

import com.robertx22.mine_and_slash.database.spells.entities.bases.ISpellEntity;
import com.robertx22.mine_and_slash.potion_effects.all.BleedPotion;
import com.robertx22.mine_and_slash.potion_effects.all.SummonTauntEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.shaman.CriticalSurgeEffect;
import com.robertx22.mine_and_slash.saveclasses.EntitySpellData;
import com.robertx22.mine_and_slash.uncommon.datasaving.EntitySpellDataSaving;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TeamUtils;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.world.*;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Collection;

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

        System.out.println("beginning attributes");
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED)
            .setBaseValue((double) 0.4F);

        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH)
            .setBaseValue(15.0D);

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

        if (this.spellData == null || this.getSpellData().getCaster(world) == null) {
            remove();
            return;
        }
        else if (!this.spellData.getInit() && this.getSpellData().getCaster(world) != null) { // hacky init solution
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

        if (this.ticksExisted > this.durationInTicks() || this.getOwner() == null) {
            this.remove();
            for(int i = 0; i < 20; ++i) {
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                double d2 = this.rand.nextGaussian() * 0.02D;
                this.world.addParticle(ParticleTypes.POOF, this.getPosXRandom(1.0D), this.getPosYRandom(), this.getPosZRandom(1.0D), d0, d1, d2);
            }
            //this.spellData.setInit(false);
        }

    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(6, new SwimGoal(this));
        this.goalSelector.addGoal(7, new FollowOwnerGoal(this, 1.0D, 6.0F, 1.0F, false));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(10, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(10, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, MobEntity.class, false));
        this.targetSelector.addGoal(3, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(4, new OwnerHurtTargetGoal(this));
    }

    @Override
    public boolean canAttack(LivingEntity target) {
        return !this.isOwner(target) && !this.isOnSameTeam(target) && !(target instanceof AgeableEntity) && !(target instanceof IronGolemEntity || target instanceof SnowGolemEntity) && super.canAttack(target);
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
        if (this.spellData.getInit()) {
            if (this.isTamed()) {
                LivingEntity livingentity = this.getOwner();
                if (entityIn == livingentity) {
                    return true;
                }

                if (livingentity != null) {

                    if (entityIn instanceof PlayerEntity) {
                        return livingentity.isOnSameTeam(entityIn) || TeamUtils.areOnSameTeam((ServerPlayerEntity) livingentity, (ServerPlayerEntity) entityIn);
                    }

                    if (entityIn instanceof TameableEntity) {
                        if (((TameableEntity) entityIn).isTamed()) {
                            if (((TameableEntity) entityIn).getOwner() != null) {
                                LivingEntity targetOwner = ((TameableEntity) entityIn).getOwner();
                                return livingentity.isOnSameTeam(targetOwner) || TeamUtils.areOnSameTeam((ServerPlayerEntity) livingentity, (ServerPlayerEntity) targetOwner);
                            }
                        }
                    }
                }
            }
        }

        return super.isOnSameTeam(entityIn);
    }

    @Override
    public boolean attackEntityAsMob(Entity en) {

        if (super.attackEntityAsMob(en)) {
            if (en instanceof LivingEntity) {
                LivingEntity lEn = (LivingEntity) en;
                if (this.world.getDifficulty() != Difficulty.PEACEFUL) {

                    if (this.spellData != null) {
                        if (this.spellData.getInit() && !this.dead && this.isTamed())
                        {
                            if (this.getOwner() != null && this.world != null) {
                                if (this.getOwner().getEntityWorld() == this.world) {
                                    if (!PotionEffectUtils.has(lEn, SummonTauntEffect.INSTANCE)) {
                                        PotionEffectUtils.apply(SummonTauntEffect.INSTANCE, this, lEn);
                                        if (lEn instanceof MobEntity) {
                                            lEn.setRevengeTarget(this);
                                            ((MobEntity) lEn).setAttackTarget(this);
                                        }
                                    }
                                    dealSummonDamageTo(lEn);
                                }
                            }
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
        } else if (target instanceof ArchonPetEntity) {
            ArchonPetEntity archonentity = (ArchonPetEntity) target;
            return !archonentity.isTamed() || archonentity.getOwner() != owner;
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
        //this.spellData.setInit(false);
    }

}
