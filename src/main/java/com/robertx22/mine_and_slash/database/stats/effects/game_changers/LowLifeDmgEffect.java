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

        float num = MathHelper.clamp(1 + (1 - currentHP / maxHP) / 3, 1, 1.25F);

        effect.number *= num;
        return effect;
    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {
        return effect.number > 0;
    }

}
