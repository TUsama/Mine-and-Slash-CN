package com.robertx22.mine_and_slash.database.spells.synergies.fire;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.fire.SpellBladeSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.base.OnBasicAttackSynergy;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.ember_mage.SpellBladeEffect;
import com.robertx22.mine_and_slash.potion_effects.shaman.PowerSurgeEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class SpellBladeBurstSynergy extends OnBasicAttackSynergy {

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Synergy"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Modifies Spell Blade"));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent("While Spell Blade is active, attacks"));
        list.add(new StringTextComponent("cause enemies to send out fiery novas,"));
        list.add(new StringTextComponent("dealing spell damage to other nearby "));
        list.add(new StringTextComponent("enemies: "));

        list.addAll(getCalc(Load.spells(info.player)).GetTooltipString(info, Load.spells(info.player), this));

        return list;
    }

    @Override
    public void alterSpell(PreCalcSpellConfigs c) {
        c.set(SC.MANA_COST, 2, 5);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.BASE_VALUE, 0, 0);
        c.set(SC.FIRE_ATTACK_SCALE_VALUE, 1.0F, 2.0F);
        c.set(SC.RADIUS, 2.0F, 2.5F);
        c.setMaxLevel(8);
        return c;
    }

    @Override
    public void tryActivate(DamageEffect ctx) {

        float radius = getContext(ctx.source).getConfigFor(this)
                .get(SC.RADIUS)
                .get(Load.spells(ctx.source), this);


        if (PotionEffectUtils.has(ctx.source, SpellBladeEffect.INSTANCE)) {

            if (ctx.getEffectType()
                    .equals(EffectData.EffectTypes.BASIC_ATTACK) || ctx.getEffectType()
                    .equals(EffectData.EffectTypes.ATTACK_SPELL) || ctx.getEffectType()
                    .equals(EffectData.EffectTypes.SUMMON_DMG)) {

                int num = getPreCalcConfig().getCalc(Load.spells(ctx.source), this)
                        .getCalculatedValue(ctx.sourceData, Load.spells(ctx.source), this);

                ParticleEnum.sendToClients(ctx.target,
                        new ParticlePacketData(ctx.target.getPosition(), ParticleEnum.NOVA).radius(
                                radius)
                                .type(ParticleTypes.FLAME)
                                .amount(30)
                );

                List<LivingEntity> entities = EntityFinder.start(ctx.source, LivingEntity.class, ctx.target.getPositionVector())
                        .radius(radius).searchFor(EntityFinder.SearchFor.ENEMIES)
                        .build();

                for (LivingEntity en : entities) {
                    if (en != ctx.target) {
                        DamageEffect dmg = new DamageEffect(
                                null, ctx.source, en, num, EffectData.EffectTypes.SPELL, WeaponTypes.None);
                        dmg.element = Elements.Fire;
                        dmg.Activate();
                    }
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
        return SpellBladeSpell.getInstance();
    }

    @Override
    public String locNameForLangFile() {
        return "Flame Blade";
    }
}
