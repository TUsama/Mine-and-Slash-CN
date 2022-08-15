package com.robertx22.mine_and_slash.database.map_affixes.beneficial;

import com.robertx22.mine_and_slash.database.map_affixes.BeneficialMapAffix;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CompletePhysDispersionFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.PhysicalDamagePercent;
import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDispersion;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;

import java.util.Arrays;
import java.util.List;

public class BonusPhysDispersionAffixx extends BeneficialMapAffix {

    @Override
    public String GUID() {
        return "bonus_phys_disp";
    }

    @Override
    public List<StatModData> Stats(int percent) {
        return Arrays.asList(StatModData.Load(new CompletePhysDispersionFlat().size(StatMod.Size.NORMAL), percent));
    }

    @Override
    public float lootMulti() {
        return 1.7F;
    }

}
