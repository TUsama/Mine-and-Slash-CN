package com.robertx22.mine_and_slash.database.stats.types.traits.ele_lords;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.Trait;
import com.robertx22.mine_and_slash.database.stats.mods.flat.misc.FasterCastRateFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealPowerFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.AllElementalDamageMulti;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsOtherStats;

import java.util.Arrays;
import java.util.List;

public class LordOfBlizzardsTrait extends Trait implements IAffectsOtherStats {

    @Override
    public List<StatMod> getStats() {

        return Arrays.asList(new HealPowerFlat().size(StatMod.Size.HALF_MORE), new FasterCastRateFlat().size(StatMod.Size.HALF_MORE));

    }

    @Override
    public String GUID() {
        return "lord_of_blizzards";
    }

    @Override
    public String locNameForLangFile() {
        return "Lord of Blizzards";
    }
}
