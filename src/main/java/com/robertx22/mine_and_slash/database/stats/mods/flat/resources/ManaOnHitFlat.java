package com.robertx22.mine_and_slash.database.stats.mods.flat.resources;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.resources.ManaOnHit;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class ManaOnHitFlat extends StatMod {

    public ManaOnHitFlat() {
    }

    @Override
    public float Min() {
        return 2;
    }

    @Override
    public float Max() {
        return 3;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return ManaOnHit.getInstance();
    }

}