package com.robertx22.mine_and_slash.database.spells.spell_classes.unholy;

import com.robertx22.mine_and_slash.database.spells.entities.summons.SkeletonPetEntity;
import com.robertx22.mine_and_slash.database.spells.entities.summons.SpiritWolfPetEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.necromancer.SummonedSkeletonEffect;
import com.robertx22.mine_and_slash.potion_effects.necromancer.SummonedZombieEffect;
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

public class SummonSkeletalArmySpell extends BaseSpell {

    private SummonSkeletalArmySpell() {
        super(
            new ImmutableSpellConfigs() {

                @Override
                public Masteries school() {
                    return Masteries.UNHOLY;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.AT_SIGHT;
                }

                @Override
                public SoundEvent sound() {
                    return SoundEvents.ENTITY_SKELETON_AMBIENT;
                }

                @Override
                public Elements element() {
                    return Elements.Physical;
                }
            }.summonsEntity(w -> new SkeletonPetEntity(w))
                .setSwingArmOnCast());
    }

    public static SummonSkeletalArmySpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.HEALTH_COST, 0.2F, 0.3F);
        c.set(SC.MANA_COST, 12, 16);
        c.set(SC.ENERGY_COST, 0, 0);
        c.set(SC.MAGIC_SHIELD_COST, 0.2F, 0.3F);
        c.set(SC.BASE_VALUE, 25, 42);
        c.set(SC.BONUS_HEALTH, 0F, 0.5F);
        c.set(SC.TIMES_TO_CAST, 3, 6);
        c.set(SC.CAST_TIME_TICKS, 30, 30);
        c.set(SC.COOLDOWN_SECONDS, 30, 24);
        c.set(SC.DURATION_TICKS, 20 * 12, 20 * 16);
        c.set(SC.TICK_RATE, 20, 20);

        c.setMaxLevel(16);

        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(4, 5);
    }

    @Override
    public void castExtra(SpellCastContext ctx) {
        PotionEffectUtils.reApplyToSelf(SummonedSkeletonEffect.INSTANCE, ctx.caster);
    }

    @Override
    public String GUID() {
        return "summon_skeletal_army";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Summon Attack"));
        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "" + TextFormatting.ITALIC + "Summons also triggers on-attack effects."));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Channel, Duration, Entity, Summon"));
        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent("Summon a skeletal army to aid in combat."));
        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));

        return list;

    }

    @Override
    public Words getName() {
        return Words.SummonSkeletalArmy;
    }

    private static class SingletonHolder {
        private static final SummonSkeletalArmySpell INSTANCE = new SummonSkeletalArmySpell();
    }
}
