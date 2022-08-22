package com.robertx22.mine_and_slash.database.stats.types.offense.transfers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.TransferMethod;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalAttackDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatTransfer;

import java.util.Arrays;
import java.util.List;

public class PhysicalToFireTransfer extends Stat implements IStatTransfer {

    @Override
    public List<TransferMethod> Transfer() {
        return Arrays.asList(new TransferMethod(PhysicalDamage.getInstance(), new ElementalAttackDamage(Elements.Fire)));

    }

    @Override
    public String GUID() {
        return "phys_to_fire_transfer";
    }

    @Override
    public String locNameForLangFile() {
        return "Phys DMG Converted to Fire Wep DMG";
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
        return "Converts to fire weapon damage based on your physical weapon damage.";
    }
}
