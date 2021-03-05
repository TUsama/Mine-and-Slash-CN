package com.robertx22.mine_and_slash.database.stats.mods.flat.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.offense.conversions.PhysicalToNatureConversion;
import com.robertx22.mine_and_slash.database.stats.types.offense.conversions.PhysicalToWaterConversion;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class PhysToNatureConvFlat extends StatMod {

    @Override
    public float Min() {
        return 15;

    }

    @Override
    public float Max() {
        return 25;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return new PhysicalToNatureConversion();
    }

}

