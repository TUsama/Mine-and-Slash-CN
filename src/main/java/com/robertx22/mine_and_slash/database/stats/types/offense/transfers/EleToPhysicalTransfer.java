package com.robertx22.mine_and_slash.database.stats.types.offense.transfers;

import com.robertx22.mine_and_slash.database.stats.ConversionMethod;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.TransferMethod;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalAttackDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatConversion;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatTransfer;

import java.util.Arrays;
import java.util.List;

public class EleToPhysicalTransfer extends Stat implements IStatTransfer {

    @Override
    public List<TransferMethod> Transfer() {
        return Arrays.asList(new TransferMethod(new ElementalAttackDamage(Elements.Water), PhysicalDamage.getInstance()),
                new TransferMethod(new ElementalAttackDamage(Elements.Fire), PhysicalDamage.getInstance()),
                new TransferMethod(new ElementalAttackDamage(Elements.Thunder), PhysicalDamage.getInstance()),
                new TransferMethod(new ElementalAttackDamage(Elements.Nature), PhysicalDamage.getInstance()));

    }

    @Override
    public String GUID() {
        return "ele_to_phys_transfer";
    }

    @Override
    public String locNameForLangFile() {
        return "Ele Wep DMG Converted to Phys DMG";
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
        return "Converts to physical damage based on all your elemental weapon damage.";
    }
}
