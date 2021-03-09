package com.robertx22.mine_and_slash.database.spells.entities.proj;

import com.robertx22.mine_and_slash.database.spells.entities.bases.BaseElementalBoltEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ParticleRegister;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GeometryUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class GroundSlamEntity extends BaseElementalBoltEntity {

    public GroundSlamEntity(EntityType<? extends GroundSlamEntity> type, World world) {
        super(type, world);
    }

    public GroundSlamEntity(World worldIn) {

        super(EntityRegister.GROUND_SLAM, worldIn);

    }

    @Override
    public void initSpellEntity() {
        this.setNoGravity(true);
        this.setDeathTime(getSpellData().configs.get(SC.DURATION_TICKS)
                .intValue());
    }

    public GroundSlamEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.GROUND_SLAM, world);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public ItemStack getItem() {
        return new ItemStack(Items.IRON_BLOCK);
    }

    @Override
    public Elements element() {
        return Elements.Elemental;
    }

    @Override
    public void onHit(LivingEntity entity) {
        dealSpellDamageTo(entity);

        SoundUtils.playSound(this, SoundEvents.ENTITY_GENERIC_HURT, 0.1F, 1F);
    }

    @Override
    public void onTick() {
        if (world.isRemote) {
            if (this.ticksExisted > 2) {
                for (int i = 0; i < 10; i++) {
                    Vec3d p = GeometryUtils.getRandomPosInRadiusCircle(getPositionVector(), 0.2F);
                    ParticleUtils.spawn(ParticleTypes.SMOKE, world, p);
                }
            }
        }

    }

    @Override
    protected void onImpact(RayTraceResult result) {

        LivingEntity entityHit = getEntityHit(result, 0.3D);

        if (entityHit != null) {
            if (world.isRemote) {
                SoundUtils.playSound(this, SoundEvents.ENTITY_GENERIC_HURT, 1F, 0.9F);
            }

            onHit(entityHit);

        } else {
            if (world.isRemote) {
                SoundUtils.playSound(this, SoundEvents.BLOCK_STONE_HIT, 0.7F, 0.9F);
                ParticleUtils.spawn(ParticleTypes.EXPLOSION, world, getPositionVec());
                this.remove();
            }
        }
    }

}