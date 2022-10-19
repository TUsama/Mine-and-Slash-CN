package com.robertx22.mine_and_slash.database.spells.spell_classes.physical.linkers;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.entities.single_target_bolt.RagingDragonBallEntity;
import com.robertx22.mine_and_slash.database.spells.entities.single_target_bolt.RimeballEntity;
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
import com.robertx22.mine_and_slash.potion_effects.physical.ComboLinkerEffect;
import com.robertx22.mine_and_slash.potion_effects.physical.ComboStarterEffect;
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
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class RagingDragonLinkerSpell extends BaseSpell {

    private RagingDragonLinkerSpell() {
        super(new ImmutableSpellConfigs() {
            @Override
            public Masteries school() {
                return Masteries.PHYSICAL;
            }

            @Override
            public SpellCastType castType() {
                return SpellCastType.PROJECTILE;
            }

            @Override
            public SoundEvent sound() {
                return SoundEvents.ENTITY_EVOKER_FANGS_ATTACK;
            }

            @Override
            public Elements element() {
                return Elements.Elemental;
            }
        }.summonsEntity(world -> new RagingDragonBallEntity(world)).setSwingArmOnCast().addCastRequirement(SpellPredicates.REQUIRE_MELEE).addCastRequirement(SpellPredicates.REQUIRE_STARTER));
    }

    public static RagingDragonLinkerSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(2, 3);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.HEALTH_COST, 0, 0);
        c.set(SC.MANA_COST, 0, 0);
        c.set(SC.ENERGY_COST, 7.5F, 11.5F);
        c.set(SC.MAGIC_SHIELD_COST, 0, 0);
        c.set(SC.BONUS_HEALTH, 0, 0);
        c.set(SC.PROJECTILE_COUNT, 3, 3);
        c.set(SC.BASE_VALUE, 0, 0);
        c.set(SC.ATTACK_SCALE_VALUE, 2.5F, 2.85F);
        c.set(SC.SHOOT_SPEED, 0.8F, 1.4F);
        c.set(SC.CAST_TIME_TICKS, 0, 0);
        c.set(SC.COOLDOWN_TICKS, 90, 90);
        c.set(SC.CDR_EFFICIENCY, 0, 0);
        c.set(SC.DURATION_TICKS, 50, 50);
        c.set(SC.RADIUS, 4, 4);

        c.setMaxLevel(8);

        return c;
    }

    @Override
    public String GUID() {
        return "raging_dragon_linker";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Attack Spell"));
        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "" + TextFormatting.ITALIC + "Spell that also triggers on-attack effects."));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Area, Duration, Melee, Projectile"));

        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent(TextFormatting.GRAY + "This spell's cooldown is unaffected by"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "cooldown reduction."));
        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent(TextFormatting.GRAY + "Converts Weapon DMG to Fire/Lightning DMG."));
        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent(TextFormatting.GRAY + "Finishing this spell generates: " + ComboLinkerEffect.INSTANCE.locNameForLangFile()));
        list.add(new StringTextComponent(TextFormatting.GRAY + "Finishing this spell expends: " + ComboStarterEffect.INSTANCE.locNameForLangFile()));
        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent("Electrify enemies in front of you by striking"));
        list.add(new StringTextComponent("the ground and conjuring lightning upon your "));
        list.add(new StringTextComponent("foes. Also fire flaming bolts which each deal"));
        list.add(new StringTextComponent("half the damage as fire damage: "));

        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));

        return list;

    }

    @Override
    public Words getName() {
        return Words.RagingDragonLinker;
    }

    @Override
    public void castExtra(SpellCastContext ctx) {

        float RADIUS = ctx.getConfigFor(this)
                .get(SC.RADIUS)
                .get(ctx.spellsCap, this);

        if (ctx.caster instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) ctx.caster;
            player.spawnSweepParticles();
        }

        LivingEntity caster = ctx.caster;

        World world = caster.world;

        List<LivingEntity> entities = EntityFinder.start(caster, LivingEntity.class, caster.getPositionVector())
                .radius(RADIUS * 0.5F)
                .distance(RADIUS)
                .finder(EntityFinder.Finder.IN_FRONT).searchFor(EntityFinder.SearchFor.ENEMIES)
                .build();

        ParticlePacketData pdata = new ParticlePacketData(ctx.caster.getPosition(), ParticleEnum.CHARGED_NOVA);
        pdata.radius = RADIUS;
        ParticleEnum.CHARGED_NOVA.sendToClients(ctx.caster, pdata);

        int num = ctx.getConfigFor(this)
                .getCalc(ctx.spellsCap, this)
                .getCalculatedValue(ctx.data, ctx.spellsCap, this);

        SoundUtils.playSound(caster, SoundEvents.ENTITY_ENDER_DRAGON_GROWL, 0.75F, 1.00F);

        for (LivingEntity en : entities) {

            AttackSpellDamageEffect dmg = new AttackSpellDamageEffect(ctx.caster, en, num, ctx.data, Load.Unit(en),
                    this
            );
            dmg.element = Elements.Thunder;
            dmg.Activate();

            SpellUtils.summonLightningStrike(en);
        }

        SoundUtils.playSound(caster, SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, 0.75F, 1.3F);

        if (PotionEffectUtils.has(ctx.caster, ComboStarterEffect.INSTANCE)) {
            PotionEffectUtils.reduceStacks(ctx.caster, ComboStarterEffect.INSTANCE);
        }
        PotionEffectUtils.reApplyToSelf(ComboLinkerEffect.INSTANCE, ctx.caster);
    }

    private static class SingletonHolder {
        private static final RagingDragonLinkerSpell INSTANCE = new RagingDragonLinkerSpell();
    }
}
