package com.robertx22.mine_and_slash.database.stats.mods.flat.elemental;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.elementals.all_damage.AllDotDmg;
import com.robertx22.mine_and_slash.database.stats.types.elementals.all_damage.AllEleDmg;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class AllDotDmgFlat extends StatMod {

    @Override
    public Stat GetBaseStat() {
        return new AllDotDmg();
    }

    @Override
    public float Min() {
        return 6;
    }

    @Override
    public float Max() {
        return 15;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Flat;
    }

}
