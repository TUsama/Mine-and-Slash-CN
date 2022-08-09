package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.game_changers.NaturesAvatarEffect;
import com.robertx22.mine_and_slash.database.stats.effects.game_changers.OverseerEffect;
import com.robertx22.mine_and_slash.database.stats.types.defense.Armor;
import com.robertx22.mine_and_slash.database.stats.types.defense.DodgeRating;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalHit;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public class Overseer extends BaseGameChangerTrait implements IStatEffects {

    private Overseer() {
    }

    public static final Stat INSTANCE = new Overseer();

    @Override
    public String locDescForLangFile() {
        return "On attack, 50 percent chance of gaining a Fortitude Charge for 8 seconds, which each provide 4 percent damage reduction. You may have a maximum of 3 Fortitude Charges.";
    }

    @Override
    public String getIconPath() {
        return "game_changers/overseer";
    }

    @Override
    public String locNameForLangFile() {
        return "Overseer";
    }

    @Override
    public String GUID() {
        return "overseer_trait";
    }

    @Override
    public List<ExactStatData> getExactStats() {
        return Arrays.asList(
            new ExactStatData(-20, StatModTypes.Multi, Armor.getInstance()),
            new ExactStatData(-20, StatModTypes.Multi, DodgeRating.getInstance())
        );
    }

    @Override
    public IStatEffect getEffect() {
        return OverseerEffect.INSTANCE;
    }
}


