package com.robertx22.mine_and_slash.database.spells.entities.single_target_bolt;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.entities.bases.BaseElementalBoltEntity;
import com.robertx22.mine_and_slash.database.spells.entities.summons.ArchonPetEntity;
import com.robertx22.mine_and_slash.database.spells.entities.summons.SkeletonPetEntity;
import com.robertx22.mine_and_slash.database.spells.entities.summons.SpiritWolfPetEntity;
import com.robertx22.mine_and_slash.database.spells.entities.summons.ZombiePetEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GeometryUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TeamUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class LifeSiphonEntity extends BaseElementalBoltEntity {

    public LifeSiphonEntity(EntityType<? extends LifeSiphonEntity> type, World world) {
        super(type, world);
    }

    public LifeSiphonEntity(World worldIn) {

        super(EntityRegister.LIFE_SIPHON, worldIn);

    }

    public LifeSiphonEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.LIFE_SIPHON, world);

    }

    @Override
    public void initSpellEntity() {
        this.setDeathTime(getSpellData().configs.get(SC.DURATION_TICKS)
                .intValue());
    }

    @Override
    public Elements element() {
        return Elements.Physical;
    }

    @Override
    public void onHit(LivingEntity entity) {
        SpellDamageEffect dmg = dealSpellDamageTo(entity, new Options().activatesEffect(false));
        entity.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0f, 0.7f);
        if (isEnemy(entity)) { // should no longer heal if it hit an ally or pet
            SpellUtils.heal(this.getSpellData().getSpell(), this.getSpellData().getCaster(world), dmg.number);
        }
        dmg.Activate();

    }

    public boolean isEnemy(LivingEntity target) {
        LivingEntity caster = this.getSpellData().getCaster(world);
        if (target instanceof SpiritWolfPetEntity) {
            SpiritWolfPetEntity wolfentity = (SpiritWolfPetEntity) target;
            return !wolfentity.isTamed() || wolfentity.getOwner() != caster;
        } else if (target instanceof ZombiePetEntity){
            ZombiePetEntity zombieentity = (ZombiePetEntity) target;
            return !zombieentity.isTamed() || zombieentity.getOwner() != caster;
        } else if (target instanceof SkeletonPetEntity) {
            SkeletonPetEntity skeletonentity = (SkeletonPetEntity) target;
            return !skeletonentity.isTamed() || skeletonentity.getOwner() != caster;
        } else if (target instanceof ArchonPetEntity) {
            ArchonPetEntity archonentity = (ArchonPetEntity) target;
            return !archonentity.isTamed() || archonentity.getOwner() != caster;
        } else if (target instanceof PlayerEntity && caster instanceof PlayerEntity && !((PlayerEntity)caster).canAttackPlayer((PlayerEntity)target)) {
            return false;
        } else if (caster.isOnSameTeam(target)) {
            return false;
        }  else if (target instanceof AbstractHorseEntity && ((AbstractHorseEntity)target).isTame()) {
            return false;
        } else {
            return !(target instanceof TameableEntity) || !((TameableEntity)target).isTamed();
        }
    }


    @Override
    public void onTick() {

        if (world.isRemote) {
            if (this.ticksExisted > 1) {
                for (int i = 0; i < 4; i++) {
                    Vec3d p = GeometryUtils.getRandomPosInRadiusCircle(getPositionVector(), 0.2F);
                    ParticleUtils.spawn(ParticleTypes.WITCH, world, p);
                }
            }

        }

    }

}