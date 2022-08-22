package com.robertx22.mine_and_slash.database.spells.synergies.storm;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.storm.CriticalSurgeSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.storm.PowerSurgeSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.base.OnHitSynergy;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.shaman.CriticalSurgeEffect;
import com.robertx22.mine_and_slash.potion_effects.shaman.PowerSurgeEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.ICrittable;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class CriticalSurgeSplashSynergy extends OnHitSynergy {

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Synergy (Bolt)"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Modifies Critical Surge"));

        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent(TextFormatting.GRAY + "Bolt damage is a special damage type and is"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "unaffected by spell damage modifiers."));
        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent("While Critical Surge is active, critical"));
        list.add(new StringTextComponent("hits have chance to cause the target to"));
        list.add(new StringTextComponent("send out a nova, dealing bolt damage: "));

        list.addAll(getCalc(Load.spells(info.player)).GetTooltipString(info, Load.spells(info.player), this));

        return list;
    }

    @Override
    public void alterSpell(PreCalcSpellConfigs c) {
        c.set(SC.CAST_TIME_TICKS, 20, 20);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.BASE_VALUE, 5, 8);
        c.set(SC.CHANCE, 5, 20);
        c.set(SC.RADIUS, 1.5F, 2.5F);
        c.setMaxLevel(8);
        return c;
    }

    @Override
    public void tryActivate(DamageEffect ctx) {

        float chance = getContext(ctx.source).getConfigFor(this)
                .get(SC.CHANCE)
                .get(Load.spells(ctx.source), this);

        if (PotionEffectUtils.has(ctx.source, CriticalSurgeEffect.INSTANCE)) {

            if (ctx.isCriticalHit() && RandomUtils.roll(chance) && ctx.getEffectType() == EffectData.EffectTypes.SPELL) {

                float radius = getContext(ctx.source).getConfigFor(this)
                        .get(SC.RADIUS)
                        .get(Load.spells(ctx.source), this);

                int num = getCalcVal(ctx.source);

                List<LivingEntity> entities = EntityFinder.start(ctx.source, LivingEntity.class, ctx.target.getPositionVector())
                        .radius(radius).searchFor(EntityFinder.SearchFor.ENEMIES)
                        .build();

                ParticlePacketData pdata = new ParticlePacketData(ctx.target.getPosition()
                        .up(1), ParticleEnum.CHARGED_NOVA);
                pdata.radius = radius;
                ParticleEnum.CHARGED_NOVA.sendToClients(ctx.source, pdata);

                SpellUtils.summonLightningStrike(ctx.target);

                SoundUtils.playSound(ctx.target, SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, 0.75F, 1);

                //SoundUtils.playSound(ctx.target, SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, 0.8F, 1.3F);

                for (LivingEntity en : entities) {
                    DamageEffect dmg = new DamageEffect(
                            null, ctx.source, en, num, EffectData.EffectTypes.BOLT, WeaponTypes.None);
                    dmg.element = Elements.Thunder;
                    dmg.Activate();
                }
            }
        }
    }

    @Override
    public Place getSynergyPlace() {
        return Place.FIRST;
    }

    @Override
    public IAbility getRequiredAbility() {
        return CriticalSurgeSpell.getInstance();
    }

    @Override
    public String locNameForLangFile() {
        return "Voltaic Blast";
    }
}
