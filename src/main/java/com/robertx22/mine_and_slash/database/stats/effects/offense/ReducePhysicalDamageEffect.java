package com.robertx22.mine_and_slash.database.stats.effects.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.util.math.MathHelper;

public class ReducePhysicalDamageEffect extends BaseDamageEffect {

    private ReducePhysicalDamageEffect() {
    }

    public static ReducePhysicalDamageEffect getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public int GetPriority() {
        return Priority.First.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    @Override
    public DamageEffect activate(DamageEffect effect, StatData data, Stat stat) {

        float damageDecrease = MathHelper.clamp(data.getAverageValue(), stat.minimumValue, stat.maximumValue);

        effect.number *=  (1 - damageDecrease / 100);

        return effect;
    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {
        return effect.element.equals(Elements.Physical);
    }

    private static class SingletonHolder {
        private static final ReducePhysicalDamageEffect INSTANCE = new ReducePhysicalDamageEffect();
    }
}
