package com.robertx22.mine_and_slash.database.gearitemslots.bases;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.DodgeRatingFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicShieldFlat;

import java.util.Arrays;
import java.util.List;

public abstract class BaseArmor extends GearItemSlot {

    @Override
    public final int Weight() {
        return super.Weight() / 3;
    }

    @Override
    public List<PosStats> getPossiblePrimaryStats() {
        return Arrays.asList(new PosStats(new ArmorFlat().size(StatMod.Size.QUARTER_MORE)),
                new PosStats(new ArmorFlat().size(StatMod.Size.LOW), new MagicShieldFlat().size(StatMod.Size.LOW)),
                new PosStats(new ArmorFlat().size(StatMod.Size.LOW), new DodgeRatingFlat().size(StatMod.Size.LOW)));
    }

    @Override
    public GearSlotType slotType() {
        return GearSlotType.Armor;
    }
}
