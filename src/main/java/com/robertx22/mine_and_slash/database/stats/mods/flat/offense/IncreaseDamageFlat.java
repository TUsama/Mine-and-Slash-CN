package com.robertx22.mine_and_slash.database.stats.mods.flat.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.defense.DamageShield;
import com.robertx22.mine_and_slash.database.stats.types.offense.IncreaseDamage;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class IncreaseDamageFlat extends StatMod {

    public IncreaseDamageFlat() {
    }

    @Override
    public float Min() {
        return 16;
    }

    @Override
    public float Max() {
        return 24;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return new IncreaseDamage();
    }

}
