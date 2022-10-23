package com.robertx22.mine_and_slash.database.stats.mods.multi.defense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.defense.DodgeRating;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class DodgeRatingMulti extends StatMod {

    @Override
    public float Min() {
        return 4;
    }

    @Override
    public float Max() {
        return 6;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Multi;
    }

    @Override
    public Stat GetBaseStat() {
        return DodgeRating.getInstance();
    }

}
