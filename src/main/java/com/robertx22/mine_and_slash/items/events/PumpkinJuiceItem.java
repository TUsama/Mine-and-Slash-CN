package com.robertx22.mine_and_slash.items.events;

import com.robertx22.mine_and_slash.advacements.PlayerLevelTrigger;
import com.robertx22.mine_and_slash.database.currency.base.IShapedRecipe;
import com.robertx22.mine_and_slash.items.SimpleMatItem;
import com.robertx22.mine_and_slash.items.reset_potions.bases.BaseInstantPotion;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModItems;
import com.robertx22.mine_and_slash.potion_effects.all.BleedPotion;
import com.robertx22.mine_and_slash.potion_effects.all.events.GildedPumpkinJuiceEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
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

public class PumpkinJuiceItem extends BaseInstantPotion {

    public PumpkinJuiceItem() {
        super();
    }

    @Override
    public ITextComponent tooltip() {
        ITextComponent comp = new StringTextComponent("Provides a 25% EXP boost for 1 hour.");
        return comp;

    }

    @Override
    public void onFinish(ItemStack stack, World world, LivingEntity player, EntityCap.UnitData unitdata) {

        //System.out.println("finished drinking");
        if (player instanceof PlayerEntity) {
            //System.out.println("found player: " + player);
            PotionEffectUtils.reApplyToSelf(GildedPumpkinJuiceEffect.INSTANCE, player);
        }
    }

    @Override
    public String GUID() {
        return "events/pumpkin_juice";
    }

    @Override
    public String locNameForLangFile() {
        return "Gilded Pumpkin Juice";
    }

}
