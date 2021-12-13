package com.robertx22.mine_and_slash.database.spells.entities.cloud;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.entities.bases.BaseCloudEntity;
import com.robertx22.mine_and_slash.database.spells.entities.proj.RangerArrowEntity;
import com.robertx22.mine_and_slash.database.spells.entities.proj.StoneEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.saveclasses.EntitySpellData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

import java.util.List;

public class RockSlideEntity extends BaseCloudEntity {

    public RockSlideEntity(World world) {
        super(EntityRegister.ROCK_SLIDE, world);
    }

    public RockSlideEntity(EntityType type, World world) {
        super(type, world);
    }

    public RockSlideEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.ROCK_SLIDE, world);
    }

    @Override
    public void initSpellEntity() {

    }

    @Override
    public void onHit(LivingEntity entity) {

        SpellDamageEffect dmg = this.getSetupSpellDamage(entity);
        ParticleUtils.spawn(ParticleTypes.EXPLOSION, world, entity.getPositionVector());

        dmg.Activate();
    }

    @Override
    public void summonFallParticle(Vec3d p) {

    }

    public static int STONES_PER_SUMMON = 3;

    @Override
    public void onTick() {
        try {
            EntitySpellData sdata = getSpellData();
            int tickrate = sdata.configs.get(SC.TICK_RATE)
                    .intValue();
            float RADIUS = sdata.configs.get(SC.RADIUS);

            super.onTick();

            if (this.ticksExisted % tickrate == 0) {
                LivingEntity caster = getCaster();

                if (!this.world.isRemote) {
                    for (int i = 0; i < STONES_PER_SUMMON; i++) {
                        float yRandom = (float) RandomUtils.RandomRange(1, 100) / 80F;
                        float height = 2;
                        Vec3d p = GeometryUtils.getRandomHorizontalPosInRadiusCircle(
                                posX, posY + height + yRandom, posZ, RADIUS);

                        StoneEntity en = SpellUtils.getSpellEntity(getSpellData().configs, new StoneEntity(world), getSpellData().getSpell(), caster);
                        SpellUtils.setupProjectileForCasting(en, caster, 1.5F);
                        en.setMotion(new Vec3d(0, -0.1, 0));
                        en.setLocationAndAngles(p.x, p.y, p.z, 0, 0);
                        caster.world.addEntity(en);

                        List<LivingEntity> entities = EntityFinder.start(
                                getCaster(), LivingEntity.class, getPositionVector())
                                .radius(RADIUS)
                                .build();
                        entities.forEach(x -> onHit(x));

                        SoundUtils.playSound(en, SoundEvents.BLOCK_STONE_BREAK, 2.0F, 0.9F);
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}