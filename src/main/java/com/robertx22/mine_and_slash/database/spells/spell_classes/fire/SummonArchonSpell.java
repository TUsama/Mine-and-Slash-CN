package com.robertx22.mine_and_slash.database.spells.spell_classes.fire;

import com.robertx22.mine_and_slash.database.spells.entities.summons.ArchonPetEntity;
import com.robertx22.mine_and_slash.database.spells.entities.summons.ZombiePetEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.ember_mage.SummonedArchonEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class SummonArchonSpell extends BaseSpell {

    private SummonArchonSpell() {
        super(
            new ImmutableSpellConfigs() {

                @Override
                public Masteries school() {
                    return Masteries.FIRE;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.AT_SIGHT;
                }

                @Override
                public SoundEvent sound() {
                    return SoundEvents.ENTITY_BLAZE_AMBIENT;
                }

                @Override
                public Elements element() {
                    return Elements.Fire;
                }
            }.summonsEntity(w -> new ArchonPetEntity(w))
                .setSwingArmOnCast());
    }

    public static SummonArchonSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.HEALTH_COST, 0, 0);
        c.set(SC.MANA_COST, 15, 24);
        c.set(SC.ENERGY_COST, 0, 0);
        c.set(SC.MAGIC_SHIELD_COST, 0, 0);
        c.set(SC.BASE_VALUE, 7, 16);
        c.set(SC.BONUS_HEALTH, 1.0F, 3.0F);
        c.set(SC.CAST_TIME_TICKS, 40, 40);
        c.set(SC.COOLDOWN_SECONDS, 180, 180);
        c.set(SC.CDR_EFFICIENCY, 0, 0);
        c.set(SC.DURATION_TICKS, 20 * 180, 20 * 180);
        c.set(SC.RADIUS, 3,6);
        c.set(SC.TICK_RATE, 60, 60);

        c.setMaxLevel(12);

        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(2, 5);
    }

    @Override
    public String GUID() {
        return "summon_archon";
    }

    @Override
    public void castExtra(SpellCastContext ctx) {
        PotionEffectUtils.reApplyToSelf(SummonedArchonEffect.INSTANCE, ctx.caster);
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Summon Attack"));
        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "" + TextFormatting.ITALIC + "Summons also triggers on-attack effects."));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Area, Duration, Entity, Summon"));

        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent(TextFormatting.GRAY + "This spell's cooldown is unaffected by"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "cooldown reduction."));
        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent("Summon an archon that aids in combat. The archon"));
        list.add(new StringTextComponent("periodically recovers nearby allies' mana. The"));
        list.add(new StringTextComponent("base value of the recovered mana is the same as"));
        list.add(new StringTextComponent("the summon's damage: "));
        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));

        return list;

    }

    @Override
    public Words getName() {
        return Words.SummonArchon;
    }

    private static class SingletonHolder {
        private static final SummonArchonSpell INSTANCE = new SummonArchonSpell();
    }
}
