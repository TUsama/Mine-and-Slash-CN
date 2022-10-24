package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.saveclasses.ExactStatData;

import java.util.Arrays;
import java.util.List;

public class GuardianAngel extends BaseGameChangerTrait {

    private GuardianAngel() {
    }

    public static final GuardianAngel INSTANCE = new GuardianAngel();

    @Override
    public String locDescForLangFile() {
        return "Your kills don't share EXP when in a party. You don't contribute towards the player count when EXP is shared in a party. You don't receive EXP from party members' kills.";
    }

    @Override
    public String getIconPath() {
        return "game_changers/guardian_angel";
    }

    @Override
    public String locNameForLangFile() {
        return "Guardian Angel";
    }

    @Override
    public String GUID() {
        return "guardian_angel_trait";
    }

    @Override
    public List<ExactStatData> getExactStats() {

        return Arrays.asList(
        );
    }

}
