package com.robertx22.mine_and_slash.database.spells.spell_classes.divine;

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
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class WhirlwindSpell extends BaseSpell {

    private WhirlwindSpell() {
        super(
            new ImmutableSpellConfigs() {

                @Override
                public Masteries school() {
                    return Masteries.DIVINE;
                }

                @Override
                public SpellCastType castType() {
                    return SpellCastType.SPECIAL;
                }

                @Override
                public SoundEvent sound() {
                    return SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP;
                }

                @Override
                public Elements element() {
                    return Elements.Physical;
                }

            }.cooldownIfCanceled(true).setSwingArmOnCast().addCastRequirement(SpellPredicates.REQUIRE_MELEE));
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.HEALTH_COST, 0, 0);
        c.set(SC.MANA_COST, 7, 12);
        c.set(SC.ENERGY_COST, 12, 16);
        c.set(SC.MAGIC_SHIELD_COST, 0, 0);
        c.set(SC.BASE_VALUE, 0, 0);
        c.set(SC.ATTACK_SCALE_VALUE, 0.5F, 1.0F);
        c.set(SC.CAST_TIME_TICKS, 120, 120);
        c.set(SC.COOLDOWN_SECONDS, 8, 6);
        c.set(SC.RADIUS, 3F, 4F);
        c.set(SC.TIMES_TO_CAST, 20, 30);

        c.setMaxLevel(16);

        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(7, 4);
    }

    public static WhirlwindSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "whirlwind";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Attack Spell"));
        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "" + TextFormatting.ITALIC + "Spell that also triggers on-attack effects."));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Area, Channel"));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent(TextFormatting.GRAY + "Converts Weapon DMG to Phys."));
        TooltipUtils.addEmpty(list);
        list.add(new SText("Spin to do damage to enemies around you: "));

        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));

        return list;

    }

    public void damageMobsAroundYou(SpellCastContext ctx, LivingEntity caster) {

        if (!caster.world.isRemote) {

            float radius = ctx.getConfigFor(this)
                .get(SC.RADIUS)
                .get(ctx.spellsCap, this);

            ParticlePacketData pdata = new ParticlePacketData(caster.getPosition()
                .up(1), ParticleEnum.WHIRLWIND);
            pdata.radius = radius;
            ParticleEnum.WHIRLWIND.sendToClients(caster, pdata);

            int num = getCalculation(ctx).getCalculatedValue(Load.Unit(caster), ctx.spellsCap, ctx.ability);

            List<LivingEntity> entities = EntityFinder.start(caster, LivingEntity.class, caster.getPositionVector())
                .radius(radius).searchFor(EntityFinder.SearchFor.ENEMIES)
                .build();

            for (LivingEntity en : entities) {
                AttackSpellDamageEffect dmg = new AttackSpellDamageEffect(ctx.caster, en, num, ctx.data, Load.Unit(en),
                        this
                );
                dmg.removeKnockback();
                dmg.Activate();

            }
        }
    }

    @Override
    public Words getName() {
        return Words.Whirlwind;
    }

    @Override
    public void castExtra(SpellCastContext ctx) {

        damageMobsAroundYou(ctx, ctx.caster);

        SoundUtils.playSound(ctx.caster, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, 0.7F, 2.0F);

    }

    private static class SingletonHolder {
        private static final WhirlwindSpell INSTANCE = new WhirlwindSpell();
    }
}
