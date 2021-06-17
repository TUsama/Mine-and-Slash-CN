package com.robertx22.mine_and_slash.database.stats.types.elementals.all_damage;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.offense.AllDoTDmgEffect;
import com.robertx22.mine_and_slash.database.stats.effects.offense.AllEleDmgEffect;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class AllDotDmg extends Stat implements IStatEffects {

    public static String GUID = "all_dot_dmg";

    @Override
    public String locDescForLangFile() {
        return "Increases all damage over time effects (Burn, Bleed, Thorns, etc.).";
    }

    public AllDotDmg() {

    }

    @Override
    public StatGroup statGroup() {
        return StatGroup.Damage;
    }

    @Override
    public String getIconPath() {
        return GUID;
    }

    @Override
    public StatScaling getScaling() {
        return StatScaling.NONE;
    }

    @Override
    public boolean IsPercent() {
        return true;
    }

    @Override
    public IStatEffect getEffect() {
        return new AllDoTDmgEffect();
    }

    @Override
    public String locNameForLangFile() {
        return "All DoT Damage";
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public Elements getElement() {
        return Elements.Physical;
    }
}
