package com.robertx22.mine_and_slash.database.stats.types.offense.conversions;

import com.robertx22.mine_and_slash.database.stats.ConversionMethod;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalAttackDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.database.stats.types.resources.EnergyRegen;
import com.robertx22.mine_and_slash.database.stats.types.resources.ManaRegen;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatConversion;

import java.util.Arrays;
import java.util.List;

public class PhysicalToFireConversion extends Stat implements IStatConversion {

    @Override
    public List<ConversionMethod> conversion() {
        return Arrays.asList(new ConversionMethod(PhysicalDamage.getInstance(), new ElementalAttackDamage(Elements.Fire)));

    }

    @Override
    public String GUID() {
        return "physical_to_fire_conversion";
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
