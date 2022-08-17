package com.robertx22.mine_and_slash.items.reset_potions;

import com.robertx22.mine_and_slash.advacements.PlayerLevelTrigger;
import com.robertx22.mine_and_slash.database.currency.base.IShapedRecipe;
import com.robertx22.mine_and_slash.items.SimpleMatItem;
import com.robertx22.mine_and_slash.items.reset_potions.bases.BaseInstantPotion;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModItems;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class ResetAllPotionItem extends BaseInstantPotion implements IShapedRecipe {

    public ResetAllPotionItem() {
        super();
    }

    @Override
    public ITextComponent tooltip() {
        ITextComponent comp = new StringTextComponent("Resets your stat, spell, and talent points.");
        return comp;

    }

    @Override
    public void onFinish(ItemStack stack, World world, LivingEntity player, EntityCap.UnitData unitdata) {

        if (player instanceof PlayerEntity) {
            Load.statPoints((PlayerEntity) player)
                .resetStats();
            Load.spells((PlayerEntity) player)
                    .reset();
            Load.talents((PlayerEntity) player)
                    .reset();
        }
    }

    @Override
    public String GUID() {
        return "alchemy/instant/misc/reset_all";
    }

    @Override
    public String locNameForLangFile() {
        return "Potion of All Reset";
    }

    @Override
    public ShapedRecipeBuilder getRecipe() {
        return shaped(ModItems.RESET_ALL.get())
            .key('t', ModItems.RESET_SPELLS.get())
            .key('v', ModItems.RESET_STATS.get())
            .key('b', ModItems.RESET_TALENTS.get())
                .key('l', Items.LAPIS_LAZULI)
            .patternLine(" v ")
            .patternLine("ltl")
            .patternLine(" b ")
            .addCriterion("player_level", new PlayerLevelTrigger.Instance(5));
    }

}
