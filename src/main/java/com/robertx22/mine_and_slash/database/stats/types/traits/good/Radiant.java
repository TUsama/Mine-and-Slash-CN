package com.robertx22.mine_and_slash.database.stats.types.traits.good;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.Trait;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.ArmorPenetrationFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.SpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalPeneFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.PhysicalDamagePercent;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsOtherStats;

import java.util.Arrays;
import java.util.List;

public class Radiant extends Trait implements IAffectsOtherStats {

    public static String GUID = "radiant";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new SpellDamageFlat(), new ElementalPeneFlat(Elements.Elemental).size(StatMod.Size.HALF_MORE));

    }

    @Override
    public int Weight() {
        return 1000;
    }

    @Override
    public String locNameForLangFile() {
        return "Radiant";
    }
}
