package com.robertx22.mine_and_slash.database.spells.spell_classes.nature;

import com.robertx22.mine_and_slash.database.spells.entities.single_target_bolt.PoisonBallEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.SpellTooltips;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.potion_effects.druid.ThornsEffect;
import com.robertx22.mine_and_slash.potion_effects.ember_mage.BurnEffect;
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

public class PoisonBallSpell extends BaseSpell {

    private PoisonBallSpell() {
        super(
            new ImmutableSpellConfigs() {

                @Override
                public Masteries school() {
                    return Masteries.NATURE;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.PROJECTILE;
                }

                @Override
                public SoundEvent sound() {
                    return SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE;
                }

                @Override
                public Elements element() {
                    return Elements.Nature;
                }
            }.rightClickFor(AllowedAsRightClickOn.MAGE_WEAPON)
                .summonsEntity(world -> new PoisonBallEntity(world)).setSwingArmOnCast());
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.HEALTH_COST, 0, 0);
        c.set(SC.MANA_COST, 10, 14);
        c.set(SC.ENERGY_COST, 0, 0);
        c.set(SC.MAGIC_SHIELD_COST, 0, 0);
        c.set(SC.BASE_VALUE, 5, 10);
        c.set(SC.ATTACK_SCALE_VALUE, 0.65F, 1.0F);
        c.set(SC.SHOOT_SPEED, 0.25F, 0.25F);
        c.set(SC.PROJECTILE_COUNT, 3, 3);
        c.set(SC.CAST_TIME_TICKS, 0, 0);
        c.set(SC.COOLDOWN_TICKS, 60, 60);
        c.set(SC.CDR_EFFICIENCY, 0, 0);
        c.set(SC.RADIUS, 2F, 2F);
        c.set(SC.DURATION_TICKS, 60, 120);
        c.set(SC.BONUS_HEALTH, 0, 0);
        c.set(SC.TICK_RATE, 10, 10);


        c.setMaxLevel(12);

        return c;
    }

    public static PoisonBallSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "poison_ball";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Spell"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Area, Duration, Pierce, Projectile"));

        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent(TextFormatting.GRAY + "This spell's cooldown is unaffected by"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "cooldown reduction."));
        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent(TextFormatting.GRAY + "Converts Weapon DMG to Nature DMG."));
        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent("Throw a projectile, damaging enemies in its path : "));

        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));

        return list;

    }

    @Override
    public Words getName() {
        return Words.PoisonBall;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(4, 2);
    }

    private static class SingletonHolder {
        private static final PoisonBallSpell INSTANCE = new PoisonBallSpell();
    }
}