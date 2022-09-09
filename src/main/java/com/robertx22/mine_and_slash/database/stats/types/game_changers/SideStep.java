package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.types.defense.Armor;
import com.robertx22.mine_and_slash.database.stats.types.defense.BlockStrength;
import com.robertx22.mine_and_slash.database.stats.types.defense.DodgeRating;
import com.robertx22.mine_and_slash.database.stats.types.defense.SpellDodge;
import com.robertx22.mine_and_slash.database.stats.types.offense.SpellDamage;
import com.robertx22.mine_and_slash.database.stats.types.resources.HealPower;
import com.robertx22.mine_and_slash.database.stats.types.resources.MagicShield;
import com.robertx22.mine_and_slash.database.stats.types.spell_calc.ReducedCooldownStat;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

import java.util.Arrays;
import java.util.List;

public class SideStep extends BaseGameChangerTrait {

    private SideStep() {
    }

    public static final SideStep INSTANCE = new SideStep();

    @Override
    public String locDescForLangFile() {
        return "A technique developed by the legendary Shadow Walker.";
    }

    @Override
    public String getIconPath() {
        return "game_changers/side_step";
    }

    @Override
    public String locNameForLangFile() {
        return "Side Step";
    }

    @Override
    public String GUID() {
        return "side_step_trait";
    }

    @Override
    public List<ExactStatData> getExactStats() {

        return Arrays.asList(
                new ExactStatData(30, StatModTypes.Multi, DodgeRating.getInstance()),
            new ExactStatData(30, StatModTypes.Flat, new SpellDodge()),
            new ExactStatData(-50, StatModTypes.Multi, Armor.getInstance()),
                new ExactStatData(-30, StatModTypes.Multi, MagicShield.getInstance()),
                new ExactStatData(-30, StatModTypes.Multi, new BlockStrength())
        );
    }

}
