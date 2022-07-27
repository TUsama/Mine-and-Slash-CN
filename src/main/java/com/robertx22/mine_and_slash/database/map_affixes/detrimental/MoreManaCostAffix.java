package com.robertx22.mine_and_slash.database.map_affixes.detrimental;

import com.robertx22.mine_and_slash.database.map_affixes.DetrimentalMapAffix;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.misc.CooldownReductionFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.misc.ReducedManaCostFlat;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;

import java.util.Arrays;
import java.util.List;

public class MoreManaCostAffix extends DetrimentalMapAffix {

    @Override
    public String GUID() {
        return "more_manacost_affix";
    }

    @Override
    public List<StatModData> Stats(int percent) {
        return Arrays.asList(StatModData.Load(new ReducedManaCostFlat().size(StatMod.Size.TRIPLE_LESS), percent));

    }

    @Override
    public float lootMulti() {
        return 1.05F;
    }

}
