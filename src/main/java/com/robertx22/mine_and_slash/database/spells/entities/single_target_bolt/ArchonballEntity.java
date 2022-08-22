package com.robertx22.mine_and_slash.database.spells.entities.single_target_bolt;

import com.robertx22.mine_and_slash.database.spells.entities.bases.BaseElementalBoltEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.saveclasses.EntitySpellData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GeometryUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

import java.util.List;

public class ArchonballEntity extends BaseElementalBoltEntity {

    public ArchonballEntity(EntityType<? extends ArchonballEntity> type, World world) {
        super(type, world);
    }

    public ArchonballEntity(World worldIn) {

        super(EntityRegister.ARCHONBALL, worldIn);

    }

    public ArchonballEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.ARCHONBALL, world);
    }

    @Override
    public void initSpellEntity() {
        this.setNoGravity(true);
        this.setDeathTime(60);
    }

    @Override
    public Elements element() {
        return Elements.Fire;
    }

    @Override
    public void onHit(LivingEntity entity) {

        EntitySpellData sdata = getSpellData();

        dealSpellDamageTo(entity);

        ParticleUtils.spawnParticles(ParticleTypes.FLAME, entity, 20);
        entity.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 1.0F, 0.75F);
    }

    @Override
    public void onTick() {

        if (world.isRemote) {
            if (this.ticksExisted > 1) {
                for (int i = 0; i < 5; i++) {
                    Vec3d p = GeometryUtils.getRandomPosInRadiusCircle(getPositionVector(), 0.2F);
                    ParticleUtils.spawn(ParticleTypes.FLAME, world, p);
                }
            }
        }

    }

}