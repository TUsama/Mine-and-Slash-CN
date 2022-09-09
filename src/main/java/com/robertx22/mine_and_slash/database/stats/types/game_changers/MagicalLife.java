package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.TransferMethod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicShieldFlat;
import com.robertx22.mine_and_slash.database.stats.types.defense.DodgeRating;
import com.robertx22.mine_and_slash.database.stats.types.resources.Health;
import com.robertx22.mine_and_slash.database.stats.types.resources.HealthRegen;
import com.robertx22.mine_and_slash.database.stats.types.resources.MagicShield;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatTransfer;

import java.util.Arrays;
import java.util.List;

public class MagicalLife extends BaseGameChangerTrait implements IStatTransfer {

    private MagicalLife() {
    }

    public static final MagicalLife INSTANCE = new MagicalLife();

    @Override
    public String locDescForLangFile() {
        return "Converts your magic shield into health.";
    }

    @Override
    public String getIconPath() {
        return "game_changers/magical_life";
    }

    @Override
    public String locNameForLangFile() {
        return "Magical Life";
    }

    @Override
    public List<TransferMethod> Transfer() {
        return Arrays.asList(new TransferMethod(MagicShield.getInstance(), Health.getInstance()));
    }

    @Override
    public List<ExactStatData> getExactStats() {
        return Arrays.asList(
                new ExactStatData(-10, StatModTypes.Multi, Health.getInstance())
        );
    }

    @Override
    public String GUID() {
        return "magical_life_trait";
    }

    @Override
    public void transferStats(Unit copy, Unit unit, StatData data) {
        /*
        for (TransferMethod stat : this.Transfer()) {

            float val = copy.peekAtStat(stat.converted.GUID())
                .getFlatAverage();

            if (val != 0) {
                unit.getCreateStat(stat.converted)
                    .addFlat(-val);
                unit.getCreateStat(stat.statThatBenefits)
                    .addFlat(val);
            }
        }*/
        for (TransferMethod stat : this.Transfer()) {

            float val = copy.peekAtStat(stat.converted.GUID())
                    .getFlatAverage();

            float total = copy.peekAtStat(stat.converted.GUID())
                    .getTotalVal();

            if (val != 0) {
                unit.getCreateStat(stat.converted)
                        .addFlat(-val);
                unit.getCreateStat(stat.statThatBenefits)
                        .addFlat(total);
            }
        }
    }
}
