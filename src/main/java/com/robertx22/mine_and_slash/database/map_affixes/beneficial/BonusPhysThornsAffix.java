package com.robertx22.mine_and_slash.database.map_affixes.beneficial;

import com.robertx22.mine_and_slash.database.map_affixes.BeneficialMapAffix;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.PhysicalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.BlockReflectFlat;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class BonusPhysThornsAffix extends BeneficialMapAffix {

    @Override
    public String GUID() {
        return "bonus_phys_thorns";
    }

    @Override
    public List<StatModData> Stats(int percent) {
        return Arrays.asList(StatModData.Load(new BlockReflectFlat(Elements.Physical).size(StatMod.Size.NORMAL), percent));
    }

    @Override
    public float lootMulti() {
        return 1.05F;
    }

}
