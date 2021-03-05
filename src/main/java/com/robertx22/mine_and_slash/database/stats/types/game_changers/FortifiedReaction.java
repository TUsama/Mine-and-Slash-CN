package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.TransferMethod;
import com.robertx22.mine_and_slash.database.stats.types.defense.Armor;
import com.robertx22.mine_and_slash.database.stats.types.defense.DodgeRating;
import com.robertx22.mine_and_slash.database.stats.types.resources.Health;
import com.robertx22.mine_and_slash.database.stats.types.resources.MagicShield;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatTransfer;

import java.util.Arrays;
import java.util.List;

public class FortifiedReaction extends BaseGameChangerTrait implements IStatTransfer {

    private FortifiedReaction() {
    }

    public static final FortifiedReaction INSTANCE = new FortifiedReaction();

    @Override
    public String locDescForLangFile() {
        return "Converts your dodge rating into armor.";
    }

    @Override
    public String getIconPath() {
        return "game_changers/fortified_reaction";
    }

    @Override
    public String locNameForLangFile() {
        return "Fortified Reaction";
    }

    @Override
    public List<TransferMethod> Transfer() {
        return Arrays.asList(new TransferMethod(DodgeRating.getInstance(), Armor.getInstance()));
    }

    @Override
    public List<ExactStatData> getExactStats() {
        return Arrays.asList(
                new ExactStatData(-25, StatModTypes.Multi, DodgeRating.getInstance())
        );
    }

    @Override
    public String GUID() {
        return "fortified_reaction_trait";
    }

    @Override
    public void transferStats(Unit copy, Unit unit, StatData data) {

        for (TransferMethod stat : this.Transfer()) {

            float val = copy.peekAtStat(stat.converted.GUID())
                .getFlatAverage();

            if (val != 0) {
                unit.getCreateStat(stat.converted)
                    .addFlat(-val);
                unit.getCreateStat(stat.statThatBenefits)
                    .addFlat(val);
            }
        }

    }

}
