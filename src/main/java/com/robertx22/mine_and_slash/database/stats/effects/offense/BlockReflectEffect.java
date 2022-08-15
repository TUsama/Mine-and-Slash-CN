package com.robertx22.mine_and_slash.database.stats.effects.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.database.stats.effects.defense.BlockEffect;
import com.robertx22.mine_and_slash.database.stats.types.resources.Energy;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData.EffectTypes;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.util.SoundEvents;

public class BlockReflectEffect extends BaseDamageEffect {

    public Elements element = Elements.Physical;

    public BlockReflectEffect(Elements element) {
        this.element = element;
    }

    @Override
    public int GetPriority() {
        return Priority.afterThis(new BlockEffect().GetPriority());
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Target;
    }

    @Override
    public DamageEffect activate(DamageEffect effect, StatData data, Stat stat) {

        float dmg = data.getAverageValue(); 

        if (effect.isActivelyBlocking) { // check if the blocker is actively blocking, if so, apply bonus damage based on success/failure
            if (effect.isFullyBlocked) {
                dmg *= 1.5F;
            } else if (effect.isPartiallyBlocked) {
                dmg *= 1.1F;
            }
        }

        DamageEffect dmgeffect = new DamageEffect(null, effect.target, effect.source, (int) dmg, effect.targetData,
            effect.sourceData, EffectTypes.REFLECT, WeaponTypes.None
        );

        dmgeffect.element = stat.getElement();

        SoundUtils.playSound(effect.target, SoundEvents.BLOCK_GLASS_BREAK, 1.0F, 2);

        dmgeffect.Activate();

        return effect;

    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {
        return ((effect.getEffectType()
            .equals(EffectTypes.BASIC_ATTACK) || effect.getEffectType()
                .equals(EffectTypes.ATTACK_SPELL) || effect.getEffectType()
                .equals(EffectTypes.SUMMON_DMG)) && !effect.isDodged);
    }

}
