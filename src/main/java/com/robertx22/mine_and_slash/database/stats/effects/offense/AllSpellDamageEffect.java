package com.robertx22.mine_and_slash.database.stats.effects.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;

public class AllSpellDamageEffect extends BaseDamageEffect {

    //public static final AllSpellDamageEffect INSTANCE = new AllSpellDamageEffect();

    //private AllSpellDamageEffect() {
    //    super(SpellDamageEffect.class);
    //}

    @Override
    public int GetPriority() {
        return Priority.Second.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    @Override
    public DamageEffect activate(DamageEffect effect, StatData data, Stat stat) {
        float multi = data.getMultiplier();
        effect.number = effect.number + (effect.preIncNumber * (multi - 1));

        return effect;
    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {
        return effect.getEffectType()
            .equals(EffectData.EffectTypes.SPELL) || effect.getEffectType()
                .equals(EffectData.EffectTypes.ATTACK_SPELL);
    }

}