package com.robertx22.mine_and_slash.database.spells.spell_classes.divine;

import com.robertx22.mine_and_slash.database.spells.entities.proj.GroundSlamEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellPredicates;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class GroundSlamSpell extends BaseSpell {

    private GroundSlamSpell() {
        super(
            new ImmutableSpellConfigs() {

                @Override
                public Masteries school() {
                    return Masteries.DIVINE;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.PROJECTILE;
                }

                @Override
                public SoundEvent sound() {
                    return SoundEvents.ITEM_ARMOR_EQUIP_IRON;
                }

                @Override
                public Elements element() {
                    return Elements.Physical;
                }
            }.cooldownIfCanceled(true)
                    .rightClickFor(AllowedAsRightClickOn.MELEE_WEAPON)
                    .summonsEntity(w -> new GroundSlamEntity(w))
                    .setSwingArmOnCast().addCastRequirement(SpellPredicates.REQUIRE_MELEE));
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.HEALTH_COST, 0, 0);
        c.set(SC.MANA_COST, 6, 11);
        c.set(SC.ENERGY_COST, 8, 17);
        c.set(SC.MAGIC_SHIELD_COST, 0, 0);
        c.set(SC.BASE_VALUE, 0, 0);
        c.set(SC.ARMOR_ATTACK_SCALE_VALUE, 0.3F, 0.5F);
        c.set(SC.SHOOT_SPEED, 1.0F, 1.5F);
        c.set(SC.PROJECTILE_COUNT, 1, 9);
        c.set(SC.CAST_TIME_TICKS, 0, 0);
        c.set(SC.COOLDOWN_TICKS, 200, 120);
        c.set(SC.TIMES_TO_CAST, 1, 1);
        c.set(SC.DURATION_TICKS, 10, 10);
        c.set(SC.BONUS_HEALTH, 0, 0);

        c.setMaxLevel(16);

        return c;
    }

    public static GroundSlamSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(5, 3);
    }

    @Override
    public String GUID() {
        return "ground_slam";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Attack Spell"));
        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "" + TextFormatting.ITALIC + "Spell that also triggers on-attack effects."));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Projectile"));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent("Converts armor to damage - unleash to"));
        list.add(new StringTextComponent("damage enemies in front of you: "));

        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));

        return list;

    }

    @Override
    public Words getName() {
        return Words.GroundSlam;
    }

    private static class SingletonHolder {
        private static final GroundSlamSpell INSTANCE = new GroundSlamSpell();
    }
}
