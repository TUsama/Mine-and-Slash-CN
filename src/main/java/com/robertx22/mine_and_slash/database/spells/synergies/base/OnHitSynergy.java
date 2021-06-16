package com.robertx22.mine_and_slash.database.spells.synergies.base;

import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;

public abstract class OnHitSynergy extends Synergy {

    public abstract void tryActivate(DamageEffect effect);

}
