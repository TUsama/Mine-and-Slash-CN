package com.robertx22.mine_and_slash.database.spells.spell_classes.hunting.buffs;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseHuntingBuffSpell extends BaseSpell {

    public BaseHuntingBuffSpell(ImmutableSpellConfigs configs) {
        super(configs.setSwingArmOnCast());
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.MANA_COST, 18, 26);
        c.set(SC.CAST_TIME_TICKS, 100, 100);
        c.set(SC.COOLDOWN_SECONDS, 60, 45);
        c.set(SC.DURATION_TICKS, 180 * 20, 300 * 20);
        c.set(SC.RADIUS, 3, 6);

        c.setMaxLevel(12);
        return c;
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Applies wind to all nearby allies: "));

        list.addAll(getImmutableConfigs().potionEffect()
            .GetTooltipStringWithNoExtraSpellInfo(info));

        list.add(new StringTextComponent("Only one Hunting wind is allowed at a time!"));

        return list;

    }

}