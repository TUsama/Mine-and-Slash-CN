package com.robertx22.mine_and_slash.database.spells.entities.summons;

import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GeometryUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class ZombiePetEntity extends BaseSummonedEntity implements IRangedAttackMob {

    public ZombiePetEntity(EntityType<? extends TameableEntity> type, World world) {
        super(type, world);
    }

    public ZombiePetEntity(World world) {
        super(EntityRegister.ZOMBIE_PET, world);
    }

    public ZombiePetEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.ZOMBIE_PET, world);
    }

    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {

    }

    /*
    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED)
                .setBaseValue((double) 0.3F);

        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH)
                .setBaseValue(15.0D);

        this.getAttributes()
                .registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE)
                .setBaseValue(0.5D); // atk only with a tiny bit of damage to proc the vanilla stuff like knockback
    }*/

    @Override
    public void tick() {
        super.tick();

        if (world.isRemote) {
            if (this.ticksExisted > 1) {
                for (int i = 0; i < 2; i++) {
                    Vec3d p = GeometryUtils.getRandomPosInRadiusCircle(getPositionVector(), 0.2F);
                    ParticleUtils.spawn(ParticleTypes.WITCH, world, p);
                }
            }

        }

    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_ZOMBIE_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_ZOMBIE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_ZOMBIE_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ENTITY_ZOMBIE_STEP, 0.15F, 1.0F);
    }

}
