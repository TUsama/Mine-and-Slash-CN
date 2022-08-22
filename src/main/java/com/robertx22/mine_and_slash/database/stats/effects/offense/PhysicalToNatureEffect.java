package com.robertx22.mine_and_slash.database.stats.effects.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.util.math.MathHelper;

public class PhysicalToNatureEffect extends BaseDamageEffect {

    public static final PhysicalToNatureEffect INSTANCE = new PhysicalToNatureEffect();

    @Override
    public int GetPriority() {
        return Priority.beforeThis(PhysicalToHighestEle.INSTANCE.GetPriority());
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    @Override
    public DamageEffect activate(DamageEffect effect, StatData data, Stat stat) {

        float val = effect.number;
        float multi = MathHelper.clamp(data.getMultiplier(), 0, stat.maximumValue);
        float given = (val * multi) - val;
        effect.addBonusEleDmg(Elements.Nature, given);
        effect.number -= given;

        return effect;
    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {
        return effect.element == Elements.Physical;
    }

}

