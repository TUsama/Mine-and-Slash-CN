package com.robertx22.mine_and_slash.database.stats.effects.offense;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.Summon;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseStatEffect;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SummonDamageEffect;

public class AllSummonDamageEffect extends BaseStatEffect<SummonDamageEffect> {
    public static final AllSummonDamageEffect INSTANCE = new AllSummonDamageEffect();

    private AllSummonDamageEffect() {
        super(SummonDamageEffect.class);
    }

    @Override
    public int GetPriority() {
        return Priority.Second.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    @Override
    public SummonDamageEffect activate(SummonDamageEffect effect, StatData data, Stat stat) {
        effect.number *= data.getMultiplier();

        return effect;
    }

    @Override
    public boolean canActivate(SummonDamageEffect effect, StatData data, Stat stat) {
        return effect.getEffectType()
            .equals(EffectData.EffectTypes.SUMMON_DMG);
    }

}