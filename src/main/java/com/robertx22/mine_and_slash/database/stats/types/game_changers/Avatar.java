package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.types.defense.Armor;
import com.robertx22.mine_and_slash.database.stats.types.defense.DodgeRating;
import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDispersion;
import com.robertx22.mine_and_slash.database.stats.types.resources.Health;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public class Avatar extends BaseGameChangerTrait {

    private Avatar() {
    }

    public static final Stat INSTANCE = new Avatar();

    @Override
    public String locDescForLangFile() {
        return "Abandon your physical form.";
    }

    @Override
    public String getIconPath() {
        return "game_changers/avatar";
    }

    @Override
    public String locNameForLangFile() {
        return "Avatar";
    }

    @Override
    public String GUID() {
        return "avatar_trait";
    }

    @Override
    public List<ExactStatData> getExactStats() {
        return Arrays.asList(
            new ExactStatData(100, StatModTypes.Flat, new PhysicalDispersion()),
                new ExactStatData(20, StatModTypes.Percent, DodgeRating.getInstance()),
                new ExactStatData(-10, StatModTypes.Multi, Health.getInstance()),
            new ExactStatData(-50, StatModTypes.Multi, Armor.getInstance())
        );
    }

    //@Override
    //public IStatEffect getEffect() {
    //    return NaturesAvatarEffect.INSTANCE;
    //}
}


