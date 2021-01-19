package com.robertx22.mine_and_slash.database.stats.effects.causes;

import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;

public class OnAttackCause extends BaseCause {

    @Override
    public boolean shouldActivate(EffectData Effect) {

        if (Effect instanceof DamageEffect) {

            DamageEffect dmgeffect = (DamageEffect) Effect;

            if (dmgeffect.getEffectType() == EffectData.EffectTypes.BASIC_ATTACK && dmgeffect.isDmgAllowed()) {
                return true;
            }

        }
        return false;
    }

}

