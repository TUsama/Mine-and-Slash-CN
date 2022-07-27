package com.robertx22.mine_and_slash.database.spells.spell_classes.unholy.buffs;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseUnholyBuffSpell extends BaseSpell {

    public BaseUnholyBuffSpell(ImmutableSpellConfigs configs) {
        super(configs.setSwingArmOnCast());
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.HEALTH_COST, 0.15F, 0.15F);
        c.set(SC.MANA_COST, 6, 9);
        c.set(SC.ENERGY_COST, 0, 0);
        c.set(SC.MAGIC_SHIELD_COST, 0.15F, 0.15F);
        c.set(SC.CAST_TIME_TICKS, 160, 220);
        c.set(SC.COOLDOWN_SECONDS, 10, 10);
        c.set(SC.DURATION_TICKS, 180 * 20, 240 * 20);
        c.set(SC.RADIUS, 4, 8);

        c.setMaxLevel(8);
        return c;
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "Spell"));
        list.add(new StringTextComponent(TextFormatting.GRAY + "" + TextFormatting.ITALIC + "Area, Buff"));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent("Applies buff to nearby allies: "));

        list.addAll(getImmutableConfigs().potionEffect()
            .GetTooltipStringWithNoExtraSpellInfo(info));

        return list;

    }

}