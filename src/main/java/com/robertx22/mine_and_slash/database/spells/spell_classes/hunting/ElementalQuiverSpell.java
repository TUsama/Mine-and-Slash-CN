package com.robertx22.mine_and_slash.database.spells.spell_classes.hunting;

import com.robertx22.mine_and_slash.database.spells.spell_classes.SpellTooltips;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.physical.ComboLinkerEffect;
import com.robertx22.mine_and_slash.potion_effects.physical.ComboStarterEffect;
import com.robertx22.mine_and_slash.potion_effects.physical.RallyEffect;
import com.robertx22.mine_and_slash.potion_effects.ranger.ExertEffect;
import com.robertx22.mine_and_slash.potion_effects.ranger.ImbueEffect;
import com.robertx22.mine_and_slash.potion_effects.ranger.elemental.FireQuiverEffect;
import com.robertx22.mine_and_slash.potion_effects.ranger.elemental.FrostQuiverEffect;
import com.robertx22.mine_and_slash.potion_effects.ranger.elemental.LightningQuiverEffect;
import com.robertx22.mine_and_slash.potion_effects.ranger.elemental.NatureQuiverEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.AttackSpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class ElementalQuiverSpell extends BaseSpell {

    private ElementalQuiverSpell() {
        super(
            new ImmutableSpellConfigs() {
                @Override
                public Masteries school() {
                    return Masteries.HUNTING;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.SPECIAL;
                }

                @Override
                public SoundEvent sound() {
                    return SoundEvents.ITEM_CROSSBOW_QUICK_CHARGE_3;
                }

                @Override
                public Elements element() {
                    return Elements.Elemental;
                }
            }.setSwingArmOnCast());

    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.HEALTH_COST, 0, 0);
        c.set(SC.MANA_COST, 5, 7);
        c.set(SC.ENERGY_COST, 0, 0);
        c.set(SC.MAGIC_SHIELD_COST, 0, 0);
        c.set(SC.BASE_VALUE, 0, 0);
        c.set(SC.CAST_TIME_TICKS, 20, 20);
        c.set(SC.COOLDOWN_SECONDS, 1, 1);
        c.set(SC.DURATION_TICKS, 20 * 180, 20 * 300);

        c.setMaxLevel(4);
        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(3, 2);
    }

    public static ElementalQuiverSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "elemental_quiver";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Spell"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Buff, Duration, Self"));

        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent("Applies a buff that changes the element of your"));
        list.add(new StringTextComponent("Hunting tree arrow spells. This buff also converts"));
        list.add(new StringTextComponent("your Phys ATK to that element. Cast this spell to"));
        list.add(new StringTextComponent("cycle through the elements (see potion effects"));
        list.add(new StringTextComponent("bar for element indicator). The order is: "));
        list.add(new StringTextComponent("Frost > Fire > Lightning > Nature > Off"));

        list.add(SpellTooltips.buff());

        list.addAll(FrostQuiverEffect.INSTANCE
                .GetTooltipStringWithNoExtraSpellInfo(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.ElementalQuiver;
    }

    private static class SingletonHolder {
        private static final ElementalQuiverSpell INSTANCE = new ElementalQuiverSpell();
    }

    @Override
    public void castExtra(SpellCastContext ctx) {

        ctx.caster.world.playSound((PlayerEntity) null, ctx.caster.getPosX(), ctx.caster.getPosY(), ctx.caster.getPosZ(), SoundEvents.ITEM_CROSSBOW_LOADING_END, SoundCategory.PLAYERS, 1.0F, 1.0F);


        if (PotionEffectUtils.has(ctx.caster, FrostQuiverEffect.INSTANCE)) {
            PotionEffectUtils.reduceStacks(ctx.caster, FrostQuiverEffect.INSTANCE);
            PotionEffectUtils.reApplyToSelf(FireQuiverEffect.INSTANCE, ctx.caster);

            ParticlePacketData pdata3 = new ParticlePacketData(ctx.caster.getPosition()
                    .up(1), ParticleEnum.BLAZING_INFERNO);
            ParticleEnum.BLAZING_INFERNO.sendToClients(ctx.caster, pdata3);

        } else if (PotionEffectUtils.has(ctx.caster, FireQuiverEffect.INSTANCE)) {
            PotionEffectUtils.reduceStacks(ctx.caster, FireQuiverEffect.INSTANCE);
            PotionEffectUtils.reApplyToSelf(LightningQuiverEffect.INSTANCE, ctx.caster);

            ParticlePacketData pdata = new ParticlePacketData(ctx.caster.getPosition()
                    .up(1), ParticleEnum.CHARGED_NOVA);
            ParticleEnum.CHARGED_NOVA.sendToClients(ctx.caster, pdata);

        } else if (PotionEffectUtils.has(ctx.caster, LightningQuiverEffect.INSTANCE)) {
            PotionEffectUtils.reduceStacks(ctx.caster, LightningQuiverEffect.INSTANCE);
            PotionEffectUtils.reApplyToSelf(NatureQuiverEffect.INSTANCE, ctx.caster);

            ParticlePacketData pdata4 = new ParticlePacketData(ctx.caster.getPosition()
                    .up(1), ParticleEnum.POISON_CLOUD);
            ParticleEnum.POISON_CLOUD.sendToClients(ctx.caster, pdata4);

        } else if (PotionEffectUtils.has(ctx.caster, NatureQuiverEffect.INSTANCE)) {
            PotionEffectUtils.reduceStacks(ctx.caster, NatureQuiverEffect.INSTANCE);

        } else {
            PotionEffectUtils.reApplyToSelf(FrostQuiverEffect.INSTANCE, ctx.caster);

            ParticlePacketData pdata2 = new ParticlePacketData(ctx.caster.getPosition()
                    .up(1), ParticleEnum.SIMPLE_FROST_NOVA);
            ParticleEnum.SIMPLE_FROST_NOVA.sendToClients(ctx.caster, pdata2);

        }
    }
}