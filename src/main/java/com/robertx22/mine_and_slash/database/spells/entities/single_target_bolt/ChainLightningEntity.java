package com.robertx22.mine_and_slash.database.spells.entities.single_target_bolt;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.entities.bases.BaseElementalBoltEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModSounds;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ParticleRegister;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.shaman.ChainLightningEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.*;
import net.minecraft.command.arguments.EntityAnchorArgument;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ArmorStandEntity;
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

import java.util.List;

public class ChainLightningEntity extends BaseElementalBoltEntity {

    public ChainLightningEntity(EntityType<? extends ChainLightningEntity> type, World world) {
        super(type, world);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public ItemStack getItem() {
        return new ItemStack(Items.AIR);
    }

    public ChainLightningEntity(World worldIn) {

        super(EntityRegister.LIGHTNING, worldIn);

    }

    public ChainLightningEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
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

        int shootSpeed = getSpellData().configs.get(SC.SHOOT_SPEED)
                .intValue();
        int chance = getSpellData().configs.get(SC.CHANCE)
                .intValue();

        entity.playSound(SoundEvents.ENTITY_BAT_TAKEOFF, 1.4f, 2.5f);
        PotionEffectUtils.apply(ChainLightningEffect.INSTANCE, getCaster(), entity);
        dealSpellDamageTo(entity);

        LivingEntity caster = getCaster();

        if (RandomUtils.roll(chance)) {
            List<LivingEntity> entities = EntityFinder.start(getCaster(), LivingEntity.class, entity.getPositionVector())
                    .radius(6).searchFor(EntityFinder.SearchFor.ENEMIES)
                    .build();

            if (entities.size() > 0) {

                LivingEntity closest = entities.get(0);

                for (LivingEntity en : entities) {
                    if (en != closest) {
                        if (this.getDistance(en) < this.getDistance(closest) && !PotionEffectUtils.has(en, ChainLightningEffect.INSTANCE) && !(en instanceof ArmorStandEntity)) {
                            closest = en;
                        }
                    }
                }

                if (closest.isAlive() && !PotionEffectUtils.has(closest, ChainLightningEffect.INSTANCE)) {
                    Vec3d p = new Vec3d(posX, posY, posZ);
                    Vec3d t = new Vec3d(closest.posX, closest.posY + (closest.getEyeHeight() / 2), closest.posZ);

                    this.lookAt(EntityAnchorArgument.Type.EYES, t);

                    ChainLightningEntity en = SpellUtils.getSpellEntity(getSpellData().configs, new ChainLightningEntity(world), getSpellData().getSpell(), caster);
                    SpellUtils.setupProjectileForCasting(en, caster, shootSpeed, this.rotationPitch, this.rotationYaw);

                    en.setLocationAndAngles(p.x, p.y, p.z, this.rotationPitch, this.rotationYaw);

                    //en.setMotion(new Vec3d(t.x - p.x, t.y - p.y, t.z - p.z));
                    ParticleUtils.spawn(ParticleTypes.INSTANT_EFFECT, world, p);
                    caster.world.addEntity(en);

                    SoundUtils.playSound(en, ModSounds.THUNDER.get(), 1, 1);
                }
            }
        }
        this.remove();

    }

    @Override
    public void onTick() {

        if (world.isRemote) {
            if (this.ticksExisted > 1) {
                for (int i = 0; i < 20; i++) {
                    Vec3d p = GeometryUtils.getRandomPosInRadiusCircle(getPositionVector(), 0.05F);
                    ParticleUtils.spawn(ParticleRegister.THUNDER3, world, p);
                }
                for (int i = 0; i < 5; i++) {
                    Vec3d p = GeometryUtils.getRandomPosInRadiusCircle(getPositionVector(), 0.05F);
                    ParticleUtils.spawn(ParticleRegister.THUNDER_PURPLE, world, p);
                }
            }
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
            if (world.isRemote) {
                this.playSound(SoundEvents.BLOCK_STONE_HIT, 0.7F, 0.9F);
            }

            this.remove();
        }
    }
}