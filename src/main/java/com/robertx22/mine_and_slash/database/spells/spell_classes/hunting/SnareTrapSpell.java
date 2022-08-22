package com.robertx22.mine_and_slash.database.spells.spell_classes.hunting;

import com.robertx22.mine_and_slash.database.spells.entities.proj.FireBombEntity;
import com.robertx22.mine_and_slash.database.spells.entities.proj.SnareTrapEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.potion_effects.druid.ThornArmorEffect;
import com.robertx22.mine_and_slash.potion_effects.ranger.SnareEffect;
import com.robertx22.mine_and_slash.potion_effects.ranger.WeakenEffect;
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

public class SnareTrapSpell extends BaseSpell {

    private SnareTrapSpell() {
        super(
            new ImmutableSpellConfigs() {

                @Override
                public Masteries school() {
                    return Masteries.HUNTING;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.PROJECTILE;
                }

                @Override
                public SoundEvent sound() {
                    return SoundEvents.BLOCK_TRIPWIRE_CLICK_ON;
                }

                @Override
                public Elements element() {
                    return Elements.Physical;
                }
            }.cooldownIfCanceled(true)
                .summonsEntity(w -> new SnareTrapEntity(w))
                .setSwingArmOnCast());
    }

    public static SnareTrapSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.HEALTH_COST, 0, 0);
        c.set(SC.MANA_COST, 11, 16);
        c.set(SC.ENERGY_COST, 0, 0);
        c.set(SC.MAGIC_SHIELD_COST, 0, 0);
        c.set(SC.BASE_VALUE, 4, 9);
        c.set(SC.ATTACK_SCALE_VALUE, 1.5F, 2.0F);
        c.set(SC.SHOOT_SPEED, 1.4F, 2F);
        c.set(SC.PROJECTILE_COUNT, 1, 1);
        c.set(SC.CAST_TIME_TICKS, 20, 20);
        c.set(SC.COOLDOWN_SECONDS, 14, 11);
        c.set(SC.DURATION_TICKS, 200, 200);
        c.set(SC.RADIUS, 2.0F, 3.5F);
        c.set(SC.TICK_RATE, 20, 20);
        c.set(SC.BONUS_HEALTH, 0, 0);

        c.setMaxLevel(4);

        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(7, 4);
    }

    @Override
    public String GUID() {
        return "snare_trap";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Spell"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Area, Debuff, Duration, Entity"));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent(TextFormatting.GRAY + "Converts Weapon DMG to Phys DMG."));

        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent(TextFormatting.GRAY + "Traps must be set on the ground for at least 1s"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "before activating."));
        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent("Throw out a trap that explodes, dealing"));
        list.add(new StringTextComponent("AOE physical damage and snaring enemies"));
        list.add(new StringTextComponent("in place: "));

        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent("Applies: "));
        list.addAll(SnareEffect.INSTANCE.GetTooltipStringWithNoExtraSpellInfo(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.SnareTrap;
    }

    private static class SingletonHolder {
        private static final SnareTrapSpell INSTANCE = new SnareTrapSpell();
    }
}
