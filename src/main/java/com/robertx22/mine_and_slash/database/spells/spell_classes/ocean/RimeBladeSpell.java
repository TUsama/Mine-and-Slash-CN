package com.robertx22.mine_and_slash.database.spells.spell_classes.ocean;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.entities.single_target_bolt.ChainLightningEntity;
import com.robertx22.mine_and_slash.database.spells.entities.single_target_bolt.FireballEntity;
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
import com.robertx22.mine_and_slash.potion_effects.shaman.ChainLightningEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.AttackSpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.command.arguments.EntityAnchorArgument;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ArmorStandEntity;
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

public class RimeBladeSpell extends BaseSpell {

    private RimeBladeSpell() {
        super(new ImmutableSpellConfigs() {
            @Override
            public Masteries school() {
                return Masteries.OCEAN;
            }

            @Override
            public SpellCastType castType() {
                return SpellCastType.PROJECTILE;
            }

            @Override
            public SoundEvent sound() {
                return ModSounds.FREEZE.get();
            }

            @Override
            public Elements element() {
                return Elements.Water;
            }
        }.summonsEntity(world -> new RimeballEntity(world)).setSwingArmOnCast().rightClickFor(AllowedAsRightClickOn.MELEE_WEAPON).addCastRequirement(SpellPredicates.REQUIRE_MELEE));
    }

    public static RimeBladeSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(2, 4);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.HEALTH_COST, 0, 0);
        c.set(SC.MANA_COST, 11, 18);
        c.set(SC.ENERGY_COST, 4, 6);
        c.set(SC.MAGIC_SHIELD_COST, 0, 0);
        c.set(SC.BONUS_HEALTH, 0, 0);
        c.set(SC.PROJECTILE_COUNT, 3, 3);
        c.set(SC.BASE_VALUE, 3, 6);
        c.set(SC.ATTACK_SCALE_VALUE, 0.7F, 1.0F);
        c.set(SC.SHOOT_SPEED, 0.8F, 1.4F);
        c.set(SC.CAST_TIME_TICKS, 0, 0);
        c.set(SC.COOLDOWN_SECONDS, 7, 4);
        c.set(SC.DURATION_TICKS, 50, 50);
        c.set(SC.RADIUS, 4, 6);

        c.setMaxLevel(12);

        return c;
    }

    @Override
    public String GUID() {
        return "rime_blade";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Attack Spell, Spell"));
        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "" + TextFormatting.ITALIC + "Spell that also triggers on-attack effects."));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Area, Duration, Melee, Projectile"));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent(TextFormatting.GRAY + "Converts Weapon DMG to Frost DMG."));
        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent("Damage enemies in front of you. Also fire"));
        list.add(new StringTextComponent("icy projectiles which deal half the damage"));
        list.add(new StringTextComponent("as spell damage: "));

        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));

        return list;

    }

    @Override
    public Words getName() {
        return Words.RimeBlade;
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
                            .type(ParticleTypes.ITEM_SNOWBALL)
                            .amount((int) (60)));
        }

        SoundUtils.playSound(caster, SoundEvents.BLOCK_GLASS_BREAK, 1.2F, 1.3F);
    }

    private static class SingletonHolder {
        private static final RimeBladeSpell INSTANCE = new RimeBladeSpell();
    }
}
