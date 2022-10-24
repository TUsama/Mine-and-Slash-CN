package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.types.resources.HealthRegen;
import com.robertx22.mine_and_slash.database.stats.types.resources.Lifesteal;
import com.robertx22.mine_and_slash.database.stats.types.resources.SpellSteal;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

import java.util.Arrays;
import java.util.List;

public class Permanence extends BaseGameChangerTrait {

    private Permanence() {
    }

    public static final Permanence INSTANCE = new Permanence();

    @Override
    public String locDescForLangFile() {
        return "Prevents EXP Gain.";
    }

    @Override
    public String getIconPath() {
        return "game_changers/permanence";
    }

    @Override
    public String locNameForLangFile() {
        return "Permanence";
    }

    @Override
    public String GUID() {
        return "permanence_trait";
    }

    @Override
    public List<ExactStatData> getExactStats() {

        return Arrays.asList(
        );
    }

}
