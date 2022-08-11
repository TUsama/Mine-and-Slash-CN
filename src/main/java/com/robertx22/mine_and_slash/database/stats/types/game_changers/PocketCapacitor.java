package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.types.resources.Lifesteal;
import com.robertx22.mine_and_slash.database.stats.types.resources.MagicShield;
import com.robertx22.mine_and_slash.database.stats.types.resources.MagicShieldRegen;
import com.robertx22.mine_and_slash.database.stats.types.resources.SpellSteal;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

import java.util.Arrays;
import java.util.List;

public class PocketCapacitor extends BaseGameChangerTrait {

    private PocketCapacitor() {
    }

    public static final PocketCapacitor INSTANCE = new PocketCapacitor();

    @Override
    public String locDescForLangFile() {
        return "A portable capacitor? How much does it cost?";
    }

    @Override
    public String getIconPath() {
        return "game_changers/pocket_capacitor";
    }

    @Override
    public String locNameForLangFile() {
        return "Pocket Capacitor";
    }

    @Override
    public String GUID() {
        return "pocket_capacitor_trait";
    }

    @Override
    public List<ExactStatData> getExactStats() {

        return Arrays.asList(
            new ExactStatData(80, StatModTypes.Multi, MagicShieldRegen.getInstance()),
            new ExactStatData(-1000, StatModTypes.Multi, Lifesteal.getInstance()),
            new ExactStatData(-1000, StatModTypes.Multi, SpellSteal.getInstance())
        );
    }

}
