package com.robertx22.mine_and_slash.database.stats.types.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.offense.IncreaseDamageEffect;
import com.robertx22.mine_and_slash.database.stats.effects.offense.ReducePhysicalDamageEffect;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class ReducePhysicalDamage extends Stat implements IStatEffects {

    public ReducePhysicalDamage() {
    }

    public static ReducePhysicalDamage getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public IStatEffect getEffect() {
        return ReducePhysicalDamageEffect.getInstance();
    }

    @Override
    public String GUID() {
        return "reduce_physical_damage";
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
        return "Reduces all physical damage dealt by a percentage.";
    }

    @Override
    public String locNameForLangFile() {
        return "Reduced Physical Damage";
    }

    private static class SingletonHolder {
        private static final ReducePhysicalDamage INSTANCE = new ReducePhysicalDamage();
    }

    @Override
    public StatGroup statGroup() {
        return StatGroup.Misc;
    }

    @Override
    public boolean IsShownOnStatGui() {
        return false;
    }
}
