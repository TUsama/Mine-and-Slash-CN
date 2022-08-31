package com.robertx22.mine_and_slash.database.currency;

import com.robertx22.mine_and_slash.advacements.PlayerLevelTrigger;
import com.robertx22.mine_and_slash.database.currency.base.CurrencyItem;
import com.robertx22.mine_and_slash.database.currency.base.ICurrencyItemEffect;
import com.robertx22.mine_and_slash.database.currency.base.IShapedRecipe;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.BaseLocRequirement;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.SimpleGearLocReq;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.item_types.GearReq;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.loot.blueprints.GearBlueprint;
import com.robertx22.mine_and_slash.loot.gens.util.GearCreationUtils;
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

public class OrbOfAlchemyItem extends CurrencyItem implements ICurrencyItemEffect, IRenamed, IShapedRecipe {
    @Override
    public String GUID() {
        return "currency/orb_of_alchemy";
    }

    public static final String ID = Ref.MODID + ":currency/orb_of_alchemy";

    @Override
    public List<String> oldNames() {
        return Arrays.asList(Ref.MODID + ":orb_of_alchemy");
    }

    public OrbOfAlchemyItem() {

        super(ID);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);

        GearBlueprint gearPrint = new GearBlueprint(gear.level);
        gearPrint.gearItemSlot.set(gear.gearTypeName);
        gearPrint.rarity.minRarity = 2;
        gearPrint.rarity.maxRarity = 2;
        gearPrint.level.LevelRange = false;

        GearItemData newgear = gearPrint.createData();
        gear.WriteOverDataThatShouldStayCommon(newgear);

        if (newgear.isRuned()) {
            newgear.runes.capacity++;
            newgear.runes.capacity++;
        }

        for (IRerollable rel : newgear.GetAllRerollable()) {
            rel.RerollNumbers(newgear);
        }

        ItemStack result = ItemStack.EMPTY;

        if (gear.changesItemStack()) {
            result = GearCreationUtils.CreateStack(newgear);
        } else {
            result = stack;
            Gear.Save(result, newgear);
        }

        return result;
    }

    @Override
    public List<BaseLocRequirement> requirements() {
        return Arrays.asList(GearReq.INSTANCE, SimpleGearLocReq.IS_COMMON);
    }

    @Override
    public int getTier() {
        return 2;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Rare;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("Turn coal into diamonds?");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Orb Of Alchemy";
    }

    @Override
    public String locDescForLangFile() {
        return "Transform Common Item";
    }

    @Override
    public int instabilityAddAmount() {
        return 20;
    }

    @Override
    public ShapedRecipeBuilder getRecipe() {
        return shaped(ModItems.ORB_OF_ALCHEMY.get())
            .key('t', ModItems.ORB_OF_TRANSMUTATION.get())
            .key('v', Items.EMERALD)
            .key('o', ItemOre.ItemOres.get(IRarity.Rare))
            .patternLine("ovo")
            .patternLine("vtv")
            .patternLine("ovo")
            .addCriterion("player_level", new PlayerLevelTrigger.Instance(10));
    }

}