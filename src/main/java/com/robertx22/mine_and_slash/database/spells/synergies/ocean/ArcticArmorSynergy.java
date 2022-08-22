package com.robertx22.mine_and_slash.database.spells.synergies.ocean;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.nature.ThornArmorSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean.ArcticArmorSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.base.OnBasicAttackSynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.base.OnHitSynergy;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.druid.ThornArmorEffect;
import com.robertx22.mine_and_slash.potion_effects.druid.ThornsEffect;
import com.robertx22.mine_and_slash.potion_effects.ocean_mystic.ArcticArmorEffect;
import com.robertx22.mine_and_slash.potion_effects.ocean_mystic.ColdEssenceEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ArcticArmorSynergy extends OnHitSynergy {

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Synergy"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Modifies Arctic Armor"));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent("While Arctic Armor is active, hits"));
        list.add(new StringTextComponent("have a chance to apply on self: " + ColdEssenceEffect.INSTANCE.locNameForLangFile()));

        list.addAll(getCalc(Load.spells(info.player)).GetTooltipString(info, Load.spells(info.player), this));

        return list;
    }

    @Override
    public void tryActivate(DamageEffect ctx) {

        float chance = getContext(ctx.source).getConfigFor(this)
            .get(SC.CHANCE)
            .get(Load.spells(ctx.source), this);

        if (RandomUtils.roll(chance)) {
            if (PotionEffectUtils.has(ctx.source, ArcticArmorEffect.INSTANCE)) {
                PotionEffectUtils.reApplyToSelf(ColdEssenceEffect.INSTANCE, ctx.source);
            }
        }
    }

    @Override
    public void alterSpell(PreCalcSpellConfigs c) {
        c.set(SC.MANA_COST, 1, 2);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.BASE_VALUE, 0, 0);
        c.set(SC.CHANCE, 15, 35);
        c.setMaxLevel(4);
        return c;
    }

    @Override
    public Place getSynergyPlace() {
        return Place.FIRST;
    }

    @Nullable
    @Override
    public IAbility getRequiredAbility() {
        return ArcticArmorSpell.getInstance();
    }

    @Override
    public String locNameForLangFile() {
        return "Arctic Armor";
    }

}
