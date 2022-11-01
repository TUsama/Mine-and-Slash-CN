package com.robertx22.mine_and_slash.database.stats.mods.flat.misc;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.misc.SlowOnDamageTaken;
import com.robertx22.mine_and_slash.database.stats.types.mob_spells.Speedy;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class SLowOnDamageTakenFlat extends StatMod {

    @Override
    public float Min() {
        return 100;

    }

    @Override
    public float Max() {
        return 100;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return new SlowOnDamageTaken();
    }

}

