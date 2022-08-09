package com.robertx22.mine_and_slash.database.stats.effects.game_changers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.database.stats.effects.defense.ArmorEffect;
import com.robertx22.mine_and_slash.database.stats.effects.defense.MagicShieldEffect;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.IElementalEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.IElementalResistable;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.util.math.MathHelper;

public class ShulkerShellEffect extends BaseDamageEffect {

    public static final ShulkerShellEffect INSTANCE = new ShulkerShellEffect();

    @Override
    public int GetPriority() {
        return Priority.beforeThis(ArmorEffect.INSTANCE.GetPriority());
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Target;
    }

    @Override
    public DamageEffect activate(DamageEffect effect, StatData data, Stat stat) {

        DamageEffect dmg = new DamageEffect(
                null, effect.source, effect.target, (int) (effect.number * 0.6F), effect.getEffectType(), effect.weaponType);
        dmg.element = Elements.Physical;
        dmg.Activate();

        effect.number *= 0.4f;

        return effect;
    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {
        if (effect instanceof IElementalResistable) {

            IElementalEffect ele = (IElementalEffect) effect;

            if (ele.GetElement() != Elements.Physical) {
                if (ele.GetElement()
                        .equals(stat.getElement())) {
                    return true;
                }
            }
        }
        return false;
    }

}

