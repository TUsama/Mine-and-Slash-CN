package com.robertx22.mine_and_slash.database.stats.effects.game_changers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import net.minecraft.util.math.MathHelper;

public class LowLifeDmgEffect extends BaseDamageEffect {

    public static final LowLifeDmgEffect INSTANCE = new LowLifeDmgEffect();

    @Override
    public int GetPriority() {
        return Priority.Last.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    @Override
    public DamageEffect activate(DamageEffect effect, StatData data, Stat stat) {

        float currentHP = effect.sourceData.getResources().getHealth(effect.sourceData, effect.source);

        float maxHP = effect.sourceData.getUnit()
                .healthData()
                .getAverageValue();

        if (currentHP <= maxHP * 0.3F) { // if 30% life or less, deal extra 30% damage.
            effect.number *= 1.3F;
        }

        return effect;
    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {
        return effect.number > 0;
    }

}
