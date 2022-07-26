package com.robertx22.mine_and_slash.database.stats.types.traits.good;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.Trait;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.ArmorPenetrationFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.CriticalHitPercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.PhysicalDamagePercent;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsOtherStats;

import java.util.Arrays;
import java.util.List;

public class Spiky extends Trait implements IAffectsOtherStats {

    public static String GUID = "spiky";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new PhysicalDamagePercent().size(StatMod.Size.HALF), new ArmorPenetrationFlat().size(StatMod.Size.DOUBLE));

    }

    @Override
    public int Weight() {
        return 1000;
    }

    @Override
    public String locNameForLangFile() {
        return "Spiky";
    }
}
