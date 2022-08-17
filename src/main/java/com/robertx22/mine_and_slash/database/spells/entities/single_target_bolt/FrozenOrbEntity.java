package com.robertx22.mine_and_slash.database.spells.entities.single_target_bolt;

import com.robertx22.mine_and_slash.database.spells.entities.bases.BaseElementalBoltEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ParticleRegister;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GeometryUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

import java.util.List;

public class FrozenOrbEntity extends BaseElementalBoltEntity {

    public FrozenOrbEntity(EntityType<? extends FrozenOrbEntity> type, World world) {
        super(type, world);
    }

    public FrozenOrbEntity(World worldIn) {

        super(EntityRegister.FROZEN_ORB, worldIn);

    }

    public FrozenOrbEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.FROZEN_ORB, world);

    }

    @Override
    public void initSpellEntity() {
        this.setNoGravity(true);

        this.setDeathTime(getSpellData().configs.get(SC.DURATION_TICKS)
                .intValue());
    }

    @Override
    public double radius() {
        return 3.0F;
    }

    @Override
    public Elements element() {
        return Elements.Water;
    }

    @Override
    public void onHit(LivingEntity entity) {

        if (world.isRemote) {
            Vec3d p = GeometryUtils.getRandomPosInRadiusCircle(getPositionVector(), (float) radius());
            ParticleUtils.spawn(ParticleTypes.ITEM_SNOWBALL, world, p);
        }
        LivingEntity caster = getCaster();

        if (caster == null) {
            return;
        }

        List<LivingEntity> entities = EntityFinder.start(caster, LivingEntity.class, getPositionVector())
                .radius((float) radius() * 2).searchFor(EntityFinder.SearchFor.ENEMIES)
                .build();

        entities.forEach(x -> {

            SpellDamageEffect dmg = dealSpellDamageTo(x, new Options().knockbacks(false)
                    .activatesEffect(false));

            dmg.Activate();

            x.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 20, 5));

            this.playSound(SoundEvents.BLOCK_GLASS_BREAK, 1.0f, 1.8f);

        });
    }

    @Override
    public void onTick() {

        if (world.isRemote) {
            if (this.ticksExisted > 1) {
                for (int i = 0; i < 2; i++) {
                    Vec3d p = GeometryUtils.getRandomPosInRadiusCircle(getPositionVector(), (float) radius());
                    ParticleUtils.spawn(ParticleTypes.ITEM_SNOWBALL, world, p);
                }
                for (int i = 0; i < 3; i++) {
                    Vec3d p = GeometryUtils.getRandomPosInRadiusCircle(getPositionVector(), (float) radius() / 2);
                    ParticleUtils.spawn(ParticleTypes.ITEM_SNOWBALL, world, p);
                }
                for (int i = 0; i < 5; i++) {
                    Vec3d p = GeometryUtils.getRandomPosInRadiusCircle(getPositionVector(), (float) radius());
                    ParticleUtils.spawn(ParticleTypes.CLOUD, world, p);
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
                    x.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 20, 5));

                });

            } else {

                SoundUtils.playSound(this, SoundEvents.ENTITY_SNOWBALL_THROW, 1, 1);
                // TODO why isnt this being cast?

            }
        }

        if (this.ticksExisted % tickRate == 5) {
            if (!world.isRemote) {
                SoundUtils.playSound(this, SoundEvents.ENTITY_HORSE_BREATHE, 3.0F, 4.0F);
            }
        }

        if (world.isRemote) {

            for (int i = 0; i < 3; i++) {
                Vec3d p = GeometryUtils.getRandomPosInRadiusCircle(getPositionVector(), (float) radius());
                ParticleUtils.spawn(ParticleTypes.ITEM_SNOWBALL, world, p);

            }
        }

        if (this.inGround) {
            this.remove();
        }

    }

    @Override
    protected void onImpact(RayTraceResult result) {

        RayTraceResult.Type raytraceresult$type = result.getType();

        LivingEntity entityHit = getEntityHit(result, 0.3D);

        if (world.isRemote && raytraceresult$type == RayTraceResult.Type.BLOCK) {
            //SoundUtils.playSound(this, SoundEvents.BLOCK_STONE_HIT, 1.0F, 0.9F);
            this.playSound(SoundEvents.BLOCK_STONE_HIT, 1.0F, 0.9F);
            this.remove();
        }
        if (entityHit != null) {
            if (world.isRemote) {
                //SoundUtils.playSound(this, SoundEvents.ENTITY_GENERIC_HURT, 1F, 0.9F);
                this.playSound(SoundEvents.ENTITY_GENERIC_HURT, 1F, 0.9F);
                onHit(entityHit);
            }
        }

    }

}