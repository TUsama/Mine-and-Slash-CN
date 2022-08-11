package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.types.offense.SpellDamage;
import com.robertx22.mine_and_slash.database.stats.types.resources.HealPower;
import com.robertx22.mine_and_slash.database.stats.types.resources.HealthRegen;
import com.robertx22.mine_and_slash.database.stats.types.resources.Lifesteal;
import com.robertx22.mine_and_slash.database.stats.types.resources.SpellSteal;
import com.robertx22.mine_and_slash.database.stats.types.spell_calc.ReducedCooldownStat;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

import java.util.Arrays;
import java.util.List;

public class Vampire extends BaseGameChangerTrait {

    private Vampire() {
    }

    public static final Vampire INSTANCE = new Vampire();

    @Override
    public String locDescForLangFile() {
        return "Satiate your desires.";
    }

    @Override
    public String getIconPath() {
        return "game_changers/vampire";
    }

    @Override
    public String locNameForLangFile() {
        return "Vampire";
    }

    @Override
    public String GUID() {
        return "vampire_trait";
    }

    @Override
    public List<ExactStatData> getExactStats() {

        return Arrays.asList(
            new ExactStatData(4, StatModTypes.Flat, Lifesteal.getInstance()),
            new ExactStatData(2, StatModTypes.Flat, SpellSteal.getInstance()),
                new ExactStatData(50, StatModTypes.Multi, Lifesteal.getInstance()),
                new ExactStatData(25, StatModTypes.Multi, SpellSteal.getInstance()),
            new ExactStatData(-1000, StatModTypes.Multi, HealthRegen.getInstance())
        );
    }

}
