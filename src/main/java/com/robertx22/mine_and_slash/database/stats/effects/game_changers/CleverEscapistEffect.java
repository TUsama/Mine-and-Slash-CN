package com.robertx22.mine_and_slash.database.stats.effects.game_changers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.potion_effects.all.EscapistEffect;
import com.robertx22.mine_and_slash.potion_effects.all.PowerChargeEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.physical.EarthenShellEffect;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.ICrittable;

public class CleverEscapistEffect extends BaseDamageEffect {

    public static final CleverEscapistEffect INSTANCE = new CleverEscapistEffect();

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
            PotionEffectUtils.apply(EscapistEffect.INSTANCE, effect.source, effect.source);
        return effect;
    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {
        return effect.isDodged;
    }

}
