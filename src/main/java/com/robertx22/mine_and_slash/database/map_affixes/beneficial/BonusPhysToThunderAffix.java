package com.robertx22.mine_and_slash.database.map_affixes.beneficial;

import com.robertx22.mine_and_slash.database.map_affixes.BeneficialMapAffix;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.PhysToFireConvFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.PhysToThunderConvFlat;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;

import java.util.Arrays;
import java.util.List;

public class BonusPhysToThunderAffix extends BeneficialMapAffix {

    @Override
    public String GUID() {
        return "bonus_phys_to_thunder";
    }

    @Override
    public List<StatModData> Stats(int percent) {
        return Arrays.asList(StatModData.Load(new PhysToThunderConvFlat().size(StatMod.Size.NORMAL), percent));
    }

    @Override
    public float lootMulti() {
        return 1.3F;
    }

}
