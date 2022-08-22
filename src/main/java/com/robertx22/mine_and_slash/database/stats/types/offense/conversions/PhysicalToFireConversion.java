package com.robertx22.mine_and_slash.database.stats.types.offense.conversions;

import com.robertx22.mine_and_slash.database.stats.ConversionMethod;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.offense.PhysicalToFireEffect;
import com.robertx22.mine_and_slash.database.stats.effects.offense.PhysicalToHighestEle;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalAttackDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.database.stats.types.resources.EnergyRegen;
import com.robertx22.mine_and_slash.database.stats.types.resources.ManaRegen;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatConversion;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public class PhysicalToFireConversion extends Stat implements IStatEffects {

    public static String GUID = "phys_to_fire_conversion";

    public PhysicalToFireConversion() {
        this.maximumValue = 100;
    }

    @Override
    public IStatEffect getEffect() {
        return PhysicalToFireEffect.INSTANCE;
    }

    @Override
    public StatGroup statGroup() {
        return StatGroup.EleAttackDamage;
    }

    @Override
    public String getIcon() {
        return "\u2764";
    }

    @Override
    public String getIconPath() {
        return "ele_atk_dmg/phys_to_fire";
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public String locNameForLangFile() {
        return "Phys DMG Gained as Fire Wep DMG";
    }

    @Override
    public boolean IsPercent() {
        return true;
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
    public String locDescForLangFile() {
        return "Adds to fire weapon damage based on your physical damage.";
    }
}
