package com.robertx22.mine_and_slash.database.stats.effects.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalInfusion;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData.EffectTypes;

public class ElementalInfusionEffect extends BaseDamageEffect {

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
        ElementalInfusion basebonus = (ElementalInfusion) stat;

        float spellDmgMulti = (float) getSource(effect).getCreateStat(basebonus.StatThatGiveDamage())
            .getMultiplier() - 1;

        effect.number = effect.number + (effect.preIncNumber * ((data.getMultiplier() - 1) * spellDmgMulti));

        return effect;
    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {
        return (effect.getEffectType()
            .equals(EffectTypes.BASIC_ATTACK) || effect.getEffectType()
                .equals(EffectTypes.ATTACK_SPELL) || effect.getEffectType()
                .equals(EffectTypes.SUMMON_DMG) || effect.getEffectType()
                .equals(EffectTypes.BONUS_ATTACK)) && stat.getElement()
            .equals(effect.GetElement());
    }

}
