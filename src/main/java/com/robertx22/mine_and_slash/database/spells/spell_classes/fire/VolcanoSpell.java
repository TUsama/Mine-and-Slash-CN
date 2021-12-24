package com.robertx22.mine_and_slash.database.spells.spell_classes.fire;

import com.robertx22.mine_and_slash.database.spells.entities.cloud.VolcanoEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModSounds;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class VolcanoSpell extends BaseSpell {

    private VolcanoSpell() {
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
                    return ModSounds.FIREBALL.get();
                }

                @Override
                public Elements element() {
                    return Elements.Fire;
                }
            }.summonsEntity(world -> new VolcanoEntity(world))
                .setSwingArmOnCast());
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.HEALTH_COST, 0, 0);
        c.set(SC.MANA_COST, 22, 34);
        c.set(SC.ENERGY_COST, 0, 0);
        c.set(SC.MAGIC_SHIELD_COST, 0, 0);
        c.set(SC.BASE_VALUE, 6, 17);
        c.set(SC.ATTACK_SCALE_VALUE, 0.3F, 0.55F);
        c.set(SC.CAST_TIME_TICKS, 50, 20);
        c.set(SC.COOLDOWN_SECONDS, 28, 25);
        c.set(SC.RADIUS, 3.0F, 6.0F);
        c.set(SC.DURATION_TICKS, 200, 200);
        c.set(SC.TICK_RATE, 20, 15);
        c.set(SC.BONUS_HEALTH, 0, 0);

        c.setMaxLevel(8);

        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(4, 5);
    }

    public static VolcanoSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "volcano";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Spell"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Area, Duration, Storm"));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent(TextFormatting.GRAY + "Converts Weapon DMG to Fire."));
        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent("Summons an erupting volcano: "));

        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));

        return list;

    }

    @Override
    public Words getName() {
        return Words.Volcano;
    }

    private static class SingletonHolder {
        private static final VolcanoSpell INSTANCE = new VolcanoSpell();
    }
}
