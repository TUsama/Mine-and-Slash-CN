package com.robertx22.mine_and_slash.database.spells.spell_classes.divine;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModSounds;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ParticleRegister;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ChargeSpell extends BaseSpell {

    private ChargeSpell() {
        super(
            new ImmutableSpellConfigs() {

                @Override
                public Masteries school() {
                    return Masteries.DIVINE;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.SPECIAL;
                }

                @Override
                public SoundEvent sound() {
                    return SoundEvents.ENTITY_SNOWBALL_THROW;
                }

                @Override
                public Elements element() {
                    return Elements.Physical;
                }
            }.rightClickFor(AllowedAsRightClickOn.MELEE_WEAPON)
        );
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.HEALTH_COST, 0, 0);
        c.set(SC.MANA_COST, 3, 6);
        c.set(SC.ENERGY_COST, 7, 14);
        c.set(SC.MAGIC_SHIELD_COST, 0, 0);
        c.set(SC.BASE_VALUE, 2, 9);
        c.set(SC.PHYSICAL_ATTACK_SCALE_VALUE, 1.4F, 3.0F);
        c.set(SC.CAST_TIME_TICKS, 0, 0);
        c.set(SC.COOLDOWN_SECONDS, 14, 7);

        c.setMaxLevel(16);

        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(6, 2);
    }

    public static ChargeSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "charge";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Attack Spell"));
        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "" + TextFormatting.ITALIC + "Spell that also triggers on-attack effects."));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Movement"));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent("Dash in your current direction,"));
        list.add(new StringTextComponent("damages all enemies in the path: "));

        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));

        return list;

    }

    @Override
    public Words getName() {
        return Words.Charge;
    }

    public static void dashForward(LivingEntity caster) {

        Vec3d playerLook = caster.getLook(1);
        Vec3d dashVec = new Vec3d(playerLook.getX() * 4, caster.getMotion().getY(), playerLook.getZ() * 4);
        caster.setMotion(dashVec);
        //float distance = 0.017453292f;
        //caster.setMotion(new Vec3d(0, 0, 0));
        //caster.knockBack(caster, 3.5f, (double) MathHelper.sin(caster.rotationYaw * distance),
        //    (double) (-MathHelper.cos(caster.rotationYaw * distance))
        //);
        if (caster instanceof ServerPlayerEntity) {
            ((ServerPlayerEntity) caster).connection.sendPacket(new SEntityVelocityPacket(caster));
            caster.velocityChanged = false;
        }
    }

    @Override
    public void castExtra(SpellCastContext ctx) {
        LivingEntity caster = ctx.caster;
        World world = ctx.caster.world;

        for (int i = 0; i < 20; ++i)
        {
            double d0 = world.rand.nextGaussian() * 0.02D;
            double d1 = world.rand.nextGaussian() * 0.02D;
            double d2 = world.rand.nextGaussian() * 0.02D;
            world.addParticle(ParticleTypes.POOF, caster.getPosX() + (double) (caster.world.rand.nextFloat() * caster.getWidth() * 2.0F) - (double) caster.getWidth() - d0 * 10.0D, caster.getPosY() + (double) (caster.world.rand.nextFloat() * caster.getHeight()) - d1 * 10.0D, caster.getPosZ() + (double) (caster.world.rand.nextFloat() * caster.getWidth() * 2.0F) - (double) caster.getWidth() - d2 * 10.0D, d0, d1, d2);
        }


        dashForward(ctx.caster);

        int num = getCalculation(ctx).getCalculatedValue(Load.Unit(caster), ctx.spellsCap, ctx.ability);

        List<LivingEntity> entities = EntityFinder.start(caster, LivingEntity.class, caster.getPositionVector())
            .radius(3)
            .distance(12)
            .finder(EntityFinder.Finder.IN_FRONT)
            .build();

        entities.forEach(x -> {
            DamageEffect dmg = new DamageEffect(null, caster, x, num, EffectData.EffectTypes.ATTACK_SPELL, WeaponTypes.None);
            dmg.element = Elements.Physical;
            dmg.Activate();
        });

        SoundUtils.playSound(caster, ModSounds.DASH.get(), 1, 1.2F);

    }

    private static class SingletonHolder {
        private static final ChargeSpell INSTANCE = new ChargeSpell();
    }
}