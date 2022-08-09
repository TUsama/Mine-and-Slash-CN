package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.game_changers.ManaBatteryEffect;
import com.robertx22.mine_and_slash.database.stats.effects.game_changers.NaturesAvatarEffect;
import com.robertx22.mine_and_slash.database.stats.types.defense.Armor;
import com.robertx22.mine_and_slash.database.stats.types.generated.AllElementalDamage;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalResist;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public class NaturesAvatar extends BaseGameChangerTrait implements IStatEffects {

    private NaturesAvatar() {
    }

    public static final Stat INSTANCE = new NaturesAvatar();

    @Override
    public String locDescForLangFile() {
        return "50 percent of non-nature damage is converted to nature damage. Deal no non-nature damage.";
    }

    @Override
    public String getIconPath() {
        return "game_changers/natures_avatar";
    }

    @Override
    public String locNameForLangFile() {
        return "Nature's Avatar";
    }

    @Override
    public String GUID() {
        return "natures_avatar_trait";
    }

    @Override
    public List<ExactStatData> getExactStats() {
        return Arrays.asList(
            new ExactStatData(10, StatModTypes.Flat, new AllElementalDamage(Elements.Nature)),
            new ExactStatData(10, StatModTypes.Flat, new ElementalResist(Elements.Nature))
        );
    }

    @Override
    public IStatEffect getEffect() {
        return NaturesAvatarEffect.INSTANCE;
    }
}


