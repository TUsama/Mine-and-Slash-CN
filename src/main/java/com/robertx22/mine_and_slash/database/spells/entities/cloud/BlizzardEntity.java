package com.robertx22.mine_and_slash.database.spells.entities.cloud;

import com.robertx22.mine_and_slash.database.spells.entities.bases.BaseCloudEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.saveclasses.EntitySpellData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

import java.util.List;

public class BlizzardEntity extends BaseCloudEntity {

    public BlizzardEntity(World world) {
        super(EntityRegister.BLIZZARD, world);
    }

    public BlizzardEntity(EntityType type, World world) {
        super(type, world);
    }

    public BlizzardEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.BLIZZARD, world);
    }

    @Override
    public void initSpellEntity() {

    }

    @Override
    public void onHit(LivingEntity entity) {

        entity.playSound(SoundEvents.BLOCK_GLASS_BREAK, 1.0f, 1.4f);

        DamageEffect dmg = dealSpellDamageTo(entity, new Options().knockbacks(false)
            .activatesEffect(false));

        dmg.Activate();

    }

    @Override
    public void summonFallParticle(Vec3d p) {
        ParticleUtils.spawn(ParticleTypes.ITEM_SNOWBALL, world, p);
        ParticleUtils.spawn(ParticleTypes.ITEM_SNOWBALL, world, p);
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
                            .radius(RADIUS)
                            .build();

                    entities.forEach(x -> onHit(x));

                    SoundUtils.playSound(this, SoundEvents.ENTITY_HORSE_BREATHE, 1.25F, 2.0F);
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