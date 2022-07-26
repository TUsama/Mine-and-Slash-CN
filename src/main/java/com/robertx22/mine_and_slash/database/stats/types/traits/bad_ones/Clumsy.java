package com.robertx22.mine_and_slash.database.stats.types.traits.bad_ones;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.Trait;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.SpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.ManaRegenPercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.PhysicalDamagePercent;
import com.robertx22.mine_and_slash.database.stats.types.generated.WeaponDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.SpellDamage;

import java.util.Arrays;
import java.util.List;

public class Clumsy extends Trait {

    public static String GUID = "clumsy";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList(new PhysicalDamagePercent().size(StatMod.Size.HALF_LESS));

    }

    @Override
    public int Weight() {
        return 750;
    }

    @Override
    public String locNameForLangFile() {
        return "Clumsy";
    }
}
