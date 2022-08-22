package com.robertx22.mine_and_slash.database.spells.entities.proj;

import com.robertx22.mine_and_slash.database.spells.entities.bases.EntityBaseProjectile;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.hunting.ImbueSpell;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.potion_effects.ranger.ExertEffect;
import com.robertx22.mine_and_slash.potion_effects.ranger.ImbueEffect;
import com.robertx22.mine_and_slash.saveclasses.EntitySpellData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.AttackSpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GeometryUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

import java.util.List;

public class RangerArrowEntity extends EntityBaseProjectile {

    public RangerArrowEntity(EntityType<? extends Entity> type, World world) {
        super(type, world);
    }

    public RangerArrowEntity(World worldIn) {
        super(EntityRegister.RANGER_ARROW, worldIn);
    }

    public RangerArrowEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.RANGER_ARROW, world);
    }

    @Override
    public void initSpellEntity() {
        try {
            this.imbued = this.getSpellData()
                .getCaster(world)
                .isPotionActive(ImbueEffect.getInstance());
            this.exert = this.getSpellData()
                    .getCaster(world)
                    .isPotionActive(ExertEffect.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setDeathTime(80);
    }

    public boolean imbued = false;
    public boolean exert = false;

    @Override
    public double radius() {
        return 1;
    }

    @Override
    public void onTick() {

        if (world.isRemote) {
            if (this.ticksExisted > 1) {
                if (imbued) {
                    for (int i = 0; i < 2; i++) {
                        Vec3d p = GeometryUtils.getRandomPosInRadiusCircle(getPositionVector(), 0.15F);
                        ParticleUtils.spawn(ParticleTypes.WITCH, world, p);
                    }
                }
                if (exert) {
                    for (int i = 0; i < 2; i++) {
                        Vec3d p = GeometryUtils.getRandomPosInRadiusCircle(getPositionVector(), 0.15F);
                        ParticleUtils.spawn(ParticleTypes.SNEEZE, world, p);
                    }
                }
            }
        }
    }

    public void onHit(LivingEntity entity) {

        try {

            LivingEntity caster = getCaster();

            BaseSpell spell = getSpellData().getSpell();

            AttackSpellDamageEffect dmg = this.getStartupAttackSpellDamage(entity);

            float add = 0;

            if (imbued) {
                add = (float) (ImbueSpell.getInstance()
                    .getCalculation(new SpellCastContext(caster, 0, ImbueSpell.getInstance()))
                    .getCalculatedValue(Load.Unit(caster), Load.spells(caster), ImbueSpell.getInstance()));

                dmg.number += add;
            }

            if (exert) {

                List<LivingEntity> entities = EntityFinder.start(caster, LivingEntity.class, entity.getPositionVector())
                        .radius(radius()).searchFor(EntityFinder.SearchFor.ENEMIES)
                        .build();

                for (LivingEntity en : entities) {
                    if (en != entity) {
                        SpellDamageEffect dmgAoe = this.getSetupSpellDamage(en);
                        dmgAoe.number = (dmgAoe.number + add) * 0.5F; //halves damage after adding imbue, if available
                        dmgAoe.Activate();
                    }
                }

                this.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 0.5F, 1.1F);
            }

            dmg.Activate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onImpact(RayTraceResult result) {
        try {
            LivingEntity entityHit = getEntityHit(result, 0.3D);

            if (entityHit != null) {
                if (world.isRemote) {
                    this.playSound(SoundEvents.ENTITY_GENERIC_HURT, 1F, 0.9F);
                }

                if (!entityHit.world.isRemote) {
                    onHit(entityHit);
                }

            } else {
                if (world.isRemote) {
                    this.playSound(SoundEvents.BLOCK_STONE_HIT, 0.7F, 0.9F);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        this.remove();

    }

    @Override
    public void writeAdditional(CompoundNBT nbt) {
        super.writeAdditional(nbt);
        nbt.putBoolean("imbued", imbued);
        nbt.putBoolean("exert", exert);

    }

    @Override
    public void readAdditional(CompoundNBT nbt) {
        super.readAdditional(nbt);
        this.imbued = nbt.getBoolean("imbued");
        this.exert = nbt.getBoolean("exert");
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(Items.AIR);
    }

}
