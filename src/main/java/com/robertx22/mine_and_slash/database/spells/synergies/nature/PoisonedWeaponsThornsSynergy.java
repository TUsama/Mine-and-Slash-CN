package com.robertx22.mine_and_slash.database.spells.synergies.nature;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.nature.PoisonedWeaponsSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.base.OnBasicAttackSynergy;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.druid.ThornsEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class PoisonedWeaponsThornsSynergy extends OnBasicAttackSynergy {

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Synergy"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Modifies Poisoned Weapons"));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent("While Poisoned Weapons is active,"));
        list.add(new StringTextComponent("attacks deals extra damage to"));
        list.add(new StringTextComponent("targets affected by " + ThornsEffect.INSTANCE.locNameForLangFile() + ": "));

        list.addAll(getCalc(Load.spells(info.player)).GetTooltipString(info, Load.spells(info.player), this));

        return list;
    }

    @Override
    public void alterSpell(PreCalcSpellConfigs c) {
        c.set(SC.MANA_COST, 1, 4);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.BASE_VALUE, 4, 11);
        c.setMaxLevel(8);
        return c;
    }

    @Override
    public void tryActivate(DamageEffect ctx) {

        if (ctx.getEffectType()
            .equals(EffectData.EffectTypes.BASIC_ATTACK) || ctx.getEffectType()
                .equals(EffectData.EffectTypes.ATTACK_SPELL) || ctx.getEffectType()
                .equals(EffectData.EffectTypes.SUMMON_DMG)) {

            if (PotionEffectUtils.has(ctx.target, ThornsEffect.INSTANCE)) {

                ParticleEnum.sendToClients(ctx.target,
                    new ParticlePacketData(ctx.target.getPosition(), ParticleEnum.NOVA).radius(
                        2)
                        .type(ParticleTypes.CRIT)
                        .amount(30)
                );

                int num = getPreCalcConfig().getCalc(Load.spells(ctx.source), this)
                    .getCalculatedValue(ctx.sourceData, Load.spells(ctx.source), this);

                //SynergyDamageEffect dmg = new SynergyDamageEffect(this,
                //    ctx.source, ctx.target, num, ctx.sourceData, ctx.targetData, getSpell());
                DamageEffect dmg = new DamageEffect(
                        null, ctx.source, ctx.target, num, EffectData.EffectTypes.SPELL, WeaponTypes.None);
                dmg.element = getSpell()
                    .getElement();
                dmg.Activate();

            }
        }
    }

    @Override
    public Place getSynergyPlace() {
        return Place.FIRST;
    }

    @Override
    public IAbility getRequiredAbility() {
        return PoisonedWeaponsSpell.getInstance();
    }

    @Override
    public String locNameForLangFile() {
        return "Poison Thorn Seeker";
    }
}
