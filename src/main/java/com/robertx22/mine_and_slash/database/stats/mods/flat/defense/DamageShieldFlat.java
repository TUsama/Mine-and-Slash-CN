package com.robertx22.mine_and_slash.database.stats.mods.flat.defense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.defense.DamageShield;
import com.robertx22.mine_and_slash.database.stats.types.defense.SpellDodge;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class DamageShieldFlat extends StatMod {

    public DamageShieldFlat() {
    }

    @Override
    public float Min() {
        return 2.5F;
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
        return new DamageShield();
    }

}
