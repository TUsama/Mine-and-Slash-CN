package com.robertx22.mine_and_slash.database.spells.spell_classes.physical.finishers;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellPredicates;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModSounds;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.physical.*;
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

public class EarthenSmashFinisherSpell extends BaseSpell {

    private EarthenSmashFinisherSpell() {
        super(
            new ImmutableSpellConfigs() {

                @Override
                public Masteries school() {
                    return Masteries.PHYSICAL;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.SPECIAL;
                }

                @Override
                public SoundEvent sound() {
                    return ModSounds.EXPLOSION.get();
                }

                @Override
                public Elements element() {
                    return Elements.Nature;
                }
            }.cooldownIfCanceled(true)
                .setSwingArmOnCast().addCastRequirement(SpellPredicates.REQUIRE_MELEE).addCastRequirement(SpellPredicates.REQUIRE_LINKER));
    }

    @Override
    public void castExtra(SpellCastContext ctx) {

        float radius = ctx.getConfigFor(this)
                .get(SC.RADIUS)
                .get(ctx.spellsCap, this);

        if (ctx.caster instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) ctx.caster;
            player.spawnSweepParticles();
        }

        ctx.caster.world.playSound((PlayerEntity) null, ctx.caster.getPosX(), ctx.caster.getPosY(), ctx.caster.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 1.0F, 1.0F);


        ParticlePacketData pdata = new ParticlePacketData(ctx.caster.getPosition()
                .up(1), ParticleEnum.POISON_CLOUD);
        pdata.radius = radius;
        ParticleEnum.POISON_CLOUD.sendToClients(ctx.caster, pdata);

        List<LivingEntity> entities = EntityFinder.start(ctx.caster, LivingEntity.class, ctx.caster.getPositionVector())
                .radius(radius).searchFor(EntityFinder.SearchFor.ENEMIES)
                .build();

        List<LivingEntity> list = EntityFinder.start(ctx.caster, LivingEntity.class, ctx.caster.getPositionVector())
                .radius(radius).searchFor(EntityFinder.SearchFor.ALLIES)
                .build();

        SoundUtils.playSound(ctx.caster, SoundEvents.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F);

        int num = ctx.getConfigFor(this)
                .getCalc(ctx.spellsCap, this)
                .getCalculatedValue(ctx.data, ctx.spellsCap, this);

        for (LivingEntity en : entities) {

            AttackSpellDamageEffect dmg = new AttackSpellDamageEffect(ctx.caster, en, num, ctx.data, Load.Unit(en),
                this
            );
            dmg.Activate();

            ParticleEnum.sendToClients(
                en.getPosition(), en.world,
                new ParticlePacketData(en.getPositionVector(), ParticleEnum.AOE).radius(1)
                    .motion(new Vec3d(0, 0, 0))
                    .type(ParticleTypes.COMPOSTER)
                    .amount((int) (30)));

        }

        for (LivingEntity en : list) {

            SoundUtils.playSound(en, ModSounds.STONE_CRACK.get(), 1.0F, 1.0F);

            PotionEffectUtils.apply(EarthenShellEffect.INSTANCE, ctx.caster, en);
        }

        if (PotionEffectUtils.has(ctx.caster, ComboLinkerEffect.INSTANCE)) {
            PotionEffectUtils.reduceStacks(ctx.caster, ComboLinkerEffect.INSTANCE);
        }
    }

    public static EarthenSmashFinisherSpell getInstance() {
        return EarthenSmashFinisherSpell.SingletonHolder.INSTANCE;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.HEALTH_COST, 0, 0);
        c.set(SC.MANA_COST, 0, 0);
        c.set(SC.ENERGY_COST, 3, 7);
        c.set(SC.MAGIC_SHIELD_COST, 0, 0);
        c.set(SC.BASE_VALUE, 0, 0);
        c.set(SC.ATTACK_SCALE_VALUE, 4.0F, 6.0F);
        c.set(SC.RADIUS, 4, 6);
        c.set(SC.CAST_TIME_TICKS, 20, 20);
        c.set(SC.COOLDOWN_TICKS, 100, 100);
        c.set(SC.CDR_EFFICIENCY, 0, 0);
        c.set(SC.TIMES_TO_CAST, 1, 1);
        c.set(SC.DURATION_TICKS, 20 * 20, 30 * 20);
        c.set(SC.TICK_RATE, 20, 20);

        c.setMaxLevel(8);

        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(4, 0);
    }

    @Override
    public String GUID() {
        return "earthen_smash_finisher";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Attack Spell"));
        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "" + TextFormatting.ITALIC + "Spell that also triggers on-attack effects."));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Area, Buff, Duration, Melee"));

        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent(TextFormatting.GRAY + "This spell's cooldown is unaffected by"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "cooldown reduction."));
        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent(TextFormatting.GRAY + "Converts Weapon DMG to Nature DMG."));
        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent(TextFormatting.GRAY + "Finishing this spell expends: " + ComboLinkerEffect.INSTANCE.locNameForLangFile()));
        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent("Smash the ground to deal Nature damage to nearby"));
        list.add(new StringTextComponent("enemies. Also increase nearby allies' defenses: "));

        list.addAll(EarthenShellEffect.INSTANCE.GetTooltipStringWithNoExtraSpellInfo(info));

        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));

        return list;

    }

    @Override
    public Words getName() {
        return Words.EarthenSmashFinisher;
    }

    private static class SingletonHolder {
        private static final EarthenSmashFinisherSpell INSTANCE = new EarthenSmashFinisherSpell();
    }
}