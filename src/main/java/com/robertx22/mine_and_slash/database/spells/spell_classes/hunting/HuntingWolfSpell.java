package com.robertx22.mine_and_slash.database.spells.spell_classes.hunting;

import com.robertx22.mine_and_slash.database.spells.entities.proj.SnareTrapEntity;
import com.robertx22.mine_and_slash.database.spells.entities.summons.SpiritWolfPetEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.necromancer.SummonedZombieEffect;
import com.robertx22.mine_and_slash.potion_effects.ranger.SnareEffect;
import com.robertx22.mine_and_slash.potion_effects.ranger.SummonedWolfEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.NumberUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import javax.xml.soap.Text;
import java.util.ArrayList;
import java.util.List;

public class HuntingWolfSpell extends BaseSpell {

    private HuntingWolfSpell() {
        super(
            new ImmutableSpellConfigs() {

                @Override
                public Masteries school() {
                    return Masteries.HUNTING;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.AT_SIGHT;
                }

                @Override
                public SoundEvent sound() {
                    return SoundEvents.ENTITY_WOLF_HOWL;
                }

                @Override
                public Elements element() {
                    return Elements.Physical;
                }
            }.summonsEntity(w -> new SpiritWolfPetEntity(w))
                .setSwingArmOnCast());
    }

    public static HuntingWolfSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.HEALTH_COST, 0, 0);
        c.set(SC.MANA_COST, 16, 24);
        c.set(SC.ENERGY_COST, 0, 0);
        c.set(SC.MAGIC_SHIELD_COST, 0, 0);
        c.set(SC.BASE_VALUE, 1, 3);
        c.set(SC.PHYSICAL_ATTACK_SCALE_VALUE, 0.5F, 2.25F);
        c.set(SC.BONUS_HEALTH, 0F, 3.0F);
        c.set(SC.CAST_TIME_TICKS, 60, 60);
        c.set(SC.COOLDOWN_SECONDS, 45, 45);
        c.set(SC.DURATION_TICKS, 20 * 60, 20 * 90);
        c.set(SC.TICK_RATE, 20, 20);

        c.setMaxLevel(16);

        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(5, 1);
    }

    @Override
    public void castExtra(SpellCastContext ctx) {
        PotionEffectUtils.reApplyToSelf(SummonedWolfEffect.INSTANCE, ctx.caster);
    }

    @Override
    public String GUID() {
        return "summon_hunting_wolf";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Summon Attack"));
        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "" + TextFormatting.ITALIC + "Summons also triggers on-attack effects."));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Duration, Entity, Summon"));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent("Summon a hunting wolf that aids in combat."));
        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));

        return list;

    }

    @Override
    public Words getName() {
        return Words.HuntingWolf;
    }

    private static class SingletonHolder {
        private static final HuntingWolfSpell INSTANCE = new HuntingWolfSpell();
    }
}
