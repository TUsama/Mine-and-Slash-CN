package com.robertx22.mine_and_slash.database.stats.types.traits.bad_ones;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.Trait;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalHitFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.CriticalDamagePercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.CriticalHitPercent;

import java.util.Arrays;
import java.util.List;

public class Cursed extends Trait {

    public static String GUID = "cursed";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new CriticalDamagePercent().size(StatMod.Size.HALF_LESS));
    }

    @Override
    public String locNameForLangFile() {
        return "Cursed";
    }
}
