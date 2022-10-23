package com.robertx22.mine_and_slash.database.stats.mods.flat.corestats;

import com.robertx22.mine_and_slash.database.stats.types.core_stats.base.BaseCoreStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class CoreStatFlat extends BaseCoreStatFlat {

    public CoreStatFlat(BaseCoreStat stat) {
        super(stat);
    }

    @Override
    public BaseCoreStatFlat newGeneratedInstance(BaseCoreStat stat) {
        return new CoreStatFlat(stat);
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

}
