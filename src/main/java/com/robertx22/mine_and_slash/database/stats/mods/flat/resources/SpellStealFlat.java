package com.robertx22.mine_and_slash.database.stats.mods.flat.resources;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.resources.Lifesteal;
import com.robertx22.mine_and_slash.database.stats.types.resources.SpellSteal;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class SpellStealFlat extends StatMod {

    public SpellStealFlat() {
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

    @Override
    public Stat GetBaseStat() {
        return SpellSteal.getInstance();
    }

}
