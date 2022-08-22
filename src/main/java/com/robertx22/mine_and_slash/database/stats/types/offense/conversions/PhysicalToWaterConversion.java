package com.robertx22.mine_and_slash.database.stats.types.offense.conversions;

import com.robertx22.mine_and_slash.database.stats.ConversionMethod;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.offense.PhysicalToFireEffect;
import com.robertx22.mine_and_slash.database.stats.effects.offense.PhysicalToWaterEffect;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalAttackDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatConversion;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

import java.util.Arrays;
import java.util.List;

public class PhysicalToWaterConversion extends Stat implements IStatEffects {

    public static String GUID = "phys_to_water_conversion";

    public PhysicalToWaterConversion() {
        this.maximumValue = 100;
    }

    @Override
    public IStatEffect getEffect() {
        return PhysicalToWaterEffect.INSTANCE;
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
        return "ele_atk_dmg/phys_to_water";
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public String locNameForLangFile() {
        return "Phys DMG Gained as Frost Wep DMG";
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
        return "Adds to frost weapon damage based on your physical damage.";
    }
}
