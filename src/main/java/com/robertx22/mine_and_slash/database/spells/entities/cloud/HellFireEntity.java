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

public class HellFireEntity extends BaseCloudEntity {

    public HellFireEntity(World world) {
        super(EntityRegister.STEAM_CLOUD, world);
    }

    public HellFireEntity(EntityType type, World world) {
        super(type, world);
    }

    public HellFireEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.STEAM_CLOUD, world);
    }

    @Override
    public void initSpellEntity() {

    }

    @Override
    public void onHit(LivingEntity entity) {

        entity.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 1.0f, 1.2f);

        DamageEffect dmg = dealSpellDamageTo(entity, new Options().knockbacks(false)
            .activatesEffect(false));

        dmg.Activate();

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

                    setPosition(getCaster().getPosX(),getCaster().getPosY(),getCaster().getPosZ());

                    List<LivingEntity> entities = EntityFinder.start(
                            getCaster(), LivingEntity.class, getPositionVector())
                            .radius(RADIUS)
                            .build();

                    SoundUtils.playSound(this, SoundEvents.BLOCK_FIRE_AMBIENT, 1.1F, 0.9F);

                    entities.forEach(x -> onHit(x));

                }
            }

            if (world.isRemote && ticksExisted % 2 == 0) {

                for (int i = 1; i < 6; i++) {
                    double speed = (rand.nextBoolean() ? 1 : -1) * 0.1 + 0.05 * rand.nextDouble();

                    posX = getCaster().getPosX();
                    posY = getCaster().getPosY();
                    posZ = getCaster().getPosZ();

                    Vec3d p = GeometryUtils.getRandomHorizontalPosInRadiusCircle(
                            posX, posY + 0.5F, posZ, RADIUS);

                    if (spawnCloudParticles()) {
                        for (int a = 1; a < 2; a++) {
                            ParticleUtils.spawn(ParticleTypes.SMOKE, world, p.add(0, 3, 0));
                        }
                    }

                    summonFallParticle(p);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void summonFallParticle(Vec3d p) {
        ParticleUtils.spawn(ParticleTypes.FALLING_LAVA, world, p);
    }
}