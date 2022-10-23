package com.robertx22.mine_and_slash.database.stats.mods.flat.defense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.defense.DodgeRating;
import com.robertx22.mine_and_slash.database.stats.types.defense.SpellDodge;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class SpellDodgeFlat extends StatMod {

    public SpellDodgeFlat() {
    }

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
        return new SpellDodge();
    }

}
