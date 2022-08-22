package com.robertx22.mine_and_slash.database.spells.entities.summons;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.entities.bases.ISpellEntity;
import com.robertx22.mine_and_slash.database.spells.entities.single_target_bolt.ArchonballEntity;
import com.robertx22.mine_and_slash.database.spells.entities.single_target_bolt.ChainLightningEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.fire.SummonArchonSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.hunting.ImbueSpell;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ParticleRegister;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.all.SummonTauntEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.shaman.StaticEffect;
import com.robertx22.mine_and_slash.saveclasses.EntitySpellData;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.uncommon.datasaving.EntitySpellDataSaving;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.AttackSpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellHealEffect;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GeometryUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

import java.util.EnumSet;
import java.util.List;

public class ArchonPetEntity extends BaseSummonedEntity {

    public ArchonPetEntity(EntityType<? extends TameableEntity> type, World world) {
        super(type, world);
    }

    public ArchonPetEntity(World world) {
        super(EntityRegister.ARCHON_PET, world);
    }

    public ArchonPetEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.ARCHON_PET, world);
    }

    public float radius() {
        return getSpellData().configs.get(SC.RADIUS);
    }

    public float tickRate() {
        return getSpellData().configs.get(SC.TICK_RATE);
    }

    @Override
    public void tick() {
        super.tick();

        if (world.isRemote) {
            if (this.ticksExisted > 1) {
                for (int i = 0; i < 2; i++) {
                    Vec3d p = GeometryUtils.getRandomPosInRadiusCircle(getPositionVector(), 0.2F);
                    ParticleUtils.spawn(ParticleTypes.FLAME, world, p);
                }
            }
        }
        if (!world.isRemote) {
            if (this.ticksExisted % tickRate() == 0){

                ParticleEnum.sendToClients(
                        this, new ParticlePacketData(this.getPosition(), ParticleEnum.AOE).type(
                                ParticleTypes.INSTANT_EFFECT)
                                .motion(new Vec3d(0, 0, 0))
                                .radius(radius())
                                .amount((int) radius()*30));


                List<LivingEntity> entities = EntityFinder.start(this.getOwner(), LivingEntity.class, this.getPositionVector())
                        .radius(radius()).searchFor(EntityFinder.SearchFor.ALLIES)
                        .build();

                SoundUtils.playSound(this, SoundEvents.ENTITY_HORSE_BREATHE, 1.0F, 2.0F);

                for (LivingEntity en : entities) {
                    ParticleEnum.sendToClients(
                            this, new ParticlePacketData(en.getPosition(), ParticleEnum.AOE).type(
                                    ParticleTypes.FLAME)
                                    .motion(new Vec3d(0, 0, 0))
                                    .amount(15));
                    this.healManaTarget(en).Activate();
                }
            }
        }

    }

    @Override
    public boolean attackEntityAsMob(Entity en) {

        if (super.attackEntityAsMob(en)) {
            if (en instanceof LivingEntity) {
                LivingEntity lEn = (LivingEntity) en;
                if (this.world.getDifficulty() != Difficulty.PEACEFUL) {

                    if (this.spellData != null) {
                        if (this.spellData.getInit() && !this.dead && this.isTamed())
                        {
                            if (this.getOwner() != null && this.world != null) {
                                if (this.getOwner().getEntityWorld() == this.world) {
                                    System.out.println("taunt check");
                                    if (!PotionEffectUtils.has(lEn, SummonTauntEffect.INSTANCE)) {
                                        PotionEffectUtils.apply(SummonTauntEffect.INSTANCE, this.getOwner(), lEn);
                                        if (lEn instanceof MobEntity) {
                                            lEn.setRevengeTarget(this);
                                            ((MobEntity) lEn).setAttackTarget(this);
                                        }
                                    }
                                    ParticleEnum.sendToClients(
                                            this, new ParticlePacketData(en.getPosition(), ParticleEnum.AOE).type(
                                                    ParticleTypes.FLAME)
                                                    .motion(new Vec3d(0, 0, 0))
                                                    .amount(15));
                                    dealSummonDamageTo(lEn);
                                }
                            }
                        }
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_BLAZE_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_BLAZE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_BLAZE_HURT;
    }

    @Override
    public float getBrightness() {
        return 1.0F;
    }
}
