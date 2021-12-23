package com.robertx22.mine_and_slash.database.spells.entities.single_target_bolt;

import com.robertx22.mine_and_slash.database.spells.entities.bases.BaseElementalBoltEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ParticleRegister;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GeometryUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
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

public class LightningEntity extends BaseElementalBoltEntity {

    public LightningEntity(EntityType<? extends LightningEntity> type, World world) {
        super(type, world);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public ItemStack getItem() {
        return new ItemStack(Items.AIR);
    }

    public LightningEntity(World worldIn) {

        super(EntityRegister.LIGHTNING, worldIn);

    }

    public LightningEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.LIGHTNING, world);
    }

    @Override
    public void initSpellEntity() {
        this.setNoGravity(true);
        this.setDeathTime(getSpellData().configs.get(SC.DURATION_TICKS)
                .intValue());
    }

    @Override
    public Elements element() {
        return Elements.Thunder;
    }

    @Override
    public void onHit(LivingEntity entity) {
        entity.playSound(SoundEvents.ENTITY_BAT_TAKEOFF, 1.4f, 2.5f);
        dealSpellDamageTo(entity).removeKnockback();

    }

    @Override
    public void onTick() {

        if (world.isRemote) {
            if (this.ticksExisted > 1) {
                for (int i = 0; i < 20; i++) {
                    Vec3d p = GeometryUtils.getRandomPosInRadiusCircle(getPositionVector(), 0.1F);
                    ParticleUtils.spawn(ParticleRegister.THUNDER3, world, p);
                }
            }

        }

        if (this.inGround) {
            this.remove();
        }

    }

    @Override
    protected void onImpact(RayTraceResult result) {

        LivingEntity entityHit = getEntityHit(result, 0.15D);

        if (entityHit != null) {
            if (world.isRemote) {
                this.playSound(SoundEvents.ENTITY_GENERIC_HURT, 1F, 0.9F);
            }
            onHit(entityHit);

        } else {
            RayTraceResult.Type raytraceresult$type = result.getType();
            if (world.isRemote && raytraceresult$type == RayTraceResult.Type.BLOCK) {
                this.playSound(SoundEvents.ENTITY_BAT_TAKEOFF, 1.4f, 2.5f);
                this.remove();
            }
        }
    }
}