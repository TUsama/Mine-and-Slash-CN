package com.robertx22.mine_and_slash.database.spells.entities.summons;

import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ParticleRegister;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GeometryUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.LevelUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.entity.monster.MonsterEntity;
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

public class SpiritWolfPetEntity extends BaseSummonedEntity {

    public SpiritWolfPetEntity(EntityType<? extends TameableEntity> type, World world) {
        super(type, world);
    }

    public SpiritWolfPetEntity(World world) {
        super(EntityRegister.SPIRIT_WOLF_PET, world);
    }

    public SpiritWolfPetEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.SPIRIT_WOLF_PET, world);
    }

    /*
    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED)
                .setBaseValue((double) 0.5F);

        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH)
                .setBaseValue(15.0D);

        this.getAttributes()
                .registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE)
                .setBaseValue(0.5D); // atk only with a tiny bit of damage to proc the vanilla stuff like knockback
    }

     */

    @Override
    public void tick() {
        super.tick();

        if (world.isRemote) {
            if (this.ticksExisted > 1) {
                for (int i = 0; i < 3; i++) {
                    Vec3d p = GeometryUtils.getRandomPosInRadiusCircle(getPositionVector(), 0.2F);
                    ParticleUtils.spawn(ParticleTypes.SMOKE, world, p);
                }
            }

        }

    }

    @Override
    protected void registerGoals() {
        this.sitGoal = new SitGoal(this);
        this.goalSelector.addGoal(5, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(6, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(7, new SwimGoal(this));
        this.goalSelector.addGoal(8, new FollowOwnerGoal(this, 1.0D, 6.0F, 1.0F, false));
        this.goalSelector.addGoal(9, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(10, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(10, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setCallsForHelp());
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, MobEntity.class, false));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_WOLF_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_WOLF_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_WOLF_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
    }

}
