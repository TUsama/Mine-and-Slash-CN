package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.types.defense.Armor;
import com.robertx22.mine_and_slash.database.stats.types.defense.ArmorPenetration;
import com.robertx22.mine_and_slash.database.stats.types.defense.DodgeRating;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalPene;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalHit;
import com.robertx22.mine_and_slash.database.stats.types.offense.SpellDamage;
import com.robertx22.mine_and_slash.database.stats.types.resources.MagicShield;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RecklessBlows extends BaseGameChangerTrait {

    private RecklessBlows() {
    }

    public static final RecklessBlows INSTANCE = new RecklessBlows();

    @Override
    public String locDescForLangFile() {
        return "Expose your weakness to give everything you have.";
    }

    @Override
    public String getIconPath() {
        return "game_changers/reckless_blows";
    }

    @Override
    public String locNameForLangFile() {
        return "Reckless Blows";
    }

    @Override
    public String GUID() {
        return "reckless_blows_trait";
    }

    @Override
    public List<ExactStatData> getExactStats() {

        return Arrays.asList(
                new ExactStatData(25, StatModTypes.Flat, new ElementalPene(Elements.Elemental)),
                new ExactStatData(25, StatModTypes.Flat, ArmorPenetration.getInstance()),
                new ExactStatData(-20, StatModTypes.Multi, MagicShield.getInstance()),
                new ExactStatData(-20, StatModTypes.Multi, Armor.getInstance()),
                new ExactStatData(-20, StatModTypes.Multi, DodgeRating.getInstance())
        );
    }

}
