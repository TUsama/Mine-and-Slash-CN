package com.robertx22.mine_and_slash.database.spells.spell_classes.storm;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.potion_effects.ember_mage.SpellBladeEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
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

public class LightningAspectSpell extends BaseSpell {

    private LightningAspectSpell() {
        super(
            new ImmutableSpellConfigs() {
                @Override
                public Masteries school() {
                    return Masteries.STORM;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.GIVE_EFFECT;
                }

                @Override
                public SoundEvent sound() {
                    return SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER;
                }

                @Override
                public Elements element() {
                    return Elements.Thunder;
                }
            }.addsEffect(SpellBladeEffect.INSTANCE)
                .setSwingArmOnCast());

    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.HEALTH_COST, 0, 0);
        c.set(SC.MANA_COST, 10, 15);
        c.set(SC.ENERGY_COST, 0, 0);
        c.set(SC.MAGIC_SHIELD_COST, 0, 0);
        c.set(SC.CAST_TIME_TICKS, 20, 20);
        c.set(SC.COOLDOWN_SECONDS, 23, 19);
        c.set(SC.DURATION_TICKS, 20 * 10, 20 * 20);

        c.setMaxLevel(10);
        return c;
    }

    public static LightningAspectSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "spell_blade";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Spell"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Buff, Duration, Self"));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent("Applies buff: "));
        list.addAll(SpellBladeEffect.INSTANCE.GetTooltipStringWithNoExtraSpellInfo(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.SpellBlade;
    }

    @Override
    public void spawnParticles(SpellCastContext ctx) {
        if (ctx.caster.world.isRemote) {
            ParticleUtils.spawnParticles(ParticleTypes.FLAME, ctx.caster, 20);
            ParticleUtils.spawnParticles(ParticleTypes.WITCH, ctx.caster, 10);
        }
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(7, 1);
    }

    private static class SingletonHolder {
        private static final LightningAspectSpell INSTANCE = new LightningAspectSpell();
    }
}
