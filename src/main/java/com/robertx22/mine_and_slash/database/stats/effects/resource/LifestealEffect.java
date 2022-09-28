package com.robertx22.mine_and_slash.database.stats.effects.resource;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData.EffectTypes;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.MathUtils;
import net.minecraft.util.math.MathHelper;

public class LifestealEffect extends BaseDamageEffect {

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
                .healthData()
                .getAverageValue() * 0.1F; // cap lifesteal at 10%

        float healed = MathHelper.clamp ((float) data.getAverageValue() * effect.number / 100, 1F, leechCap);

        effect.healthHealed += healed;

        return effect;

    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {
        return effect.getEffectType()
            .equals(EffectTypes.BASIC_ATTACK) || effect.getEffectType()
                .equals(EffectTypes.ATTACK_SPELL) || effect.getEffectType()
                .equals(EffectTypes.SUMMON_DMG) || effect.getEffectType()
                .equals(EffectData.EffectTypes.BONUS_ATTACK);
    }

}
