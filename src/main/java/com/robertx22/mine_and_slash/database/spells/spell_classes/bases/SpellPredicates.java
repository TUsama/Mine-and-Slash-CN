package com.robertx22.mine_and_slash.database.spells.spell_classes.bases;

import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ShootableItem;
import net.minecraft.util.text.TextFormatting;

import java.util.function.Predicate;

public class SpellPredicates {
    private static Predicate<LivingEntity> SHOOTABLE_PRED = x -> {
        Item item = x.getHeldItemMainhand()
            .getItem();
        return item instanceof ShootableItem;
    };

    private static Predicate<LivingEntity> MELEE_PRED = x -> {
        try {
            GearItemData data = Gear.Load(x.getHeldItemMainhand());
            return data != null && (data.GetBaseGearType()
                .isMeleeWeapon() || data.GetBaseGearType().isMageWeapon());
        } catch (Exception e) {
            return false;
        }
    };

    public static SpellPredicate REQUIRE_SHOOTABLE = new SpellPredicate(SHOOTABLE_PRED, new SText(TextFormatting.RED + "" + TextFormatting.ITALIC + "Requires Ranged Weapon"));
    public static SpellPredicate REQUIRE_MELEE = new SpellPredicate(MELEE_PRED, new SText(TextFormatting.RED + "" + TextFormatting.ITALIC + "Requires Melee Weapon"));
}

