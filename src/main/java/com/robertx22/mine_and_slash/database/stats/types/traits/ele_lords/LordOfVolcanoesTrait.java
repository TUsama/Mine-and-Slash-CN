package com.robertx22.mine_and_slash.database.stats.types.traits.ele_lords;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.Trait;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.PhysToFireConvFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.AllElementalDamageMulti;
import com.robertx22.mine_and_slash.database.stats.mods.percent.ElementalAttackDamagePercent;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsOtherStats;

import java.util.Arrays;
import java.util.List;

public class LordOfVolcanoesTrait extends Trait implements IAffectsOtherStats {

    @Override
    public List<StatMod> getStats() {

        return Arrays.asList(new PhysToFireConvFlat().size(StatMod.Size.DOUBLE),
                new ElementalAttackDamagePercent(Elements.Fire).size(StatMod.Size.HALF_MORE));

    }

    @Override
    public int Weight() {
        return 1000;
    }

    @Override
    public String GUID() {
        return "lord_of_volcanoes";
    }

    @Override
    public String locNameForLangFile() {
        return "Lord of Volcanoes";
    }
}
