package com.robertx22.mine_and_slash.database.stats.mods.flat.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.offense.SpellDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.SummonDamage;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

public class SummonDamageFlat extends StatMod {

    @Override
    public float Min() {
        return 3;
    }

    @Override
    public float Max() {
        return 5;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return SummonDamage.getInstance();
    }

    @Override
    public int getRarityRank() {
        return IRarity.Common;
    }

}
