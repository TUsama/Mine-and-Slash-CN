package com.robertx22.mine_and_slash.database.stats.effects.resource;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import net.minecraft.util.math.MathHelper;

public class MagicStealEffect extends BaseDamageEffect {

    private MagicStealEffect() {
    }

    public static MagicStealEffect getInstance() {
        return SingletonHolder.INSTANCE;
    }

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

        float leechCap = effect.sourceData.getUnit()
                .magicShieldData()
                .getAverageValue() * 0.1F; // cap steal at 10%

        float healed = MathHelper.clamp ((float) data.getAverageValue() * effect.number / 100, 1F, leechCap);

        effect.magicShieldRestored += healed;

        return effect;

    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {
        return effect.getEffectType()
            .equals(EffectData.EffectTypes.BASIC_ATTACK) || effect.getEffectType()
                .equals(EffectData.EffectTypes.ATTACK_SPELL) || effect.getEffectType()
                .equals(EffectData.EffectTypes.SUMMON_DMG) || effect.getEffectType()
                .equals(EffectData.EffectTypes.BONUS_ATTACK);
    }

    private static class SingletonHolder {
        private static final MagicStealEffect INSTANCE = new MagicStealEffect();
    }
}

