package com.robertx22.mine_and_slash.database.spells.synergies.unholy;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean.HeartOfIceSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.unholy.MalevolentOfferingSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.base.OnSpellCastSynergy;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MalevolentOfferingShrewdSynergy extends OnSpellCastSynergy {

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Synergy"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Modifies Malevolent Offering"));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent("Restores magic shield to nearby allies"));
        list.add(new StringTextComponent("(does not restore caster's): "));

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
        c.set(SC.HEALTH_ATTACK_SCALE_VALUE, 0.05F, 0.15F);
        c.set(SC.BASE_VALUE, 0, 0);
        c.setMaxLevel(8);
        return c;
    }

    @Nullable
    @Override
    public IAbility getRequiredAbility() {
        return MalevolentOfferingSpell.getInstance();
    }

    @Override
    public void tryActivate(SpellCastContext ctx) {
        ResourcesData.Context heal = new ResourcesData.Context(ctx.data, ctx.caster,
            ResourcesData.Type.MAGIC_SHIELD,
            ctx.getConfigFor(this)
                .getCalc(ctx.spellsCap, this)
                .getCalculatedValue(ctx.data, Load.spells(ctx.caster), this),
            ResourcesData.Use.RESTORE, ctx.spell
        );
        ctx.data
            .modifyResource(heal);
    }

    @Override
    public String locNameForLangFile() {
        return "Shrewd Offering";
    }
}