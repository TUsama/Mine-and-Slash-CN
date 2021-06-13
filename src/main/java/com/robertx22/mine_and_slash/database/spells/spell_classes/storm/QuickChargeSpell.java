package com.robertx22.mine_and_slash.database.spells.spell_classes.storm;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.potion_effects.divine.TrickeryEffect;
import com.robertx22.mine_and_slash.potion_effects.druid.RegenerateEffect;
import com.robertx22.mine_and_slash.potion_effects.shaman.QuickChargeEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
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

public class QuickChargeSpell extends BaseSpell {

    private QuickChargeSpell() {
        super(
            new ImmutableSpellConfigs() {

                @Override
                public Masteries school() {
                    return Masteries.STORM;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.AOE_ALLIES_EFFECT;
                }

                @Override
                public SoundEvent sound() {
                    return SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP;
                }

                @Override
                public Elements element() {
                    return Elements.Thunder;
                }
            }.addsEffect(QuickChargeEffect.INSTANCE).setSwingArmOnCast());
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.MANA_COST, 14, 18);
        c.set(SC.ENERGY_COST, 0, 0);
        c.set(SC.MAGIC_SHIELD_COST, 0, 0);
        c.set(SC.CAST_TIME_TICKS, 60, 180);
        c.set(SC.COOLDOWN_SECONDS, 10, 10);
        c.set(SC.DURATION_TICKS, 180 * 20, 360 * 20);
        c.set(SC.RADIUS, 3, 6);

        c.setMaxLevel(8);

        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(6, 3);
    }

    public static QuickChargeSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "quick_charge";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Spell"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Area, Buff"));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent("Applies buff to nearby allies: "));
        list.addAll(QuickChargeEffect.INSTANCE.GetTooltipStringWithNoExtraSpellInfo(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.QuickCharge;
    }

    private static class SingletonHolder {
        private static final QuickChargeSpell INSTANCE = new QuickChargeSpell();
    }
    /*
    @Override
    public void castExtra(SpellCastContext ctx) {

        try {
            LivingEntity en = ctx.caster;
            en.getCapability(PlayerSpellCap.Data)
                    .ifPresent(x -> x.getCastingData()
                            .onTimePass((PlayerEntity) en, x, 50000));

        } catch (Exception e) {
            e.printStackTrace();


        SoundUtils.playSound(ctx.caster, SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, 1, 0.8F);

    }*/
}
