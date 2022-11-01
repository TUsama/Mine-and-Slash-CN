package com.robertx22.mine_and_slash.database.stats.types.misc;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.misc.SlowOnDamageTakenEffect;
import com.robertx22.mine_and_slash.database.stats.effects.mob_spell_effects.SpeedyEffect;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class SlowOnDamageTaken extends Stat implements IStatEffects {

    public SlowOnDamageTaken() {
        this.maximumValue = 100;
    }

    public static String GUID = "slow_on_damage_taken";

    @Override
    public String locDescForLangFile() {
        return "When hit, receive Slowness I for 3 seconds.";
    }

    @Override
    public StatGroup statGroup() {
        return StatGroup.Misc;
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
    public IStatEffect getEffect() {
        return SlowOnDamageTakenEffect.INSTANCE;
    }

    @Override
    public String locNameForLangFile() {
        return "Slow On Damage Taken";
    }

    @Override
    public boolean IsShownOnStatGui() {
        return false;
    }
}

