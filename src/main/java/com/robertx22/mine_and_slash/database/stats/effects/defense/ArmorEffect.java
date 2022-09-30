package com.robertx22.mine_and_slash.database.stats.effects.defense;

import com.robertx22.mine_and_slash.database.stats.IUsableStat;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseStatEffect;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.IArmorReducable;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.IPenetrable;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.util.math.MathHelper;

public class ArmorEffect extends BaseStatEffect<DamageEffect> {

    public static final ArmorEffect INSTANCE = new ArmorEffect();

    public ArmorEffect() {
        super(DamageEffect.class);
    }

    @Override
    public int GetPriority() {
        return Priority.Third.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Target;
    }

    @Override
    public DamageEffect activate(DamageEffect effect, StatData data, Stat stat) {
        int pene = 0;

        if (effect instanceof IPenetrable) {
            IPenetrable ipen = (IPenetrable) effect;
            pene = ipen.GetArmorPenetration();
            pene = pene / 100;
        }

        IUsableStat armor = (IUsableStat) stat;

        float EffectiveArmor = armor.GetUsableValue(effect.targetData.getLevel(), (int) (data.getAverageValue()));

        //EffectiveArmor = MathHelper.clamp(EffectiveArmor, 0, 1);
        EffectiveArmor -= pene;

        effect.number -= EffectiveArmor * effect.number;

        return effect;
    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {
        return effect instanceof IArmorReducable && effect.GetElement() == Elements.Physical;
    }

}
