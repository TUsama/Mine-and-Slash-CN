package com.robertx22.mine_and_slash.database.spells.synergies.nature;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.nature.RockSlideSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.storm.ThunderstormSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.base.Synergy;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class RockSlideEnhancedSynergy extends Synergy {

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Turn Rock Slide into a storm spell: "));

        return list;
    }

    @Override
    public int getMaxSpellLevelNormal() {
        return 1;
    }

    @Override
    public void alterSpell(PreCalcSpellConfigs c) {
        c.set(SC.MANA_COST, 12, 12);
        c.set(SC.CAST_TIME_TICKS, 30, 30);
        c.set(SC.COOLDOWN_SECONDS, 14, 14);
        c.set(SC.TICK_RATE, 4, 4);
        c.set(SC.RADIUS, 2F, 2F);
        c.set(SC.DURATION_TICKS, 200, 200);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        return c;
    }

    @Override
    public Place getSynergyPlace() {
        return Place.FIRST;
    }

    @Nullable
    @Override
    public IAbility getRequiredAbility() {
        return RockSlideSpell.getInstance();
    }

    @Override
    public String locNameForLangFile() {
        return "Stone Shrapnel";
    }
}
