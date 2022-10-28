package com.robertx22.mine_and_slash.database.stats.effects.game_changers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.potion_effects.all.FortitudeChargeEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;

public class OverseerEffect extends BaseDamageEffect {

    public static final OverseerEffect INSTANCE = new OverseerEffect();

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
        if (RandomUtils.roll(50)) {
            PotionEffectUtils.apply(FortitudeChargeEffect.INSTANCE, effect.source, effect.source);
        }

        return effect;
    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {
        return effect.getEffectType()
                .equals(EffectData.EffectTypes.ATTACK_SPELL) || effect.getEffectType()
                .equals(EffectData.EffectTypes.BASIC_ATTACK) || effect.getEffectType()
                .equals(EffectData.EffectTypes.SUMMON_DMG) && effect.isDmgAllowed();
    }

}
