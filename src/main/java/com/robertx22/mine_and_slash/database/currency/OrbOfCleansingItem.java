package com.robertx22.mine_and_slash.database.currency;

import com.robertx22.mine_and_slash.advacements.PlayerLevelTrigger;
import com.robertx22.mine_and_slash.database.currency.base.CurrencyItem;
import com.robertx22.mine_and_slash.database.currency.base.ICurrencyItemEffect;
import com.robertx22.mine_and_slash.database.currency.base.IShapedRecipe;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.BaseLocRequirement;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.GearEnumLocReq;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.SimpleGearLocReq;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.item_types.GearReq;
import com.robertx22.mine_and_slash.items.SimpleMatItem;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModItems;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.Arrays;
import java.util.List;

public class OrbOfCleansingItem extends CurrencyItem implements ICurrencyItemEffect, IShapedRecipe {

    @Override
    public String GUID() {
        return "currency/clear_chaos";
    }

    private static final String name = Ref.MODID + ":currency/clear_chaos";

    public OrbOfCleansingItem() {
        super(name);
    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);
        //gear.runes.clearRunes();
        Gear.Save(stack, gear);

        return stack;
    }

    @Override
    public List<BaseLocRequirement> requirements() {
        return Arrays.asList(GearReq.INSTANCE, SimpleGearLocReq.HAS_CHAOS);
    }

    @Override
    public int getTier() {
        return 5;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("Cleanse all evil.");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Orb of Cleansing";
    }

    @Override
    public String locDescForLangFile() {
        return "Clears Chaos and Major Arcana Chaos stats from an item.";
    }

    @Override
    public int instabilityAddAmount() {
        return 10;
    }

    @Override
    public ShapedRecipeBuilder getRecipe() {
        return shaped(ModItems.ORB_OF_FORGETFULNESS.get())
            .key('#', SimpleMatItem.MYTHIC_ESSENCE)
            .key('t', ModItems.CHAOTIC_WISP.get())
                .key('v', ModItems.CHAOS_ORB.get())
            .key('o', ItemOre.ItemOres.get(IRarity.Legendary))
            .patternLine("o#o")
            .patternLine("oto")
            .patternLine("v#v")
            .addCriterion("player_level", new PlayerLevelTrigger.Instance(10));
    }

}

