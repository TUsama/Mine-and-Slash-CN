package com.robertx22.mine_and_slash.database.stats.types.traits.good;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.Trait;
import com.robertx22.mine_and_slash.database.stats.mods.multi.defense.ArmorMulti;
import com.robertx22.mine_and_slash.database.stats.mods.percent.ArmorPercent;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsOtherStats;

import java.util.Arrays;
import java.util.List;

public class Armored extends Trait implements IAffectsOtherStats {

    public static String GUID = "armored";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new ArmorPercent().size(StatMod.Size.DOUBLE));

    }

    @Override
    public String locNameForLangFile() {
        return "Armored";
    }
}
