package com.robertx22.mine_and_slash.database.stats.mods.flat.defense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.defense.DamageTaken;
import com.robertx22.mine_and_slash.database.stats.types.offense.IncreaseDamage;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class DamageTakenFlat extends StatMod {

    public DamageTakenFlat() {
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

    @Override
    public Stat GetBaseStat() {
        return new DamageTaken();
    }

}
