package com.robertx22.mine_and_slash.database.spells.spell_classes.unholy;

import com.robertx22.mine_and_slash.database.spells.entities.summons.SpiritWolfPetEntity;
import com.robertx22.mine_and_slash.database.spells.entities.summons.ZombiePetEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.necromancer.BlightEffect;
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

public class SummonZombieSpell extends BaseSpell {

    private SummonZombieSpell() {
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
                    return SoundEvents.ENTITY_ZOMBIE_AMBIENT;
                }

                @Override
                public Elements element() {
                    return Elements.Physical;
                }
            }.summonsEntity(w -> new ZombiePetEntity(w))
                .setSwingArmOnCast());
    }

    public static SummonZombieSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.HEALTH_COST, 0.1F, 0.2F);
        c.set(SC.MANA_COST, 12, 18);
        c.set(SC.ENERGY_COST, 0, 0);
        c.set(SC.MAGIC_SHIELD_COST, 0.1F, 0.2F);
        c.set(SC.BASE_VALUE, 3, 18);
        c.set(SC.ATTACK_SCALE_VALUE, 0.25F, 0.85F);
        c.set(SC.BONUS_HEALTH, 0F, 2.0F);
        c.set(SC.CAST_TIME_TICKS, 40, 40);
        c.set(SC.COOLDOWN_SECONDS, 30, 30);
        c.set(SC.DURATION_TICKS, 20 * 60, 20 * 90);
        c.set(SC.TICK_RATE, 20, 20);

        c.setMaxLevel(16);

        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(3, 1);
    }

    @Override
    public String GUID() {
        return "summon_zombie";
    }

    @Override
    public void castExtra(SpellCastContext ctx) {
        PotionEffectUtils.reApplyToSelf(SummonedZombieEffect.INSTANCE, ctx.caster);
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Summon Attack"));
        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "" + TextFormatting.ITALIC + "Summons also triggers on-attack effects."));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Duration, Entity, Summon"));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent(TextFormatting.GRAY + "Converts Weapon DMG to Phys."));
        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent("Summon a zombie that aids in combat."));
        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));

        return list;

    }

    @Override
    public Words getName() {
        return Words.SummonZombie;
    }

    private static class SingletonHolder {
        private static final SummonZombieSpell INSTANCE = new SummonZombieSpell();
    }
}
