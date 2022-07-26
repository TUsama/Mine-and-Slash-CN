package com.robertx22.mine_and_slash.database.stats.types.traits.major_arcana;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.CoreStatFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.multi.resources.ManaMulti;
import com.robertx22.mine_and_slash.database.stats.mods.percent.MagicShieldPercent;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Intelligence;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class TheMagician extends BaseMajorArcana {

    public static final String GUID = "the_magician";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(
                new CoreStatFlat(Intelligence.INSTANCE), new MagicShieldPercent(),
                new ElementalSpellDamageFlat(Elements.Thunder).size(StatMod.Size.HALF_MORE)
        );
    }

    @Override
    public String locNameForLangFile() {
        return "The Magician";
    }
}
