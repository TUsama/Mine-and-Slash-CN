package com.robertx22.mine_and_slash.database.stats.types.traits.good;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.Trait;
import com.robertx22.mine_and_slash.database.stats.mods.percent.HealthPercent;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsOtherStats;

import java.util.Arrays;
import java.util.List;

public class Golem extends Trait implements IAffectsOtherStats {

    public static String GUID = "golem";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new HealthPercent().size(StatMod.Size.TRIPLE));

    }

    @Override
    public int Weight() {
        return 1000;
    }

    @Override
    public String locNameForLangFile() {
        return "Golem";
    }
}
