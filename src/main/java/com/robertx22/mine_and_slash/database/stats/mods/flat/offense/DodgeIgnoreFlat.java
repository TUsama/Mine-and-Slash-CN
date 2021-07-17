package com.robertx22.mine_and_slash.database.stats.mods.flat.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.effects.offense.DodgeIgnoreEffect;
import com.robertx22.mine_and_slash.database.stats.types.defense.ArmorPenetration;
import com.robertx22.mine_and_slash.database.stats.types.defense.DodgeIgnore;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class DodgeIgnoreFlat extends StatMod {

    @Override
    public float Min() {
        return 2;
    }

    @Override
    public float Max() {
        return 6;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return DodgeIgnore.getInstance();
    }

}
