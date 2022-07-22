package com.robertx22.mine_and_slash.database.stats.effects.game_changers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import net.minecraft.util.math.MathHelper;

public class AntiOHKOEffect extends BaseDamageEffect {

    public static final AntiOHKOEffect INSTANCE = new AntiOHKOEffect();

    @Override
    public int GetPriority() {
        return Priority.Last.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Target;
    }

    @Override
    public DamageEffect activate(DamageEffect effect, StatData data, Stat stat) {

        effect.number *= 0.25;
        return effect;
    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {

        float currentHP = effect.targetData.getResources().getHealth(effect.targetData, effect.target);

        float maxHP = effect.targetData.getUnit()
                .healthData()
                .getAverageValue();

        return currentHP == maxHP;
    }

}
