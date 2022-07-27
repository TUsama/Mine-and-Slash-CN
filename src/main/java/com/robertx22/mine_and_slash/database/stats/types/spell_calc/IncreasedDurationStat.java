package com.robertx22.mine_and_slash.database.stats.types.spell_calc;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.spell_calc.IncreasedDurationEffect;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class IncreasedDurationStat extends Stat implements IStatEffects {

    private IncreasedDurationStat() {
        this.maximumValue = 75;
    }

    public static IncreasedDurationStat getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public boolean IsPercent() {
        return true;
    }

    @Override
    public String getIconPath() {
        return "increased_duration";
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
        return "Increases the duration of spells and effects.";
    }

    @Override
    public String locNameForLangFile() {
        return "Increased Duration";
    }

    public static String GUID = "increased_duration";

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
        return new IncreasedDurationEffect();
    }

    private static class SingletonHolder {
        private static final IncreasedDurationStat INSTANCE = new IncreasedDurationStat();
    }
}
