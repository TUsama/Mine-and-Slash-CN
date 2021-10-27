package com.robertx22.mine_and_slash.database.stats.types.traits.major_arcana;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.SummonDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.AllElementalDamageMulti;
import com.robertx22.mine_and_slash.database.stats.mods.multi.offence.PhysicalDamageMulti;
import com.robertx22.mine_and_slash.database.stats.mods.percent.HealthPercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.PhysicalDamagePercent;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class Death extends BaseMajorArcana {

    public static final String GUID = "death";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new PhysicalDamagePercent(), new SummonDamageFlat(),
                new HealthPercent().size(StatMod.Size.HALF_LESS));
    }

    @Override
    public String locNameForLangFile() {
        return "Death";
    }
}
