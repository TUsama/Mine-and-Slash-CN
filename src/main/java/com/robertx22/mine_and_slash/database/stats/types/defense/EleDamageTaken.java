package com.robertx22.mine_and_slash.database.stats.types.defense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.defense.EleDamageTakenEffect;
import com.robertx22.mine_and_slash.database.stats.effects.defense.PhysicalDamageTakenEffect;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class EleDamageTaken extends Stat implements IStatEffects {

    public EleDamageTaken() {
        this.maximumValue = 75;
        this.minimumValue = 0;
    }

    public static EleDamageTaken getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public IStatEffect getEffect() {
        return EleDamageTakenEffect.getInstance();
    }

    @Override
    public String GUID() {
        return "ele_damage_taken";
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
        return "Increases all elemental damage received by a percentage.";
    }

    @Override
    public String locNameForLangFile() {
        return "Elemental Damage Taken";
    }

    private static class SingletonHolder {
        private static final EleDamageTaken INSTANCE = new EleDamageTaken();
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
