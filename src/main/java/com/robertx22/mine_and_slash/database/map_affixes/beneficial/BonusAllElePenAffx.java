package com.robertx22.mine_and_slash.database.map_affixes.beneficial;

import com.robertx22.mine_and_slash.database.map_affixes.BeneficialMapAffix;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.PhysToWaterConvFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalPeneFlat;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class BonusAllElePenAffx extends BeneficialMapAffix {

    @Override
    public String GUID() {
        return "bonus_ele_pen";
    }

    @Override
    public List<StatModData> Stats(int percent) {
        return Arrays.asList(StatModData.Load(new ElementalPeneFlat(Elements.Elemental).size(StatMod.Size.TRIPLE), percent));
    }

    @Override
    public float lootMulti() {
        return 1.5F;
    }

}
