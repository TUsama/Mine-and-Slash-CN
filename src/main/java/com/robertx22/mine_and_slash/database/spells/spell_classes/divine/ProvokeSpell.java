package com.robertx22.mine_and_slash.database.spells.spell_classes.divine;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.divine.EnrageEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class ProvokeSpell extends BaseSpell {

    private ProvokeSpell() {
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
                    return SoundEvents.ENTITY_POLAR_BEAR_WARNING;
                }

                @Override
                public Elements element() {
                    return Elements.Physical;
                }

            }.setSwingArmOnCast());
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.HEALTH_COST, 0, 0);
        c.set(SC.MANA_COST, 14, 3);
        c.set(SC.ENERGY_COST, 0, 0);
        c.set(SC.MAGIC_SHIELD_COST, 0, 0);
        c.set(SC.BASE_VALUE, 0, 0);
        c.set(SC.CAST_TIME_TICKS, 0, 0);
        c.set(SC.COOLDOWN_SECONDS, 14, 7);
        c.set(SC.AMOUNT,1, 5);
        c.set(SC.RADIUS, 3, 7);
        c.set(SC.TIMES_TO_CAST, 1, 1);
        c.set(SC.TICK_RATE, 20, 20);

        c.setMaxLevel(8);

        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(5,1);
    }

    public static ProvokeSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "provoke";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Spell"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Area, Debuff, Taunt"));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent("Draw the attention of nearby enemies by provoking them."));
        list.add(new StringTextComponent("Also applies: "));

        list.addAll(EnrageEffect.INSTANCE.GetTooltipStringWithNoExtraSpellInfo(info));

        return list;

    }

    public void damageMobsAroundYou(SpellCastContext ctx, LivingEntity caster) {


        float amount = ((int) ctx.getConfigFor(this)
                .get(SC.AMOUNT).get(Load.spells(ctx.caster), this)) - 1;

        if (!caster.world.isRemote) {

            float radius = ctx.getConfigFor(this)
                .get(SC.RADIUS)
                .get(ctx.spellsCap, this);

            ParticlePacketData pdata = new ParticlePacketData(caster.getPosition()
                .up(1), ParticleEnum.PROVOKE);
            pdata.radius = radius;
            ParticleEnum.PROVOKE.sendToClients(caster, pdata);

            List<LivingEntity> entities = EntityFinder.start(caster, LivingEntity.class, caster.getPositionVector())
                .radius(radius)
                    .searchFor(EntityFinder.SearchFor.ENEMIES)
                .build();

            for (LivingEntity en : entities) {

                if (en instanceof MobEntity) {
                    en.setRevengeTarget(ctx.caster);
                    ((MobEntity) en).setAttackTarget(ctx.caster);
                }

                for (int i = 0; i < amount; i++) {
                    PotionEffectUtils.apply(EnrageEffect.INSTANCE, ctx.caster, en);
                }

            }
        }
    }

    @Override
    public Words getName() {
        return Words.Provoke;
    }

    @Override
    public void castExtra(SpellCastContext ctx) {

        damageMobsAroundYou(ctx, ctx.caster);

        SoundUtils.playSound(ctx.caster, SoundEvents.ENTITY_POLAR_BEAR_WARNING, 0.7F, 1.2F);

    }

    private static class SingletonHolder {
        private static final ProvokeSpell INSTANCE = new ProvokeSpell();
    }
}
