package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.effects.game_changers.RefreshingBreezeEffect;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalHit;
import com.robertx22.mine_and_slash.database.stats.types.resources.EnergyRegen;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsStats;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public class RefreshingBreeze extends BaseGameChangerTrait implements IStatEffects, IAffectsStats {

    private RefreshingBreeze() {
    }

    public static final RefreshingBreeze INSTANCE = new RefreshingBreeze();

    public static int PERCENT = 10;

    @Override
    public String locDescForLangFile() {
        return "Dodging restores " + PERCENT + " percent of your energy regen. But if you don't dodge, you a quarter of that from energy.";
    }

    @Override
    public String getIconPath() {
        return "game_changers/breeze";
    }

    @Override
    public String locNameForLangFile() {
        return "Refreshing Breeze";
    }

    @Override
    public String GUID() {
        return "refreshing_breeze_trait";
    }

    @Override
    public IStatEffect getEffect() {
        return RefreshingBreezeEffect.INSTANCE;
    }

    @Override
    public void affectStats(EntityCap.UnitData data, StatData statData) {

    }

    @Override
    public List<ExactStatData> getExactStats() {
        return Arrays.asList(
                new ExactStatData(-30, StatModTypes.Multi, EnergyRegen.getInstance())
        );
    }
}
