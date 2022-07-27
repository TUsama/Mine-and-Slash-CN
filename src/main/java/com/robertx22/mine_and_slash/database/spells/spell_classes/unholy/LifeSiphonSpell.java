package com.robertx22.mine_and_slash.database.spells.spell_classes.unholy;

import com.robertx22.mine_and_slash.database.spells.entities.single_target_bolt.FrostballEntity;
import com.robertx22.mine_and_slash.database.spells.entities.single_target_bolt.LifeSiphonEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.SpellTooltips;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
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

public class LifeSiphonSpell extends BaseSpell {

    private LifeSiphonSpell() {
        super(
            new ImmutableSpellConfigs() {

                @Override
                public Masteries school() {
                    return Masteries.UNHOLY;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.PROJECTILE;
                }

                @Override
                public SoundEvent sound() {
                    return SoundEvents.ENTITY_ENDER_PEARL_THROW;
                }

                @Override
                public Elements element() {
                    return Elements.Physical;
                }
            }.summonsEntity(world -> new LifeSiphonEntity(world)).setSwingArmOnCast());
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.HEALTH_COST, 0, 0);
        c.set(SC.MANA_COST, 6, 9);
        c.set(SC.ENERGY_COST, 0, 0);
        c.set(SC.MAGIC_SHIELD_COST, 0, 0);
        c.set(SC.BASE_VALUE, 5, 10);
        c.set(SC.SHOOT_SPEED, 1.2F, 1.5F);
        c.set(SC.PROJECTILE_COUNT, 1, 1);
        c.set(SC.TIMES_TO_CAST, 3, 3);
        c.set(SC.CAST_TIME_TICKS, 30, 30);
        c.set(SC.COOLDOWN_SECONDS, 7, 4);
        c.set(SC.BONUS_HEALTH, 0, 0);
        c.set(SC.DURATION_TICKS, 60, 60);

        c.setMaxLevel(12);

        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(2, 3);
    }

    public static LifeSiphonSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "life_siphon";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Spell"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Channel, Duration, Heal, Projectile, Self"));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent("Throw a projectile, damaging first enemy hit"));
        list.add(new StringTextComponent("and healing the caster: "));

        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));

        return list;

    }

    @Override
    public Words getName() {
        return Words.LifeSiphon;
    }

    private static class SingletonHolder {
        private static final LifeSiphonSpell INSTANCE = new LifeSiphonSpell();
    }
}