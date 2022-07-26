package com.robertx22.mine_and_slash.database.stats.types.traits.major_arcana;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.CoreStatFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalHitFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.WeaponDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.PhysicalDamagePercent;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Dexterity;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Stamina;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;

import java.util.Arrays;
import java.util.List;

public class TheSniper extends BaseMajorArcana {

    public static final String GUID = "the_sniper";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(
                new CoreStatFlat(Dexterity.INSTANCE),
                new CoreStatFlat(Stamina.INSTANCE),
                new WeaponDamageFlat(WeaponTypes.Bow).size(StatMod.Size.DOUBLE),
                new WeaponDamageFlat(WeaponTypes.CrossBow).size(StatMod.Size.DOUBLE)
        );
    }

    @Override
    public String locNameForLangFile() {
        return "The Sniper";
    }
}
