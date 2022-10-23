package com.robertx22.mine_and_slash.database.stats.mods.flat.misc;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.spell_calc.IncreasedDurationStat;
import com.robertx22.mine_and_slash.database.stats.types.spell_calc.IncreasedProjSpeedStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class IncreasedProjSpeedFlat extends StatMod {

    @Override
    public Stat GetBaseStat() {
        return IncreasedProjSpeedStat.getInstance();
    }

    @Override
    public float Min() {
        return 8;
    }

    @Override
    public float Max() {
        return 12;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Flat;
    }
}

