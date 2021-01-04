package com.robertx22.mine_and_slash.database.spells.spell_classes.ocean.buffs;

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

public abstract class BaseOceanBuffSpell extends BaseSpell {

    public BaseOceanBuffSpell(ImmutableSpellConfigs configs) {
        super(configs.setSwingArmOnCast());
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.MANA_COST, 15, 25);
        c.set(SC.CAST_TIME_TICKS, 80, 40);
        c.set(SC.COOLDOWN_SECONDS, 60, 45);
        c.set(SC.DURATION_TICKS, 10 * 20, 20 * 20);
        c.set(SC.RADIUS, 4, 6);

        c.setMaxLevel(12);
        return c;
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Applies buff to all allies nearby: "));

        list.addAll(getImmutableConfigs().potionEffect()
            .GetTooltipStringWithNoExtraSpellInfo(info));

        return list;

    }

}