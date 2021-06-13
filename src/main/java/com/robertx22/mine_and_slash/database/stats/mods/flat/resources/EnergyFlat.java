package com.robertx22.mine_and_slash.database.stats.mods.flat.resources;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.resources.Energy;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class EnergyFlat extends StatMod {

    public EnergyFlat() {
    }

    @Override
    public float Min() {
        return 4;
    }

    @Override
    public float Max() {
        return 16;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return Energy.getInstance();
    }

}
