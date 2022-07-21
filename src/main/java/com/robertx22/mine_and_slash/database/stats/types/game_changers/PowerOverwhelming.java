package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.types.defense.Armor;
import com.robertx22.mine_and_slash.database.stats.types.defense.BlockStrength;
import com.robertx22.mine_and_slash.database.stats.types.defense.SpellDodge;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalSpellDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalHit;
import com.robertx22.mine_and_slash.database.stats.types.offense.SpellDamage;
import com.robertx22.mine_and_slash.database.stats.types.resources.MagicShield;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

import java.util.Arrays;
import java.util.List;

public class PowerOverwhelming extends BaseGameChangerTrait {

    private PowerOverwhelming() {
    }

    public static final PowerOverwhelming INSTANCE = new PowerOverwhelming();

    @Override
    public String locDescForLangFile() {
        return "Who needs luck anyway?";
    }

    @Override
    public String getIconPath() {
        return "game_changers/power_overwhelming";
    }

    @Override
    public String locNameForLangFile() {
        return "Power Overwhelming";
    }

    @Override
    public String GUID() {
        return "power_overwhelming_trait";
    }

    @Override
    public List<ExactStatData> getExactStats() {

        return Arrays.asList(
                new ExactStatData(20, StatModTypes.Flat, SpellDamage.getInstance()),
                new ExactStatData(-1000, StatModTypes.Multi, CriticalHit.getInstance()),
                new ExactStatData(-1000, StatModTypes.Multi, CriticalDamage.getInstance())
        );
    }

}
