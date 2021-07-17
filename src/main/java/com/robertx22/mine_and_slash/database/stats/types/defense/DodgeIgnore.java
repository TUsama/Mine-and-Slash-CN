package com.robertx22.mine_and_slash.database.stats.types.defense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.offense.ArmorPeneEffect;
import com.robertx22.mine_and_slash.database.stats.effects.offense.DodgeIgnoreEffect;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class DodgeIgnore extends Stat implements IStatEffects {

    public static DodgeIgnore getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public StatGroup statGroup() {
        return StatGroup.Penetration;
    }

    @Override
    public String getIcon() {
        return "\u25BC";
    }

    @Override
    public String getIconPath() {
        return "dodge";
    }

    @Override
    public String locDescForLangFile() {
        return "Ignores X dodge.";
    }

    public static String GUID = "dodge_ignore";

    private DodgeIgnore() {
        this.minimumValue = 0;
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public Elements getElement() {
        return Elements.Elemental;
    }

    @Override
    public boolean IsPercent() {
        return true;
    }

    @Override
    public StatScaling getScaling() {
        return StatScaling.SLOW_SCALING;
    }

    @Override
    public IStatEffect getEffect() {
        return new DodgeIgnoreEffect();
    }

    @Override
    public String locNameForLangFile() {
        return "Dodge Ignore";
    }

    private static class SingletonHolder {
        private static final DodgeIgnore INSTANCE = new DodgeIgnore();
    }
}

