package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.types.defense.ArmorPenetration;
import com.robertx22.mine_and_slash.database.stats.types.defense.DodgeRating;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalAttackDamage;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalPene;
import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDispersion;
import com.robertx22.mine_and_slash.database.stats.types.offense.SpellDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.transfers.EleToPhysicalTransfer;
import com.robertx22.mine_and_slash.database.stats.types.resources.HealPower;
import com.robertx22.mine_and_slash.database.stats.types.spell_calc.ReducedCooldownStat;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BerserkersImpartiality extends BaseGameChangerTrait {

    private BerserkersImpartiality() {
    }

    public static final BerserkersImpartiality INSTANCE = new BerserkersImpartiality();

    @Override
    public String locDescForLangFile() {
        return "Charge through your foes with brute force.";
    }

    @Override
    public String getIconPath() {
        return "game_changers/berserkers_impartiality";
    }

    @Override
    public String locNameForLangFile() {
        return "Berserker's Impartiality";
    }

    @Override
    public String GUID() {
        return "berserkers_impartiality_trait";
    }

    @Override
    public List<ExactStatData> getExactStats() {

        List<ExactStatData> list = new ElementalAttackDamage(Elements.Nature).generateAllSingleVariations()
                .stream()
                .map(x -> new ExactStatData(-50, StatModTypes.Multi, x))
                .collect(Collectors.toList());

        list.add(new ExactStatData(100, StatModTypes.Flat, new EleToPhysicalTransfer()));
        list.add(new ExactStatData(-25, StatModTypes.Multi, DodgeRating.getInstance()));

        return list;
    }

}
