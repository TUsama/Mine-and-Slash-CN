package com.robertx22.mine_and_slash.database.stats.types.resources;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.resource.IncreaseHealingEffect;
import com.robertx22.mine_and_slash.database.stats.effects.resource.IncreaseHealingReceivedEffect;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class HealingReceived extends Stat implements IStatEffects {
    public static String GUID = "increase_healing_received";

    private HealingReceived() {
    }

    public static HealingReceived getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public StatGroup statGroup() {
        return StatGroup.Regeneration;
    }

    @Override
    public String locDescForLangFile() {
        return "Increases all types of healing received like health regen, lifesteal, life on hit, spell heals etc";
    }

    @Override
    public IStatEffect getEffect() {
        return new IncreaseHealingReceivedEffect();
    }

    public String getIconPath() {
        return "resource/heal_power";
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public StatScaling getScaling() {
        return StatScaling.NONE;
    }

    @Override
    public Elements getElement() {
        return null;
    }

    @Override
    public boolean IsPercent() {
        return true;
    }

    @Override
    public String locNameForLangFile() {
        return "Increased Healing Received";
    }

    private static class SingletonHolder {
        private static final HealingReceived INSTANCE = new HealingReceived();
    }
}

