package com.robertx22.mine_and_slash.database.spells.entities.single_target_bolt;

import com.robertx22.mine_and_slash.database.spells.entities.bases.BaseElementalBoltEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GeometryUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

import java.util.List;

public class PoisonBallEntity extends BaseElementalBoltEntity {

    public PoisonBallEntity(EntityType<? extends PoisonBallEntity> type, World world) {
        super(type, world);
    }

    public PoisonBallEntity(World worldIn) {

        super(EntityRegister.POISON_BALL, worldIn);

    }

    public PoisonBallEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.POISON_BALL, world);
    }

    @Override
    public void initSpellEntity() {
        this.setNoGravity(true);
        this.setDeathTime(getSpellData().configs.get(SC.DURATION_TICKS)
                .intValue());
    }

    @Override
    public double radius() {
        return getSpellData().configs.get(SC.RADIUS);
    }

    @Override
    public Elements element() {
        return Elements.Nature;
    }

    @Override
    public void onHit(LivingEntity entity) {
    }

    @Override
    public void onTick() {

        if (world.isRemote) {
            if (this.ticksExisted > 1) {
                for (int i = 0; i < 4; i++) {
                    Vec3d p = GeometryUtils.getRandomPosInRadiusCircle(getPositionVector(), (float) radius());
                    ParticleUtils.spawn(ParticleTypes.ITEM_SLIME, world, p);
                }
                for (int i = 0; i < 2; i++) {
                    Vec3d p = GeometryUtils.getRandomPosInRadiusCircle(getPositionVector(), (float) radius() / 2);
                    ParticleUtils.spawn(ParticleTypes.ITEM_SLIME, world, p);
                }
                for (int i = 0; i < 2; i++) {
                    Vec3d p = GeometryUtils.getRandomPosInRadiusCircle(getPositionVector(), (float) radius());
                    ParticleUtils.spawn(ParticleTypes.COMPOSTER, world, p);
                }
            }
        }

        int tickRate = getSpellData().configs.get(SC.TICK_RATE)
                .intValue();

        if (this.ticksExisted % tickRate == 0) {
            if (!world.isRemote) {

                List<LivingEntity> entities = EntityFinder.start(getCaster(), LivingEntity.class, getPositionVector())
                        .radius(radius()).searchFor(EntityFinder.SearchFor.ENEMIES)
                        .build();

                entities.forEach(x -> {

                    SpellDamageEffect dmg = dealSpellDamageTo(x, new Options().activatesEffect(false)
                            .knockbacks(false));

                    dmg.Activate();

                    x.playSound(SoundEvents.ENTITY_SLIME_SQUISH, 1, 1);

                });
            } else {

                SoundUtils.playSound(this, SoundEvents.ENTITY_SLIME_SQUISH, 1, 1);
                // TODO why isnt this being cast?

            }
        }

        if (world.isRemote) {
            if (this.ticksExisted > 1) {
                for (int i = 0; i < 3; i++) {
                    Vec3d p = GeometryUtils.getRandomPosInRadiusCircle(getPositionVector(), 0.25F);
                    ParticleUtils.spawn(ParticleTypes.ITEM_SLIME, world, p);
                }
            }
        }

        if (this.inGround) {
            this.remove();
        }
    }

    @Override
    protected void onImpact(RayTraceResult result) {

        RayTraceResult.Type raytraceresult$type = result.getType();

        if (world.isRemote && raytraceresult$type == RayTraceResult.Type.BLOCK) {
            this.playSound(SoundEvents.BLOCK_STONE_HIT, 1.0f, 0.9F);
            this.remove();
        }
    }

}