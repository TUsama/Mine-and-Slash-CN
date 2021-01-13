package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.effects.game_changers.HarmonyEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class Harmony extends BaseGameChangerTrait implements IStatEffects {

    private Harmony() {
    }

    public static final Harmony INSTANCE = new Harmony();

    @Override
    public String locDescForLangFile() {
        return "HP restoration effects now simultaneously apply to HP and magic shield at 33% effectiveness. ";
    }

    @Override
    public String getIconPath() {
        return "game_changers/harmony";
    }

    @Override
    public String locNameForLangFile() {
        return "Harmony";
    }

    @Override
    public String GUID() {
        return "harmony_trait";
    }

    @Override
    public IStatEffect getEffect() {
        return HarmonyEffect.INSTANCE;
    }
}


