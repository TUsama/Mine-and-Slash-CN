package com.robertx22.mine_and_slash.database.spells.synergies.base;

import com.robertx22.mine_and_slash.uncommon.effectdatas.AttackSpellDamageEffect;

public abstract class OnAttackSpellDmgDoneSynergy extends Synergy {

    public abstract void tryActivate(AttackSpellDamageEffect effect);

}
