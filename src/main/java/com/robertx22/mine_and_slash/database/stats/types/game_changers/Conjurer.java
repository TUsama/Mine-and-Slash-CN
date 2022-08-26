package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.mods.flat.misc.CooldownReductionFlat;
import com.robertx22.mine_and_slash.database.stats.types.offense.SpellDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.SummonDamage;
import com.robertx22.mine_and_slash.database.stats.types.resources.HealPower;
import com.robertx22.mine_and_slash.database.stats.types.spell_calc.IncreasedDurationStat;
import com.robertx22.mine_and_slash.database.stats.types.spell_calc.ReducedCooldownStat;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

import java.util.Arrays;
import java.util.List;

public class Conjurer extends BaseGameChangerTrait {

    private Conjurer() {
    }

    public static final Conjurer INSTANCE = new Conjurer();

    @Override
    public String locDescForLangFile() {
        return "Tame the dead, beasts, and all creation.";
    }

    @Override
    public String getIconPath() {
        return "game_changers/conjurer";
    }

    @Override
    public String locNameForLangFile() {
        return "Conjurer";
    }

    @Override
    public String GUID() {
        return "conjurer_trait";
    }

    @Override
    public List<ExactStatData> getExactStats() {

        return Arrays.asList(
            new ExactStatData(30, StatModTypes.Multi, SummonDamage.getInstance()),
            new ExactStatData(15, StatModTypes.Flat, IncreasedDurationStat.getInstance()),
            new ExactStatData(-50, StatModTypes.Flat, SpellDamage.getInstance())
        );
    }

}
