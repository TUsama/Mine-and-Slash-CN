package com.robertx22.mine_and_slash.potion_effects.bases.data;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class PotionStat {

    StatModTypes type = StatModTypes.Flat;
    float value;
    Stat stat;

    public PotionStat(float value, Stat stat) {
        this.value = value;
        this.stat = stat;
    }

    public ExactStatData getExactStat(EntityCap.UnitData data, PlayerSpellCap.ISpellsCap cap, ExtraPotionData pdata, BasePotionEffect effect) {

        //float multi = effect.getAbilityThatDeterminesLevel().getLevelPowerMulti(cap);

        //float multi = effect.getAbilityThatDeterminesLevel().getLevelPowerMulti(pdata.getSpellsCap());
        float multi = pdata.getLevelPowerMulti();
        //System.out.println("UnitData: " + data + "\nSpellsCap: " + cap);

        //System.out.println("PotionStat getExactStat multi_old: " + multi_old);
        //System.out.println("PotionStat getExactStat multi: " + multi);

        float finalVal = pdata.getStacks() * value * multi;

        //System.out.println("PotionStat getExactStat finalVal: " + finalVal);

        ExactStatData statData = new ExactStatData(finalVal, type, stat);
        //statData.scaleToLvl(effect.getAbilityThatDeterminesLevel().getEffectiveAbilityLevel(pdata.getSpellsCap(), pdata.getUnitData()));
        statData.scaleToLvl(pdata.getEffectiveAbilityLevel());
        //System.out.println("PotionStat getExactStat getEffectiveAbilityLevel: " + effect.getAbilityThatDeterminesLevel().getEffectiveAbilityLevel(cap, data));
        return statData;

    }

    public ExactStatData getOwnExactStat(EntityCap.UnitData data, PlayerSpellCap.ISpellsCap cap, ExtraPotionData pdata, BasePotionEffect effect) {

        float multi = effect.getAbilityThatDeterminesLevel()
                .getLevelPowerMulti(cap);

        //float multi = effect.getAbilityThatDeterminesLevel().getLevelPowerMulti(pdata.getSpellsCap());
        //System.out.println("UnitData: " + data + "\nSpellsCap: " + cap);

        //System.out.println("PotionStat getExactStat multi_old: " + multi_old);
        //System.out.println("PotionStat getExactStat multi: " + multi);

        float finalVal = pdata.getStacks() * value * multi;

        //System.out.println("PotionStat getExactStat finalVal: " + finalVal);

        ExactStatData statData = new ExactStatData(finalVal, type, stat);
        statData.scaleToLvl(effect.getAbilityThatDeterminesLevel()
                .getEffectiveAbilityLevel(cap, data));
        //System.out.println("PotionStat getExactStat getEffectiveAbilityLevel: " + effect.getAbilityThatDeterminesLevel().getEffectiveAbilityLevel(cap, data));
        return statData;

    }

}
