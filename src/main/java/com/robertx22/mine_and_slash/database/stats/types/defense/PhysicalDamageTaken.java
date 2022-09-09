package com.robertx22.mine_and_slash.database.stats.types.defense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.defense.DamageTakenEffect;
import com.robertx22.mine_and_slash.database.stats.effects.defense.PhysicalDamageTakenEffect;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class PhysicalDamageTaken extends Stat implements IStatEffects {

    public PhysicalDamageTaken() {
        this.maximumValue = 75;
        this.minimumValue = 0;
    }

    public static PhysicalDamageTaken getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public IStatEffect getEffect() {
        return PhysicalDamageTakenEffect.getInstance();
    }

    @Override
    public String GUID() {
        return "phys_damage_taken";
    }

    @Override
    public boolean IsPercent() {
        return true;
    }

    public StatScaling getScaling() {
        return StatScaling.NONE;
    }

    @Override
    public Elements getElement() {
        return null;
    }

    @Override
    public String locDescForLangFile() {
        return "Increases all physical damage received by a percentage.";
    }

    @Override
    public String locNameForLangFile() {
        return "Physical Damage Taken";
    }

    private static class SingletonHolder {
        private static final PhysicalDamageTaken INSTANCE = new PhysicalDamageTaken();
    }

    @Override
    public StatGroup statGroup() {
        return StatGroup.Defenses;
    }

    @Override
    public boolean IsShownOnStatGui() {
        return false;
    }
}
