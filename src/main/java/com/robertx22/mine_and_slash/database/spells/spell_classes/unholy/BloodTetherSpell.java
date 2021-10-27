package com.robertx22.mine_and_slash.database.spells.spell_classes.unholy;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.ember_mage.SpellBladeEffect;
import com.robertx22.mine_and_slash.potion_effects.necromancer.NecroticTetherEffect;
import com.robertx22.mine_and_slash.potion_effects.ranger.HunterInstinctEffect;
import com.robertx22.mine_and_slash.potion_effects.ranger.WoundsEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class BloodTetherSpell extends BaseSpell {

    private BloodTetherSpell() {
        super(
            new ImmutableSpellConfigs() {
                @Override
                public Masteries school() {
                    return Masteries.UNHOLY;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.GIVE_EFFECT;
                }

                @Override
                public SoundEvent sound() {
                    return SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE;
                }

                @Override
                public Elements element() {
                    return Elements.Physical;
                }
            }.addsEffect(NecroticTetherEffect.INSTANCE)
                .setSwingArmOnCast());

    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.HEALTH_COST, 0.04F, 0.012F);
        c.set(SC.MANA_COST, 10, 15);
        c.set(SC.ENERGY_COST, 0, 0);
        c.set(SC.AMOUNT, 1, 5);
        c.set(SC.MAGIC_SHIELD_COST, 0, 0);
        c.set(SC.CAST_TIME_TICKS, 20, 20);
        c.set(SC.COOLDOWN_SECONDS, 20, 16);

        c.setMaxLevel(10);
        return c;
    }

    public static BloodTetherSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public void castExtra(SpellCastContext ctx) {

        float amount = ((int) ctx.getConfigFor(this)
                .get(SC.AMOUNT).get(Load.spells(ctx.caster), this)) - 1;

        for (int i = 0; i < amount; i++) {
            PotionEffectUtils.reApplyToSelf(NecroticTetherEffect.INSTANCE, ctx.caster);
        }
    }

    @Override
    public String GUID() {
        return "blood_tether";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Spell"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Buff, Self"));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent("Applies buff (can apply more than"));
        list.add(new StringTextComponent("once at a time): " + NecroticTetherEffect.INSTANCE
                .locNameForLangFile()));

        return list;

    }

    @Override
    public Words getName() {
        return Words.BloodTether;
    }

    @Override
    public void spawnParticles(SpellCastContext ctx) {
        if (ctx.caster.world.isRemote) {
            ParticleUtils.spawnParticles(ParticleTypes.DRIPPING_LAVA, ctx.caster, 20);
            ParticleUtils.spawnParticles(ParticleTypes.WITCH, ctx.caster, 10);
        }
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(4, 3);
    }

    private static class SingletonHolder {
        private static final BloodTetherSpell INSTANCE = new BloodTetherSpell();
    }
}
