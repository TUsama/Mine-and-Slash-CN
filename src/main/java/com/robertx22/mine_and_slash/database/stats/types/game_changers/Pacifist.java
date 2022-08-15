package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.effects.resource.IncreaseHealingEffect;
import com.robertx22.mine_and_slash.database.stats.types.defense.Armor;
import com.robertx22.mine_and_slash.database.stats.types.defense.ArmorPenetration;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalPene;
import com.robertx22.mine_and_slash.database.stats.types.offense.SpellDamage;
import com.robertx22.mine_and_slash.database.stats.types.resources.HealPower;
import com.robertx22.mine_and_slash.database.stats.types.spell_calc.ReducedCooldownStat;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Pacifist extends BaseGameChangerTrait {

    private Pacifist() {
    }

    public static final Pacifist INSTANCE = new Pacifist();

    @Override
    public String locDescForLangFile() {
        return "Lose all aggression.";
    }

    @Override
    public String getIconPath() {
        return "game_changers/pacifist";
    }

    @Override
    public String locNameForLangFile() {
        return "Pacifist";
    }

    @Override
    public String GUID() {
        return "pacifist_trait";
    }

    @Override
    public List<ExactStatData> getExactStats() {

        return Arrays.asList(
            new ExactStatData(30, StatModTypes.Flat, HealPower.getInstance()),
            new ExactStatData(25, StatModTypes.Flat, ReducedCooldownStat.getInstance()),
            new ExactStatData(-50, StatModTypes.Flat, SpellDamage.getInstance())
        );
    }

}
