package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.game_changers.TricksterEffect;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalHit;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public class Trickster extends BaseGameChangerTrait implements IStatEffects {

    private Trickster() {
    }

    public static final Stat INSTANCE = new Trickster();

    @Override
    public String locDescForLangFile() {
        return "On critical hit, 25 percent chance of gaining a Power Charge for 6 seconds, which each provide flat 30 percent critical damage. You may have a maximum of 3 Power Charges.";
    }

    @Override
    public String getIconPath() {
        return "game_changers/trickster";
    }

    @Override
    public String locNameForLangFile() {
        return "Trickster";
    }

    @Override
    public String GUID() {
        return "trickster_trait";
    }

    @Override
    public List<ExactStatData> getExactStats() {
        return Arrays.asList(
            new ExactStatData(-5, StatModTypes.Flat, CriticalHit.getInstance()),
            new ExactStatData(-20, StatModTypes.Flat, CriticalDamage.getInstance())
        );
    }

    @Override
    public IStatEffect getEffect() {
        return TricksterEffect.INSTANCE;
    }
}


