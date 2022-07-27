package com.robertx22.mine_and_slash.database.stats.types.spell_calc;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.spell_calc.IncreasedAreaEffect;
import com.robertx22.mine_and_slash.database.stats.effects.spell_calc.IncreasedProjSpeedEffect;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class IncreasedAreaStat extends Stat implements IStatEffects {

    private IncreasedAreaStat() {
        this.maximumValue = 75;
    }

    public static IncreasedAreaStat getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public boolean IsPercent() {
        return true;
    }

    @Override
    public String getIconPath() {
        return "increased_area";
    }

    @Override
    public Elements getElement() {
        return null;
    }

    @Override
    public StatScaling getScaling() {
        return StatScaling.NONE;
    }

    @Override
    public String locDescForLangFile() {
        return "Increases the radius of your spells.";
    }

    @Override
    public String locNameForLangFile() {
        return "Increased Area of Effect";
    }

    public static String GUID = "increased_area";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public StatGroup statGroup() {
        return StatGroup.SpellDamage;
    }

    @Override
    public IStatEffect getEffect() {
        return new IncreasedAreaEffect();
    }

    private static class SingletonHolder {
        private static final IncreasedAreaStat INSTANCE = new IncreasedAreaStat();
    }
}
