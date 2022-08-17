package com.robertx22.mine_and_slash.database.spells.entities.single_target_bolt;

import com.robertx22.mine_and_slash.database.spells.entities.bases.BaseElementalBoltEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.saveclasses.EntitySpellData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GeometryUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

import java.util.List;

public class FireballEntity extends BaseElementalBoltEntity {

    public FireballEntity(EntityType<? extends FireballEntity> type, World world) {
        super(type, world);
    }

    public FireballEntity(World worldIn) {

        super(EntityRegister.FIREBOLT, worldIn);

    }

    public FireballEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.FIREBOLT, world);
    }

    @Override
    public void initSpellEntity() {
        this.setNoGravity(true);
        this.setDeathTime(getSpellData().configs.get(SC.DURATION_TICKS)
                .intValue());
    }

    @Override
    public Elements element() {
        return Elements.Fire;
    }

    @Override
    public void onHit(LivingEntity entity) {

        EntitySpellData sdata = getSpellData();
        float RADIUS = sdata.configs.get(SC.RADIUS);

        List<LivingEntity> entities = EntityFinder.start(
                getCaster(), LivingEntity.class, entity.getPositionVector())
                .radius(RADIUS).searchFor(EntityFinder.SearchFor.ENEMIES)
                .build();

        entities.forEach(x -> dealSpellDamageTo(x));

        ParticleUtils.spawn(ParticleTypes.EXPLOSION, world, entity.getPositionVector());
        entity.playSound(SoundEvents.ENTITY_DRAGON_FIREBALL_EXPLODE, 0.9F, 1F);
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