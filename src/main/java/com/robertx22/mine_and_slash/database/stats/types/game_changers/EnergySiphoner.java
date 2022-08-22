package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.types.elementals.all_damage.AllDotDmg;
import com.robertx22.mine_and_slash.database.stats.types.offense.SpellDamage;
import com.robertx22.mine_and_slash.database.stats.types.resources.*;
import com.robertx22.mine_and_slash.database.stats.types.spell_calc.ReducedCooldownStat;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

import java.util.Arrays;
import java.util.List;

public class EnergySiphoner extends BaseGameChangerTrait {

    private EnergySiphoner() {
    }

    public static final EnergySiphoner INSTANCE = new EnergySiphoner();

    @Override
    public String locDescForLangFile() {
        return "Draw from power from your foes.";
    }

    @Override
    public String getIconPath() {
        return "game_changers/warlock";
    }

    @Override
    public String locNameForLangFile() {
        return "Warlock";
    }

    @Override
    public String GUID() {
        return "warlock_trait";
    }

    @Override
    public List<ExactStatData> getExactStats() {

        return Arrays.asList(
            new ExactStatData(8, StatModTypes.Flat, DoTSteal.getInstance()),
            new ExactStatData(6, StatModTypes.Flat, new AllDotDmg()),
            new ExactStatData(-25, StatModTypes.Multi, Health.getInstance()),
            new ExactStatData(-25, StatModTypes.Multi, MagicShield.getInstance())
        );
    }

}
