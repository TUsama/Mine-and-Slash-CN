package com.robertx22.mine_and_slash.database.stats.mods.flat.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalHit;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class CriticalHitFlat extends StatMod {

    public CriticalHitFlat() {
    }

    @Override
    public float Min() {
        return 2F;
    }

    @Override
    public float Max() {
        return 4F;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return CriticalHit.getInstance();
    }

}
