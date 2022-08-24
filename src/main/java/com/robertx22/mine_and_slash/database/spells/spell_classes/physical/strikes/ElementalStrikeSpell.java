package com.robertx22.mine_and_slash.database.spells.spell_classes.physical.strikes;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellPredicates;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModSounds;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ParticleRegister;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.physical.ArmorBreakEffect;
import com.robertx22.mine_and_slash.potion_effects.physical.ComboStarterEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.AttackSpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class ElementalStrikeSpell extends BaseSpell {

    private ElementalStrikeSpell() {
        super(
            new ImmutableSpellConfigs() {

                @Override
                public Masteries school() {
                    return Masteries.PHYSICAL;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.SPECIAL;
                }

                @Override
                public SoundEvent sound() {
                    return ModSounds.PUNCH.get();
                }

                @Override
                public Elements element() {
                    return Elements.Elemental;
                }
            }.cooldownIfCanceled(true)
                .rightClickFor(AllowedAsRightClickOn.MELEE_WEAPON)
                .setSwingArmOnCast().addCastRequirement(SpellPredicates.REQUIRE_MELEE));
    }

    @Override
    public void castExtra(SpellCastContext ctx) {

        if (ctx.caster instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) ctx.caster;
            player.spawnSweepParticles();
        }

        ctx.caster.world.playSound((PlayerEntity) null, ctx.caster.getPosX(), ctx.caster.getPosY(), ctx.caster.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 1.0F, 1.0F);

        Vec3d look = ctx.caster.getLookVec()
            .scale(3);

        List<LivingEntity> list = EntityFinder.start(ctx.caster, LivingEntity.class, ctx.caster.getPositionVector()
            .add(look)
            .add(0, ctx.caster.getHeight() / 2, 0))
            .finder(EntityFinder.Finder.RADIUS).searchFor(EntityFinder.SearchFor.ENEMIES)
            .radius(2)
            .height(2)
            .build();

        SoundUtils.playSound(ctx.caster, SoundEvents.ENTITY_ARMOR_STAND_HIT, 0.8F, 1.0F);

        int num = ctx.getConfigFor(this)
                .getCalc(ctx.spellsCap, this)
                .getCalculatedValue(ctx.data, ctx.spellsCap, this);

        int elementNum = RandomUtils.RandomRange(0, 3);
        Elements element = null;
        if (elementNum == 0) {
            element = Elements.Water;
        } else if (elementNum == 1) {
            element = Elements.Fire;
        } else if (elementNum == 2) {
            element = Elements.Thunder;
        } else if (elementNum == 3) {
            element = Elements.Nature;
        }

        for (LivingEntity en : list) {
            AttackSpellDamageEffect dmg = new AttackSpellDamageEffect(ctx.caster, en, num, ctx.data, Load.Unit(en),
                this
            );
            dmg.element = element;
            dmg.Activate();

            if (elementNum == 0) {
                ParticleEnum.sendToClients(
                        en.getPosition(), en.world,
                        new ParticlePacketData(en.getPositionVector(), ParticleEnum.AOE).radius(1)
                                .motion(new Vec3d(0, 0, 0))
                                .type(ParticleRegister.BUBBLE)
                                .amount((int) (5)));
            } else if (elementNum == 1) {
                ParticleEnum.sendToClients(
                        en.getPosition(), en.world,
                        new ParticlePacketData(en.getPositionVector(), ParticleEnum.AOE).radius(1)
                                .motion(new Vec3d(0, 0, 0))
                                .type(ParticleTypes.FLAME)
                                .amount((int) (5)));
            } else if (elementNum == 2) {
                ParticleEnum.sendToClients(
                        en.getPosition(), en.world,
                        new ParticlePacketData(en.getPositionVector(), ParticleEnum.AOE).radius(1)
                                .motion(new Vec3d(0, 0, 0))
                                .type(ParticleRegister.THUNDER)
                                .amount((int) (5)));
            } else if (elementNum == 3) {
                ParticleEnum.sendToClients(
                        en.getPosition(), en.world,
                        new ParticlePacketData(en.getPositionVector(), ParticleEnum.AOE).radius(1)
                                .motion(new Vec3d(0, 0, 0))
                                .type(ParticleTypes.COMPOSTER)
                                .amount((int) (5)));
            }
        }
        PotionEffectUtils.reApplyToSelf(ComboStarterEffect.INSTANCE, ctx.caster);
    }

    public static ElementalStrikeSpell getInstance() {
        return ElementalStrikeSpell.SingletonHolder.INSTANCE;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.HEALTH_COST, 0, 0);
        c.set(SC.MANA_COST, 0, 0);
        c.set(SC.ENERGY_COST, 6, 9);
        c.set(SC.MAGIC_SHIELD_COST, 0, 0);
        c.set(SC.BASE_VALUE, 0, 0);
        c.set(SC.ATTACK_SCALE_VALUE, 2.0F, 3.0F);
        c.set(SC.CAST_TIME_TICKS, 0, 0);
        c.set(SC.COOLDOWN_TICKS, 60, 60);
        c.set(SC.CDR_EFFICIENCY, 0, 0);
        c.set(SC.TIMES_TO_CAST, 1, 1);

        c.setMaxLevel(16);

        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(0, 5);
    }

    @Override
    public String GUID() {
        return "elemental_strike";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Attack Spell"));
        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "" + TextFormatting.ITALIC + "Spell that also triggers on-attack effects."));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Melee"));

        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent(TextFormatting.GRAY + "This spell's cooldown is unaffected by"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "cooldown reduction."));
        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent(TextFormatting.GRAY + "Converts Weapon DMG to a Random Element DMG."));
        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent(TextFormatting.GRAY + "Finishing this spell generates: " + ComboStarterEffect.INSTANCE.locNameForLangFile()));
        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent("Strike the enemies in front of you to deal damage"));
        list.add(new StringTextComponent("as one elements, chosen at random: "));

        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));

        return list;

    }

    @Override
    public Words getName() {
        return Words.ElementalStrike;
    }

    private static class SingletonHolder {
        private static final ElementalStrikeSpell INSTANCE = new ElementalStrikeSpell();
    }
}