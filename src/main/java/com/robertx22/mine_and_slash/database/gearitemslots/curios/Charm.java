package com.robertx22.mine_and_slash.database.gearitemslots.curios;

import com.robertx22.mine_and_slash.data_generation.wrappers.StatModsHolder;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseCurio;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.PosStats;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.corestats.CoreStatFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.Stamina;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.items.gearitems.baubles.ItemCharm;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Charm extends BaseCurio {
    public static GearItemSlot INSTANCE = new Charm();

    private Charm() {

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
        return "charm";
    }

    @Override
    public String GUID() {
        return "charm";
    }

    @Override
    public List<PosStats> getPossiblePrimaryStats() {
        return new CoreStatFlat(Stamina.INSTANCE).generateAllPossibleStatVariations()
            .stream()
            .map(x -> new PosStats(x.size(StatMod.Size.DOUBLE)))
            .collect(Collectors.toList());
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
        return ItemCharm.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> getItemsForRaritiesMap() {
        return ItemCharm.Items;
    }

    @Override
    public int Weight() {
        return 1200;
    }

    @Override
    public GearSlotType slotType() {
        return GearSlotType.Jewerly;
    }

    @Override
    public String locNameForLangFile() {
        return "Charm";
    }
}
