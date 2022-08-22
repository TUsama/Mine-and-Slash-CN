package com.robertx22.mine_and_slash.database.stats.types.defense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.offense.ArmorPeneEffect;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class ArmorPenetration extends Stat implements IStatEffects {

    public static ArmorPenetration getInstance() {
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
        return "pene/physical";
    }

    @Override
    public String locDescForLangFile() {
        return "Ignores x armor";
    }

    public static String GUID = "armor_penetration";

    private ArmorPenetration() {
        this.minimumValue = 0;
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public Elements getElement() {
        return Elements.Physical;
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
        return new ArmorPeneEffect();
    }

    @Override
    public String locNameForLangFile() {
        return "Armor Penetration";
    }

    private static class SingletonHolder {
        private static final ArmorPenetration INSTANCE = new ArmorPenetration();
    }
}

