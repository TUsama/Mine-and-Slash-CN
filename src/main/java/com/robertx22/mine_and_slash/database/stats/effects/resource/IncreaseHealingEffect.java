package com.robertx22.mine_and_slash.database.stats.effects.resource;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseHealEffect;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalInfusion;
import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.database.stats.types.resources.HealPower;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.HealEffect;

public class IncreaseHealingEffect extends BaseHealEffect {

    @Override
    public int GetPriority() {
        return Priority.First.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    } //outgoing heals

    @Override
    public HealEffect activate(HealEffect effect, StatData data, Stat stat) {

        float healpower = effect.sourceData.getUnit()
                .peekAtStat(HealPower.GUID)
                .getMultiplier();

        effect.number *= healpower; // always use healpower from the source of the heal

        return effect;
    }

    @Override
    public boolean canActivate(HealEffect effect, StatData data, Stat stat) {
        return true;
    }

}
