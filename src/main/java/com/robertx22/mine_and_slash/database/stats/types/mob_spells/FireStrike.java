package com.robertx22.mine_and_slash.database.stats.types.mob_spells;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.mob_spell_effects.FireStrikeEffect;
import com.robertx22.mine_and_slash.database.stats.effects.offense.PhysicalToHighestEle;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class FireStrike extends Stat implements IStatEffects {

    public FireStrike() {
        this.maximumValue = 100;
    }

    public static String GUID = "mob_fire_strike";

    @Override
    public String locDescForLangFile() {
        return "On hit, strike enemies in front of you with fire.";
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
        return FireStrikeEffect.INSTANCE;
    }

    @Override
    public String locNameForLangFile() {
        return "Mob Fire Strike";
    }

    @Override
    public boolean IsShownOnStatGui() {
        return false;
    }
}

