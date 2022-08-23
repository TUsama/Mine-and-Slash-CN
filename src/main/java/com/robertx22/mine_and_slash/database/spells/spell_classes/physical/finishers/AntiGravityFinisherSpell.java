package com.robertx22.mine_and_slash.database.spells.spell_classes.physical.finishers;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellPredicates;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.physical.ComboLinkerEffect;
import com.robertx22.mine_and_slash.potion_effects.physical.EmpowerEffect;
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
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class AntiGravityFinisherSpell extends BaseSpell {

    private AntiGravityFinisherSpell() {
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
                    return SoundEvents.ENTITY_EVOKER_FANGS_ATTACK;
                }

                @Override
                public Elements element() {
                    return Elements.Physical;
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
                .up(1), ParticleEnum.MAGIC_BURST);
        pdata.radius = radius;
        ParticleEnum.MAGIC_BURST.sendToClients(ctx.caster, pdata);

        List<LivingEntity> entities = EntityFinder.start(ctx.caster, LivingEntity.class, ctx.caster.getPositionVector())
                .radius(radius).searchFor(EntityFinder.SearchFor.ENEMIES)
                .build();

        SoundUtils.playSound(ctx.caster, SoundEvents.ENTITY_ENDERMAN_DEATH, 1.1F, 0.75F);

        int num = ctx.getConfigFor(this)
                .getCalc(ctx.spellsCap, this)
                .getCalculatedValue(ctx.data, ctx.spellsCap, this);

        int duration = (int) ctx.getConfigFor(this)
                .get(SC.DURATION_TICKS)
                .get(ctx.spellsCap, this);

        for (LivingEntity en : entities) {

            AttackSpellDamageEffect dmg = new AttackSpellDamageEffect(ctx.caster, en, num, ctx.data, Load.Unit(en),
                this
            );
            dmg.Activate();

            en.addPotionEffect(new EffectInstance(Effects.LEVITATION, duration, 0));

            ParticleEnum.sendToClients(
                en.getPosition(), en.world,
                new ParticlePacketData(en.getPositionVector(), ParticleEnum.AOE).radius(1)
                    .motion(new Vec3d(0, 0, 0))
                    .type(ParticleTypes.WITCH)
                    .amount((int) (30)));

        }

        if (PotionEffectUtils.has(ctx.caster, ComboLinkerEffect.INSTANCE)) {
            PotionEffectUtils.reduceStacks(ctx.caster, ComboLinkerEffect.INSTANCE);
        }
    }

    public static AntiGravityFinisherSpell getInstance() {
        return AntiGravityFinisherSpell.SingletonHolder.INSTANCE;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.HEALTH_COST, 0, 0);
        c.set(SC.MANA_COST, 0, 0);
        c.set(SC.ENERGY_COST, 5, 8);
        c.set(SC.MAGIC_SHIELD_COST, 0, 0);
        c.set(SC.BASE_VALUE, 0, 0);
        c.set(SC.ATTACK_SCALE_VALUE, 4.25F, 6.25F);
        c.set(SC.RADIUS, 3, 5);
        c.set(SC.CAST_TIME_TICKS, 20, 20);
        c.set(SC.COOLDOWN_TICKS, 60, 60);
        c.set(SC.CDR_EFFICIENCY, 0, 0);
        c.set(SC.TIMES_TO_CAST, 1, 1);
        c.set(SC.DURATION_TICKS, 60, 100);

        c.setMaxLevel(8);

        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(4, 4);
    }

    @Override
    public String GUID() {
        return "anti_gravity_finisher";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Attack Spell"));
        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "" + TextFormatting.ITALIC + "Spell that also triggers on-attack effects."));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Area, Debuff, Melee"));

        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent(TextFormatting.GRAY + "This spell's cooldown is unaffected by"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "cooldown reduction."));
        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent(TextFormatting.GRAY + "Converts Weapon DMG to Phys DMG."));
        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent(TextFormatting.GRAY + "Finishing this spell expends: " + ComboLinkerEffect.INSTANCE.locNameForLangFile()));
        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent("Channel the arcane and launch your foes into"));
        list.add(new StringTextComponent("the air for a few seconds. Enemies hit with"));
        list.add(new StringTextComponent("this spell will levitate momentarily: "));

        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));

        return list;

    }

    @Override
    public Words getName() {
        return Words.AntiGravityFinisher;
    }

    private static class SingletonHolder {
        private static final AntiGravityFinisherSpell INSTANCE = new AntiGravityFinisherSpell();
    }
}