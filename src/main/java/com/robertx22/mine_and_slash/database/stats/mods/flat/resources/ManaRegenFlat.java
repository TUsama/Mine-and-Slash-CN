package com.robertx22.mine_and_slash.database.stats.mods.flat.resources;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.resources.ManaRegen;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class ManaRegenFlat extends StatMod {

    public ManaRegenFlat() {
    }

    @Override
    public float Min() {
        return 0.5F;
    }

    @Override
    public float Max() {
        return 1.5F;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return ManaRegen.getInstance();
    }

}
