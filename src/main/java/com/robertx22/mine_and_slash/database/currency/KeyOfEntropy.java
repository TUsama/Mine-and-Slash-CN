package com.robertx22.mine_and_slash.database.currency;

import com.robertx22.mine_and_slash.advacements.PlayerLevelTrigger;
import com.robertx22.mine_and_slash.database.currency.base.CurrencyItem;
import com.robertx22.mine_and_slash.database.currency.base.ICurrencyItemEffect;
import com.robertx22.mine_and_slash.database.currency.base.IShapedRecipe;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.BaseLocRequirement;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.SimpleGearLocReq;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.item_types.GearReq;
import com.robertx22.mine_and_slash.items.SimpleMatItem;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModItems;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IRerollable;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.interfaces.IRenamed;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.Arrays;
import java.util.List;

public class KeyOfEntropy extends CurrencyItem implements ICurrencyItemEffect, IRenamed, IShapedRecipe {
    @Override
    public String GUID() {
        return "currency/reroll_chaos";
    }

    private static final String name = Ref.MODID + ":currency/reroll_chaos";

    @Override
    public List<String> oldNames() {
        return Arrays.asList(Ref.MODID + ":reroll_chaos");
    }

    public KeyOfEntropy() {

        super(name);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {
        GearItemData gear = Gear.Load(stack);
        gear.chaosStats.RerollFully(gear);
        Gear.Save(stack, gear);

        return stack;
    }

    @Override
    public List<BaseLocRequirement> requirements() {
        return Arrays.asList(GearReq.INSTANCE, SimpleGearLocReq.HAS_CHAOS_STATS);
    }

    @Override
    public int getTier() {
        return 5;
    }

    @Override
    public int getRarityRank() {
        return Legendary;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("Even chaos can be quelled.");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Key Of Entropy";
    }

    @Override
    public String locDescForLangFile() {
        return "Re-rolls chaos stat.";
    }

    @Override
    public int instabilityAddAmount() {
        return 100;
    }

    @Override
    public ShapedRecipeBuilder getRecipe() {
        return shaped(ModItems.ORB_OF_UNIQUE_BLESSING.get())
            .key('#', SimpleMatItem.GOLDEN_ORB)
            .key('t', ModItems.ORB_OF_UNIQUE_BLESSING.get())
            .key('v', Items.GOLD_INGOT)
            .key('o', ItemOre.ItemOres.get(IRarity.Legendary))
            .patternLine("o#o")
            .patternLine("oto")
            .patternLine("v#v")
            .addCriterion("player_level", new PlayerLevelTrigger.Instance(10));
    }

}