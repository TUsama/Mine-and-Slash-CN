package com.robertx22.mine_and_slash.database.spells.entities.cloud;

import com.robertx22.mine_and_slash.database.spells.entities.bases.BaseCloudEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.ocean_mystic.FrostEffect;
import com.robertx22.mine_and_slash.potion_effects.ocean_mystic.FrozenEffect;
import com.robertx22.mine_and_slash.potion_effects.ocean_mystic.ShiverEffect;
import com.robertx22.mine_and_slash.saveclasses.EntitySpellData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
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

public class ChillingFieldEntity extends BaseCloudEntity {

    public ChillingFieldEntity(World world) {
        super(EntityRegister.CHILLING_FIELD, world);
    }

    public ChillingFieldEntity(EntityType type, World world) {
        super(type, world);
    }

    public ChillingFieldEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.CHILLING_FIELD, world);
    }

    @Override
    public void initSpellEntity() {

    }

    @Override
    public void onHit(LivingEntity entity) {

        entity.playSound(SoundEvents.ENTITY_HORSE_BREATHE, 1, 1.5F);

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

                    SoundUtils.playSound(this, SoundEvents.BLOCK_BUBBLE_COLUMN_UPWARDS_INSIDE, 2, 2);

                    List<LivingEntity> entities = EntityFinder.start(
                            getCaster(), LivingEntity.class, getPositionVector())
                            .radius(RADIUS)
                            .build();

                    entities.forEach(x -> {PotionEffectUtils.apply(FrostEffect.INSTANCE, getCaster(), x);});
                    entities.forEach(x -> onHit(x));

                }
            }

            if (world.isRemote && ticksExisted % 2 == 0) {

                for (int i = 1; i < 6; i++) {
                    double speed = (rand.nextBoolean() ? 1 : -1) * 0.1 + 0.05 * rand.nextDouble();

                    Vec3d p = GeometryUtils.getRandomHorizontalPosInRadiusCircle(
                            posX, posY, posZ, RADIUS);

                    if (spawnCloudParticles()) {
                        for (int a = 1; a < 4; a++) {
                            ParticleUtils.spawn(ParticleTypes.CLOUD, world, p.add(0, 0, 0));
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
        ParticleUtils.spawn(ParticleTypes.ITEM_SNOWBALL, world, p);
    }
}