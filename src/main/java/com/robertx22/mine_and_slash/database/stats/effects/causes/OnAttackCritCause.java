package com.robertx22.mine_and_slash.database.stats.effects.causes;

import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.ICrittable;

public class OnAttackCritCause extends BaseCause {

    @Override
    public boolean shouldActivate(EffectData Effect) {

        if (Effect instanceof ICrittable) {

            ICrittable dmgeffect = (ICrittable) Effect;

            if (dmgeffect.isCriticalHit()) {
                return true;
            }

        }
        return false;
    }

}

