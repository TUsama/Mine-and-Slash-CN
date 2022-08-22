package com.robertx22.mine_and_slash.database.stats.effects.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.database.stats.types.generated.EleWepDmg;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalAttackDamage;
import com.robertx22.mine_and_slash.database.stats.types.resources.HealPower;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.MathUtils;
import net.minecraft.util.math.MathHelper;

import java.util.List;
import java.util.Map;

public class PhysicalToHighestEle extends BaseDamageEffect {

    public static final PhysicalToHighestEle INSTANCE = new PhysicalToHighestEle();

    @Override
    public int GetPriority() {
        return Priority.Third.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    @Override
    public DamageEffect activate(DamageEffect effect, StatData data, Stat stat) {

        Elements ele = effect.getHighestBonusElementalDamageElement();

        float val = effect.number;

        if (ele != null) {
            float multi = MathHelper.clamp(data.getMultiplier(), 0, stat.maximumValue);
            float given = (val * multi) - val;
            effect.addBonusEleDmg(ele, given);
            effect.number -= given;

        } else {
            float water = effect.sourceData.getUnit()
                    .peekAtStat("attack_water_damage")
                    .getAverageValue();
            float fire = effect.sourceData.getUnit()
                    .peekAtStat("attack_fire_damage")
                    .getAverageValue();
            float thunder = effect.sourceData.getUnit()
                    .peekAtStat("attack_thunder_damage")
                    .getAverageValue();
            float nature = effect.sourceData.getUnit()
                    .peekAtStat("attack_nature_damage")
                    .getAverageValue();

            Elements eleFailsafe = Elements.Physical;

            if (water > fire && water > thunder && water > nature) {
                eleFailsafe = Elements.Water;
            } else if (fire > water && fire > thunder && fire > nature) {
                eleFailsafe = Elements.Fire;
            } else if (thunder > water && thunder > fire && thunder > nature) {
                eleFailsafe = Elements.Thunder;
            } else if (nature > water && nature > fire && nature > thunder) {
                eleFailsafe = Elements.Nature;
            }
            if (eleFailsafe != Elements.Physical) {
                float val2 = effect.number;
                float multi2 = MathHelper.clamp(data.getMultiplier(), 0, stat.maximumValue);
                float given2 = (val2 * multi2) - val2;

                effect.addBonusEleDmg(eleFailsafe, given2);
                effect.number -= given2;
            }
        }

        return effect;
    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {
        return effect.element == Elements.Physical;
    }

}

