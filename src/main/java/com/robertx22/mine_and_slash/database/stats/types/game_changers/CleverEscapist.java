package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.effects.game_changers.CleverEscapistEffect;
import com.robertx22.mine_and_slash.database.stats.effects.game_changers.RefreshingBreezeEffect;
import com.robertx22.mine_and_slash.database.stats.types.defense.DodgeRating;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsStats;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public class CleverEscapist extends BaseGameChangerTrait implements IStatEffects, IAffectsStats {

    private CleverEscapist() {
    }

    public static final CleverEscapist INSTANCE = new CleverEscapist();

    @Override
    public String locDescForLangFile() {
        return "Successfully dodging grants you 10 percent increased damage and 20 percent damage reduction for 5 seconds.";
    }

    @Override
    public String getIconPath() {
        return "game_changers/clever_escapist";
    }

    @Override
    public String locNameForLangFile() {
        return "Clever Escapist";
    }

    @Override
    public String GUID() {
        return "clever_escapist_trait";
    }

    @Override
    public IStatEffect getEffect() {
        return CleverEscapistEffect.INSTANCE;
    }

    @Override
    public void affectStats(EntityCap.UnitData data, StatData statData) {

    }

    @Override
    public List<ExactStatData> getExactStats() {
        return Arrays.asList(
                new ExactStatData(-10, StatModTypes.Multi, DodgeRating.getInstance())
        );
    }
}
