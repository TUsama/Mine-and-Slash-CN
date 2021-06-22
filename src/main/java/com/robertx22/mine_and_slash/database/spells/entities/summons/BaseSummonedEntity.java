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
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

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
    public void writeSpawnData(PacketBuffer buf) {
        CompoundNBT nbt = new CompoundNBT();
        writeAdditional(nbt);
        buf.writeCompoundTag(nbt);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED)
            .setBaseValue((double) 0.3F);

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

        super.tick();

        if (this.ticksExisted > this.durationInTicks()) {
            this.remove();
        }

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
        if (this.isTamed()) {
            LivingEntity livingentity = this.getOwner();
            if (entityIn == livingentity) {
                return true;
            }

            if (livingentity != null) {
                return livingentity.isOnSameTeam(entityIn) || TeamUtils.areOnSameTeam((ServerPlayerEntity) livingentity, (ServerPlayerEntity) entityIn);
            }
        }

        return super.isOnSameTeam(entityIn);
    }

    @Override
    public boolean attackEntityAsMob(Entity en) {

        if (super.attackEntityAsMob(en)) {
            if (en instanceof LivingEntity) {
                if (this.world.getDifficulty() != Difficulty.PEACEFUL) {

                    dealSpellDamageTo((LivingEntity) en);

                }
            }

            return true;
        } else {
            return false;
        }
    }

}
