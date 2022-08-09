package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.game_changers.LowLifeDmgEffect;
import com.robertx22.mine_and_slash.database.stats.effects.game_changers.NaturesAvatarEffect;
import com.robertx22.mine_and_slash.database.stats.types.generated.AllElementalDamage;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalResist;
import com.robertx22.mine_and_slash.database.stats.types.resources.Health;
import com.robertx22.mine_and_slash.database.stats.types.resources.HealthRegen;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public class PainControl extends BaseGameChangerTrait implements IStatEffects {

    private PainControl() {
    }

    public static final Stat INSTANCE = new PainControl();

    @Override
    public String locDescForLangFile() {
        return "Deal 30 percent more damage when you have 50 percent or less life.";
    }

    @Override
    public String getIconPath() {
        return "game_changers/pain_control";
    }

    @Override
    public String locNameForLangFile() {
        return "Pain Control";
    }

    @Override
    public String GUID() {
        return "pain_control_trait";
    }

    @Override
    public List<ExactStatData> getExactStats() {
        return Arrays.asList(
            new ExactStatData(-5, StatModTypes.Multi, Health.getInstance()),
            new ExactStatData(-5, StatModTypes.Multi, HealthRegen.getInstance())
        );
    }

    @Override
    public IStatEffect getEffect() {
        return LowLifeDmgEffect.INSTANCE;
    }
}


