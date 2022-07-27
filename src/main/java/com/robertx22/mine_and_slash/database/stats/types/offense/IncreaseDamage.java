package com.robertx22.mine_and_slash.database.stats.types.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.defense.DamageShieldEffect;
import com.robertx22.mine_and_slash.database.stats.effects.offense.IncreaseDamageEffect;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class IncreaseDamage extends Stat implements IStatEffects {

    public IncreaseDamage() {
        this.maximumValue = 75;
        this.minimumValue = 0;
    }

    public static IncreaseDamage getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public IStatEffect getEffect() {
        return IncreaseDamageEffect.getInstance();
    }

    @Override
    public String GUID() {
        return "increase_damage";
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
        return "Increases all damage dealt by a percentage.";
    }

    @Override
    public String locNameForLangFile() {
        return "Increased Damage";
    }

    private static class SingletonHolder {
        private static final IncreaseDamage INSTANCE = new IncreaseDamage();
    }

    @Override
    public StatGroup statGroup() {
        return null;
    }
}
