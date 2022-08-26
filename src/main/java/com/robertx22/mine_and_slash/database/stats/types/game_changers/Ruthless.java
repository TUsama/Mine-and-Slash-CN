package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.types.defense.Armor;
import com.robertx22.mine_and_slash.database.stats.types.elementals.all_damage.AllDotDmg;
import com.robertx22.mine_and_slash.database.stats.types.offense.SpellDamage;
import com.robertx22.mine_and_slash.database.stats.types.resources.Health;
import com.robertx22.mine_and_slash.database.stats.types.spell_calc.IncreasedDurationStat;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

import java.util.Arrays;
import java.util.List;

public class Ruthless extends BaseGameChangerTrait {

    private Ruthless() {
    }

    public static final Ruthless INSTANCE = new Ruthless();

    @Override
    public String locDescForLangFile() {
        return "Torment your foes.";
    }

    @Override
    public String getIconPath() {
        return "game_changers/ruthless";
    }

    @Override
    public String locNameForLangFile() {
        return "Ruthless";
    }

    @Override
    public String GUID() {
        return "ruthless_trait";
    }

    @Override
    public List<ExactStatData> getExactStats() {

        return Arrays.asList(
            new ExactStatData(25, StatModTypes.Flat, new AllDotDmg()),
            new ExactStatData(10, StatModTypes.Flat, IncreasedDurationStat.getInstance()),
            new ExactStatData(-25, StatModTypes.Flat, SpellDamage.getInstance())
        );
    }

}
