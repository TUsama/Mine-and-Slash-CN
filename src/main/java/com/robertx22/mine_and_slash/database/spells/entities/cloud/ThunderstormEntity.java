package com.robertx22.mine_and_slash.database.spells.entities.cloud;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.entities.bases.BaseCloudEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.saveclasses.EntitySpellData;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

import java.util.List;

public class ThunderstormEntity extends BaseCloudEntity {

    public ThunderstormEntity(World world) {
        super(EntityRegister.THUNDERSTORM, world);
    }

    public ThunderstormEntity(EntityType type, World world) {
        super(type, world);
    }

    public ThunderstormEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.THUNDERSTORM, world);
    }

    @Override
    public void initSpellEntity() {

    }

    @Override
    public void onHit(LivingEntity entity) {
        this.dealSpellDamageTo(entity, new Options().knockbacks(false));
        entity.playSound(SoundEvents.ENTITY_BAT_TAKEOFF, 1.4f, 2.5f);

        SpellUtils.summonLightningStrike(entity);

    }

    @Override
    public void summonFallParticle(Vec3d p) {

        ParticleUtils.spawn(ParticleTypes.FALLING_WATER, world, p);

    }

    @Override
    public void onTick() {
        try {
            EntitySpellData sdata = getSpellData();
            int TICK_RATE = sdata.configs.get(SC.TICK_RATE)
                    .intValue();
            float RADIUS = sdata.configs.get(SC.RADIUS);

            if (this.ticksExisted % TICK_RATE == 1) {

                if (!this.world.isRemote) {

                    List<LivingEntity> entities = EntityFinder.start(
                            getCaster(), LivingEntity.class, getPositionVector())
                            .radius(RADIUS).searchFor(EntityFinder.SearchFor.ENEMIES)
                            .build();

                    SoundUtils.playSound(this, SoundEvents.WEATHER_RAIN, 1.1F, 0.8F);

                    entities.forEach(x -> onHit(x));

                }
            }

            if (world.isRemote && ticksExisted % 2 == 0) {

                for (int i = 1; i < 12; i++) {
                    double speed = (rand.nextBoolean() ? 1 : -1) * 0.1 + 0.05 * rand.nextDouble();

                    float yRandom = (float) RandomUtils.RandomRange(1, 100) / 40F;

                    float height = 4;

                    Vec3d p = GeometryUtils.getRandomHorizontalPosInRadiusCircle(
                            posX, posY + height + yRandom, posZ, RADIUS);

                    if (spawnCloudParticles()) {
                        for (int a = 1; a < 2; a++) {
                            ParticleUtils.spawn(ParticleTypes.CLOUD, world, p.add(0, 1, 0));
                        }
                    }

                    summonFallParticle(p);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}