package com.robertx22.mine_and_slash.database.spells.spell_classes.hunting;

import com.robertx22.mine_and_slash.database.spells.entities.proj.RangerArrowEntity;
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

public class ArrowBarrageSpell extends BaseSpell {

    private ArrowBarrageSpell() {
        super(
            new ImmutableSpellConfigs() {

                @Override
                public Masteries school() {
                    return Masteries.HUNTING;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.PROJECTILE;
                }

                @Override
                public SoundEvent sound() {
                    return SoundEvents.ENTITY_ARROW_SHOOT;
                }

                @Override
                public Elements element() {
                    return Elements.Physical;
                }
            }.addCastRequirement(SpellPredicates.REQUIRE_SHOOTABLE)
                .cooldownIfCanceled(true)
                .summonsEntity(world -> new RangerArrowEntity(world)).setSwingArmOnCast());
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.HEALTH_COST, 0, 0);
        c.set(SC.MANA_COST, 4, 6);
        c.set(SC.ENERGY_COST, 5, 7);
        c.set(SC.MAGIC_SHIELD_COST, 0, 0);
        c.set(SC.BASE_VALUE, 0, 0);
        c.set(SC.ATTACK_SCALE_VALUE, 1.2F, 1.6F);
        c.set(SC.PROJECTILE_COUNT, 1, 1);
        c.set(SC.SHOOT_SPEED, 3.0F, 3.0F);
        c.set(SC.CAST_TIME_TICKS, 40, 40);
        c.set(SC.COOLDOWN_SECONDS, 5, 4);
        c.set(SC.TIMES_TO_CAST, 4, 10);
        c.set(SC.BONUS_HEALTH, 0, 0);
        c.set(SC.DURATION_TICKS, 80, 80);

        c.setMaxLevel(16);

        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(0, 4);
    }

    public static ArrowBarrageSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "arrow_barrage";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Attack Spell"));
        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "" + TextFormatting.ITALIC + "Spell that also triggers on-attack effects."));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Arrow, Channel, Projectile"));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent(TextFormatting.GRAY + "Converts Weapon DMG to Phys DMG."));
        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent("Shoots out many arrows while casting."));
        list.add(new StringTextComponent("Requires Bow/Crossbow to use: "));

        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));

        return list;

    }

    @Override
    public Words getName() {
        return Words.ArrowBarrage;
    }

    private static class SingletonHolder {
        private static final ArrowBarrageSpell INSTANCE = new ArrowBarrageSpell();
    }
}
