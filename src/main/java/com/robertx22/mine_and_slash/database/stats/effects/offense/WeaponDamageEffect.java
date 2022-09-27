package com.robertx22.mine_and_slash.database.stats.effects.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.database.stats.types.generated.WeaponDamage;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Map;

public class WeaponDamageEffect extends BaseDamageEffect {

    public static WeaponDamageEffect INSTANCE = new WeaponDamageEffect();

    @Override
    public int GetPriority() {
        return Priority.afterThis(PhysicalToHighestEle.INSTANCE.GetPriority());
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    @Override
    public DamageEffect activate(DamageEffect effect, StatData data, Stat stat) {

        //effect.wepMultiBonusEleDmg(data.getMultiplier());
        float multi = data.getMultiplier();
        effect.number = effect.number + (effect.preIncNumber * (multi - 1));

        return effect;
    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {

        if ((effect.getEffectType() == EffectData.EffectTypes.BASIC_ATTACK || effect.getEffectType()
                .equals(EffectData.EffectTypes.ATTACK_SPELL) || effect.getEffectType()
                .equals(EffectData.EffectTypes.SUMMON_DMG) || effect.getEffectType()
                .equals(EffectData.EffectTypes.BONUS_ATTACK))) {
            if (stat instanceof WeaponDamage) {
                try {
                    WeaponDamage weapon = (WeaponDamage) stat;
                    GearItemData gear = Gear.Load(effect.source.getHeldItemMainhand());
                    return gear != null && (gear.GetBaseGearType().weaponType().equals(weapon.weaponType()));
                } catch (Exception e) {
                    return false;
                }
            }
        }
        return false;
    }

}