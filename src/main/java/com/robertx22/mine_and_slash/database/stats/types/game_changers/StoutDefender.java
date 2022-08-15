package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.types.defense.Armor;
import com.robertx22.mine_and_slash.database.stats.types.defense.BlockStrength;
import com.robertx22.mine_and_slash.database.stats.types.defense.DamageShield;
import com.robertx22.mine_and_slash.database.stats.types.defense.DodgeRating;
import com.robertx22.mine_and_slash.database.stats.types.offense.SpellDamage;
import com.robertx22.mine_and_slash.database.stats.types.resources.HealPower;
import com.robertx22.mine_and_slash.database.stats.types.resources.MagicShield;
import com.robertx22.mine_and_slash.database.stats.types.spell_calc.ReducedCooldownStat;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

import java.util.Arrays;
import java.util.List;

public class StoutDefender extends BaseGameChangerTrait {

    private StoutDefender() {
    }

    public static final StoutDefender INSTANCE = new StoutDefender();

    @Override
    public String locDescForLangFile() {
        return "Stand strong and protect those who are dear to you... or just yourself.";
    }

    @Override
    public String getIconPath() {
        return "game_changers/stout_defender";
    }

    @Override
    public String locNameForLangFile() {
        return "Stout Defender";
    }

    @Override
    public String GUID() {
        return "stout_defender_trait";
    }

    @Override
    public List<ExactStatData> getExactStats() {

        return Arrays.asList(
            new ExactStatData(25, StatModTypes.Percent, BlockStrength.INSTANCE),
                new ExactStatData(10, StatModTypes.Flat, DamageShield.getInstance()),
            new ExactStatData(-30, StatModTypes.Multi, MagicShield.getInstance()),
            new ExactStatData(-30, StatModTypes.Multi, Armor.getInstance()),
            new ExactStatData(-30, StatModTypes.Multi, DodgeRating.getInstance())
        );
    }

}
