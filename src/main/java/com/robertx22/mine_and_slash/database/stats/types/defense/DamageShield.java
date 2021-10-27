package com.robertx22.mine_and_slash.database.stats.types.defense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.defense.DamageShieldEffect;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class DamageShield extends Stat implements IStatEffects {

    public DamageShield() {
        this.maximumValue = 75;
        this.minimumValue = 0;
    }

    public static DamageShield getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public IStatEffect getEffect() {
        return DamageShieldEffect.getInstance();
    }

    @Override
    public String GUID() {
        return "damage_shield";
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
        return "Decreases all damage received by a percentage.";
    }

    @Override
    public String locNameForLangFile() {
        return "Damage Reduction";
    }

    private static class SingletonHolder {
        private static final DamageShield INSTANCE = new DamageShield();
    }

    @Override
    public StatGroup statGroup() {
        return StatGroup.Defenses;
    }
}
