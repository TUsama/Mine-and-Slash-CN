package com.robertx22.mine_and_slash.database.gearitemslots.offhand;

import com.robertx22.mine_and_slash.data_generation.wrappers.StatModsHolder;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseOffHand;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.PosStats;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalHitFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.EnergyRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicShieldRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.unique_items.ISpecificStatReq;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.items.gearitems.offhands.MyTorch;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Torch extends BaseOffHand implements ISpecificStatReq {
    public static GearItemSlot INSTANCE = new Torch();

    private Torch() {

    }

    @Override
    public String resourceID() {
        return "torch";
    }

    static StatReq req = new StatReq(LvlPointStat.DEXTERITY, StatReq.Size.TINY, LvlPointStat.STAMINA, StatReq.Size.TINY);

    @Override
    public PlayStyle getPlayStyle() {
        return PlayStyle.THIEF;
    }

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public String GUID() {
        return "torch";
    }

    @Override
    public Item getDefaultItem() {
        return MyTorch.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> getItemsForRaritiesMap() {
        return MyTorch.Items;
    }

    @Override
    public List<PosStats> getPossiblePrimaryStats() {
        return Arrays.asList(new PosStats(new CriticalHitFlat().size(StatMod.Size.LOW)),
                new PosStats(new EnergyRegenFlat().size(StatMod.Size.HALF_MORE),
                        new ElementalResistFlat(Elements.Elemental).size(StatMod.Size.QUARTER))
        );
    }

    @Override
    public StatModsHolder getPossibleSecondaryStats() {
        return new StatModsHolder(coreStatMods());
    }

    @Override
    public String locNameForLangFile() {
        return "Torch";
    }

}
