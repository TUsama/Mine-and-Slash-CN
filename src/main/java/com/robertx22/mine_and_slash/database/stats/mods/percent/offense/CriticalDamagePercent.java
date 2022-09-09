package com.robertx22.mine_and_slash.database.stats.mods.percent.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalDamage;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class CriticalDamagePercent extends StatMod {

    public CriticalDamagePercent() {
    }

    @Override
    public float Min() {
        return 8;

    }

    @Override
    public float Max() {
        return 16;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Percent;
    }

    @Override
    public Stat GetBaseStat() {
        return CriticalDamage.getInstance();
    }

}
