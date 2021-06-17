package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.types.generated.AllElementalDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.database.stats.types.resources.Health;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsStats;

import java.util.List;
import java.util.stream.Collectors;

public class OverflowingVitality extends BaseGameChangerTrait implements IAffectsStats {

    static int INCREASE = 1;
    static int ELE_DECREASE = 50;

    static int HP_DECREASE = 10;

    private OverflowingVitality() {
    }

    public static OverflowingVitality getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String locDescForLangFile() {
        return "Adds flat physical damage by " + INCREASE + " percent of your max health.";
    }

    @Override
    public String getIconPath() {
        return "game_changers/overflowing_vitality";
    }

    @Override
    public String locNameForLangFile() {
        return "Overflowing Vitality";
    }

    @Override
    public String GUID() {
        return "overflowing_vitality_trait";
    }

    @Override
    public List<ExactStatData> getExactStats() {

        List<ExactStatData> list = new AllElementalDamage(Elements.Nature).generateAllSingleVariations()
            .stream()
            .map(x -> new ExactStatData(-ELE_DECREASE, StatModTypes.Multi, x))
            .collect(Collectors.toList());

        list.add(new ExactStatData(-HP_DECREASE, StatModTypes.Multi, Health.getInstance()));

        return list;
    }

    @Override
    public void affectStats(EntityCap.UnitData data, StatData statData) {

        float num = data.getUnit()
            .getCreateStat(Health.getInstance())
            .getAverageValue() * INCREASE / 100;

        data.getUnit()
            .getCreateStat(PhysicalDamage.getInstance())
            .addFlat(+num);
    }

    private static class SingletonHolder {
        private static final OverflowingVitality INSTANCE = new OverflowingVitality();
    }
}
