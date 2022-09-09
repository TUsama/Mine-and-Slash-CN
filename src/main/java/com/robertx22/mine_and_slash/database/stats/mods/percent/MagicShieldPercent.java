package com.robertx22.mine_and_slash.database.stats.mods.percent;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.resources.MagicShield;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class MagicShieldPercent extends StatMod {

    public MagicShieldPercent() {
    }

    @Override
    public float Min() {
        return 4;
    }

    @Override
    public float Max() {
        return 6;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Percent;
    }

    @Override
    public Stat GetBaseStat() {
        return MagicShield.getInstance();
    }
}