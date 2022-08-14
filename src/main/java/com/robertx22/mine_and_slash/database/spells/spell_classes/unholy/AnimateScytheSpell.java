package com.robertx22.mine_and_slash.database.spells.spell_classes.unholy;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellPredicates;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
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

public class AnimateScytheSpell extends BaseSpell {

    private AnimateScytheSpell() {
        super(
            new ImmutableSpellConfigs() {

                @Override
                public Masteries school() {
                    return Masteries.UNHOLY;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.SPECIAL;
                }

                @Override
                public SoundEvent sound() {
                    return SoundEvents.ENTITY_ENDERMAN_TELEPORT;
                }

                @Override
                public Elements element() {
                    return Elements.Physical;
                }
            }.cooldownIfCanceled(true)
                .rightClickFor(AllowedAsRightClickOn.MELEE_WEAPON)
                .setSwingArmOnCast().addCastRequirement(SpellPredicates.REQUIRE_MELEE));
    }

    @Override
    public void castExtra(SpellCastContext ctx) {

        if (ctx.caster instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) ctx.caster;
            player.spawnSweepParticles();
        }

        ctx.caster.world.playSound((PlayerEntity) null, ctx.caster.getPosX(), ctx.caster.getPosY(), ctx.caster.getPosZ(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 1.0F, 1.0F);

        Vec3d look = ctx.caster.getLookVec()
            .scale(3);

        List<LivingEntity> list = EntityFinder.start(ctx.caster, LivingEntity.class, ctx.caster.getPositionVector()
            .add(look)
            .add(0, ctx.caster.getHeight() / 2, 0))
            .finder(EntityFinder.Finder.RADIUS)
            .radius(2)
            .height(2)
            .build();

        SoundUtils.playSound(ctx.caster, SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1, 0.8F);

        for (LivingEntity en : list) {

            int num = ctx.getConfigFor(this)
                .getCalc(ctx.spellsCap, this)
                .getCalculatedValue(ctx.data, ctx.spellsCap, this);

            AttackSpellDamageEffect dmg = new AttackSpellDamageEffect(ctx.caster, en, num, ctx.data, Load.Unit(en),
                this
            );
            dmg.Activate();
            dmg.Activate();

            ParticleEnum.sendToClients(
                en.getPosition(), en.world,
                new ParticlePacketData(en.getPositionVector(), ParticleEnum.AOE).radius(1)
                    .motion(new Vec3d(0, 0, 0))
                    .type(ParticleTypes.WITCH)
                    .amount((int) (30)));

            ParticleEnum.sendToClients(
                    en.getPosition(), en.world,
                    new ParticlePacketData(en.getPositionVector(), ParticleEnum.AOE).radius(1)
                            .motion(new Vec3d(0, 0, 0))
                            .type(ParticleTypes.COMPOSTER)
                            .amount((int) (30)));

        }
    }

    public static AnimateScytheSpell getInstance() {
        return AnimateScytheSpell.SingletonHolder.INSTANCE;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.HEALTH_COST, 0.04F, 0.06F);
        c.set(SC.MANA_COST, 0, 0);
        c.set(SC.ENERGY_COST, 2, 3);
        c.set(SC.MAGIC_SHIELD_COST, 0, 0);
        c.set(SC.BASE_VALUE, 1, 1);
        c.set(SC.HEALTH_ATTACK_SCALE_VALUE, 0.08F, 0.18F);
        c.set(SC.COOLDOWN_SECONDS, 4, 2);
        c.set(SC.CAST_TIME_TICKS, 0, 0);

        c.setMaxLevel(16);

        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(5, 1);
    }

    @Override
    public String GUID() {
        return "animate_scythe";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Attack Spell"));
        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "" + TextFormatting.ITALIC + "Spell that also triggers on-attack effects."));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Melee"));

        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent(TextFormatting.GRAY + "Converts Health to Phys DMG."));
        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent("Conjure an animated scythe using your own"));
        list.add(new StringTextComponent("health to attack enemies twice in front of you: "));

        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));

        return list;

    }

    @Override
    public Words getName() {
        return Words.AnimateScythe;
    }

    private static class SingletonHolder {
        private static final AnimateScytheSpell INSTANCE = new AnimateScytheSpell();
    }
}