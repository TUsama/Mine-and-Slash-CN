package com.robertx22.mine_and_slash.database.spells.synergies.base;

import com.robertx22.mine_and_slash.uncommon.effectdatas.BoltDamageEffect;

public abstract class OnBoltDmgDoneSynergy extends Synergy {

    public abstract void tryActivate(BoltDamageEffect effect);

}
