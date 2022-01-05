package com.robertx22.mine_and_slash.database.spells.synergies.storm;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.storm.LightningTotemSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.storm.ThunderstormSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.base.Synergy;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class LightningTotemEnhancedSynergy extends Synergy {

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Synergy"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Modifies Lightning Totem"));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent("Reduce cooldown but reduce damage: "));

        return list;
    }

    @Override
    public void alterSpell(PreCalcSpellConfigs c) {
        c.set(SC.MANA_COST, 5, 5);
        c.set(SC.BASE_VALUE, -2, -2);
        c.set(SC.COOLDOWN_SECONDS, -4, -4);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.setMaxLevel(1);
        return c;
    }

    @Override
    public Place getSynergyPlace() {
        return Place.SECOND;
    }

    @Nullable
    @Override
    public IAbility getRequiredAbility() {
        return LightningTotemSpell.getInstance();
    }

    @Override
    public String locNameForLangFile() {
        return "Improved Algorithm";
    }
}
