package com.robertx22.mine_and_slash.database.spells.synergies.storm;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.storm.ElectricalDischargeSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.base.OnBoltDmgDoneSynergy;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.shaman.ThunderEssenceEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.BoltDamageEffect;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ElectricalDischargeLightningEssenceSynergy extends OnBoltDmgDoneSynergy {

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Synergy"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Modifies Electrical Discharge"));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent("Hits have a chance to apply on self: " + ThunderEssenceEffect.INSTANCE.locNameForLangFile()));

        list.addAll(getCalc(Load.spells(info.player)).GetTooltipString(info, Load.spells(info.player), this));

        return list;
    }

    @Override
    public Place getSynergyPlace() {
        return Place.FIRST;
    }

    @Override
    public void alterSpell(PreCalcSpellConfigs c) {
        c.set(SC.MANA_COST, 1, 3);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.BASE_VALUE, 0, 0);
        c.set(SC.CHANCE, 20, 50);
        c.setMaxLevel(6);
        return c;
    }

    @Nullable
    @Override
    public IAbility getRequiredAbility() {
        return ElectricalDischargeSpell.getInstance();
    }

    @Override
    public void tryActivate(BoltDamageEffect ctx) {
        if (RandomUtils.roll(getContext(ctx.source).getConfigFor(this)
                .get(SC.CHANCE)
                .get(Load.spells(ctx.source), this))) {
            PotionEffectUtils.reApplyToSelf(ThunderEssenceEffect.INSTANCE, ctx.source);
        }
    }

    @Override
    public String locNameForLangFile() {
        return "Static Field";
    }
}
