package com.robertx22.mine_and_slash.database.stats.mods.flat.misc;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.misc.BonusExp;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class BonusExpFlat extends StatMod {
    @Override
    public Stat GetBaseStat() {
        return BonusExp.getInstance();
    }

    @Override
    public float Min() {
        return 14;
    }

    @Override
    public float Max() {
        return 18;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Flat;
    }

}
