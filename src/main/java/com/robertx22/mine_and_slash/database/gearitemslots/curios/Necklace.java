package com.robertx22.mine_and_slash.database.gearitemslots.curios;

import com.robertx22.mine_and_slash.data_generation.wrappers.StatModsHolder;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseCurio;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.PosStats;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.misc.BonusExpFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.*;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.LootTypeBonusFlat;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.items.gearitems.baubles.ItemNecklace;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Necklace extends BaseCurio {
    public static GearItemSlot INSTANCE = new Necklace();

    private Necklace() {

    }

    @Override
    public StatReq getRequirements() {
        return noReq;
    }

    @Override
    public PlayStyle getPlayStyle() {
        return PlayStyle.NONE;
    }

    @Override
    public String resourceID() {
        return "necklace";
    }

    @Override
    public String GUID() {
        return "necklace";
    }

    @Override
    public List<PosStats> getPossiblePrimaryStats() {
        return Arrays.asList(
                new PosStats(new HealthFlat()),
                new PosStats(new ManaFlat().size(StatMod.Size.DOUBLE)),
                new PosStats(new EnergyFlat().size(StatMod.Size.DOUBLE)),
                new PosStats(new MagicShieldFlat()),
                new PosStats(new LootTypeBonusFlat(LootType.All))
        );
    }

    @Override
    public StatModsHolder getPossibleSecondaryStats() {
        return new StatModsHolder(
                new ElementalResistFlat(Elements.Fire).size(StatMod.Size.HALF),
                new ElementalResistFlat(Elements.Water).size(StatMod.Size.HALF),
                new ElementalResistFlat(Elements.Nature).size(StatMod.Size.HALF),
                new ElementalResistFlat(Elements.Thunder).size(StatMod.Size.HALF)
        );
    }

    @Override
    public Item getDefaultItem() {
        return ItemNecklace.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> getItemsForRaritiesMap() {
        return ItemNecklace.Items;
    }

    @Override
    public GearSlotType slotType() {
        return GearSlotType.Jewerly;
    }

    @Override
    public String locNameForLangFile() {
        return "Necklace";
    }
}
