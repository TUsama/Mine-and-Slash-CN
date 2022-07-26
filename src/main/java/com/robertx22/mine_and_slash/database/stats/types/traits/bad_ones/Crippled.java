package com.robertx22.mine_and_slash.database.stats.types.traits.bad_ones;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.Trait;
import com.robertx22.mine_and_slash.database.stats.mods.multi.offence.PhysicalDamageMulti;
import com.robertx22.mine_and_slash.database.stats.mods.percent.*;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsOtherStats;

import java.util.Arrays;
import java.util.List;

public class Crippled extends Trait implements IAffectsOtherStats {

    public static String GUID = "crippled";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {

        return Arrays.asList(new ArmorPercent().size(StatMod.Size.HALF_LESS),
                new DodgeRatingPercent().size(StatMod.Size.HALF_LESS),
                new MagicShieldPercent().size(StatMod.Size.HALF_LESS));

    }

    @Override
    public int Weight() {
        return 4000;
    }

    @Override
    public String locNameForLangFile() {
        return "Crippled";
    }
}
