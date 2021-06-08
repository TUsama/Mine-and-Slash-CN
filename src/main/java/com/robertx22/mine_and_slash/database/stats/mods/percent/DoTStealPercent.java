package com.robertx22.mine_and_slash.database.stats.mods.percent;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.resources.DoTSteal;
import com.robertx22.mine_and_slash.database.stats.types.resources.SpellSteal;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class DoTStealPercent extends StatMod {

    public DoTStealPercent() {
    }

    @Override
    public float Min() {
        return 15;
    }

    @Override
    public float Max() {
        return 30;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Percent;
    }

    @Override
    public Stat GetBaseStat() {
        return DoTSteal.getInstance();
    }

}
