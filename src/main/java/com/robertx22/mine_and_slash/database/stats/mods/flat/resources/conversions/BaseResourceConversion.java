package com.robertx22.mine_and_slash.database.stats.mods.flat.resources.conversions;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public abstract class BaseResourceConversion extends StatMod {

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
