package com.robertx22.mine_and_slash.database.spells.spell_classes.unholy;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.druid.PetrifyEffect;
import com.robertx22.mine_and_slash.potion_effects.druid.ThornsEffect;
import com.robertx22.mine_and_slash.potion_effects.necromancer.BlightEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
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

public class BlightSpell extends BaseSpell {

    private BlightSpell() {
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
                    return SoundEvents.ENTITY_PLAYER_SPLASH_HIGH_SPEED;
                }

                @Override
                public Elements element() {
                    return Elements.Nature;
                }

            }.setSwingArmOnCast());
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.HEALTH_COST, 0.05F, 0.15F);
        c.set(SC.MANA_COST, 7, 11);
        c.set(SC.ENERGY_COST, 0, 0);
        c.set(SC.MAGIC_SHIELD_COST, 0.05F, 0.15F);
        c.set(SC.BASE_VALUE, 23, 34);
        c.set(SC.CAST_TIME_TICKS, 20, 20);
        c.set(SC.COOLDOWN_SECONDS, 9, 6);
        c.set(SC.RADIUS, 4, 6);
        c.set(SC.TIMES_TO_CAST, 1, 1);
        c.set(SC.TICK_RATE, 30, 30);
        c.set(SC.DURATION_TICKS, 200, 200);

        c.setMaxLevel(12);

        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return new AbilityPlace(1,2);
    }

    public static BlightSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "blight";
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Spell"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Area, Debuff, Duration"));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent("Infect nearby enemies with your blood."));
        list.add(new StringTextComponent("Applies: "));

        list.addAll(BlightEffect.INSTANCE.GetTooltipStringWithNoExtraSpellInfo(info));

        return list;

    }

    public void damageMobsAroundYou(SpellCastContext ctx, LivingEntity caster) {

        if (!caster.world.isRemote) {

            float radius = ctx.getConfigFor(this)
                .get(SC.RADIUS)
                .get(ctx.spellsCap, this);

            ParticlePacketData pdata = new ParticlePacketData(caster.getPosition()
                .up(1), ParticleEnum.POISON_CLOUD);
            pdata.radius = radius;
            ParticleEnum.POISON_CLOUD.sendToClients(caster, pdata);

            List<LivingEntity> entities = EntityFinder.start(caster, LivingEntity.class, caster.getPositionVector())
                .radius(radius)
                    .searchFor(EntityFinder.SearchFor.ENEMIES)
                .build();

            SoundUtils.playSound(ctx.caster, SoundEvents.ENTITY_WITCH_DEATH, 1, 0.8F);

            for (LivingEntity en : entities) {
                PotionEffectUtils.apply(BlightEffect.INSTANCE, caster, en);
            }
        }
    }

    @Override
    public Words getName() {
        return Words.Blight;
    }

    @Override
    public void castExtra(SpellCastContext ctx) {

        damageMobsAroundYou(ctx, ctx.caster);

        SoundUtils.playSound(ctx.caster, SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, 1, 0.8F);

    }

    private static class SingletonHolder {
        private static final BlightSpell INSTANCE = new BlightSpell();
    }
}
