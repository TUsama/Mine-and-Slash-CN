package com.robertx22.mine_and_slash.database.stats.mods.flat.misc;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.effects.spell_calc.IncreasedAreaEffect;
import com.robertx22.mine_and_slash.database.stats.types.spell_calc.IncreasedAreaStat;
import com.robertx22.mine_and_slash.database.stats.types.spell_calc.IncreasedDurationStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class IncreasedAreaFlat extends StatMod {

    @Override
    public Stat GetBaseStat() {
        return IncreasedAreaStat.getInstance();
    }

    @Override
    public float Min() {
        return 1;
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

