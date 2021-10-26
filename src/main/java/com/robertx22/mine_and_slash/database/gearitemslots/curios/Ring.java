package com.robertx22.mine_and_slash.database.gearitemslots.curios;

import com.robertx22.mine_and_slash.data_generation.wrappers.StatModsHolder;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseCurio;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.PosStats;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.*;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.LootTypeBonusFlat;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.items.gearitems.baubles.ItemRing;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Ring extends BaseCurio {

    public static GearItemSlot INSTANCE = new Ring();

    private Ring() {

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
        return "ring";
    }

    @Override
    public String GUID() {
        return "ring";
    }

    @Override
    public List<PosStats> getPossiblePrimaryStats() {
        return Arrays.asList(
                new PosStats(new ElementalResistFlat(Elements.Fire)).weight(3000),
                new PosStats(new ElementalResistFlat(Elements.Water)).weight(3000),
                new PosStats(new ElementalResistFlat(Elements.Thunder)).weight(3000),
                new PosStats(new ElementalResistFlat(Elements.Nature)).weight(3000),
                new PosStats(new ElementalResistFlat(Elements.Fire).size(StatMod.Size.HALF), new ElementalResistFlat(Elements.Water).size(StatMod.Size.HALF)),
                new PosStats(new ElementalResistFlat(Elements.Fire).size(StatMod.Size.HALF), new ElementalResistFlat(Elements.Thunder).size(StatMod.Size.HALF)),
                new PosStats(new ElementalResistFlat(Elements.Fire).size(StatMod.Size.HALF), new ElementalResistFlat(Elements.Nature).size(StatMod.Size.HALF)),
                new PosStats(new ElementalResistFlat(Elements.Water).size(StatMod.Size.HALF), new ElementalResistFlat(Elements.Thunder).size(StatMod.Size.HALF)),
                new PosStats(new ElementalResistFlat(Elements.Water).size(StatMod.Size.HALF), new ElementalResistFlat(Elements.Nature).size(StatMod.Size.HALF)),
                new PosStats(new ElementalResistFlat(Elements.Thunder).size(StatMod.Size.HALF), new ElementalResistFlat(Elements.Nature).size(StatMod.Size.HALF))
        );
    }

    @Override
    public StatModsHolder getPossibleSecondaryStats() {
        return new StatModsHolder(
                new ElementalResistFlat(Elements.Fire).size(StatMod.Size.QUARTER),
                new ElementalResistFlat(Elements.Water).size(StatMod.Size.QUARTER),
                new ElementalResistFlat(Elements.Nature).size(StatMod.Size.QUARTER),
                new ElementalResistFlat(Elements.Thunder).size(StatMod.Size.QUARTER)
        );

    }

    @Override
    public Item getDefaultItem() {
        return ItemRing.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> getItemsForRaritiesMap() {
        return ItemRing.Items;
    }

    @Override
    public int Weight() {
        return super.Weight() * 2;
    }

    @Override
    public GearSlotType slotType() {
        return GearSlotType.Jewerly;
    }

    @Override
    public String locNameForLangFile() {
        return "Ring";
    }
}
