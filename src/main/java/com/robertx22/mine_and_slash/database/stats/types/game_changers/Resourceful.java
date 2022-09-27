package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.effects.game_changers.ResourcefulSpendEnergyInsteadEffect;
import com.robertx22.mine_and_slash.database.stats.types.resources.*;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatMultipleEffects;

import java.util.Arrays;
import java.util.List;

public class Resourceful extends BaseGameChangerTrait implements IStatMultipleEffects {

    private Resourceful() {
    }

    public static final Resourceful INSTANCE = new Resourceful();

    @Override
    public String locDescForLangFile() {
        return "Spells that cost mana use energy instead. Spells that cost both still retain the initial energy cost (it adds on).";
    }

    @Override
    public String getIconPath() {
        return "game_changers/resourceful";
    }

    @Override
    public String locNameForLangFile() {
        return "Resourceful";
    }

    @Override
    public String GUID() {
        return "resourceful_trait";
    }

    @Override
    public List<IStatEffect> getEffects() {
        return Arrays.asList(ResourcefulSpendEnergyInsteadEffect.getInstance());
    }

    @Override
    public List<ExactStatData> getExactStats() {
        return Arrays.asList(
            new ExactStatData(-10, StatModTypes.Multi, Energy.getInstance()),
            new ExactStatData(-20, StatModTypes.Multi, EnergyRegen.getInstance()),
            new ExactStatData(-1000, StatModTypes.Multi, Mana.getInstance()),
            new ExactStatData(-1000, StatModTypes.Multi, ManaRegen.getInstance())
        );
    }

}


