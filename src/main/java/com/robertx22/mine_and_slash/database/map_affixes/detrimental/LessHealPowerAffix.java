package com.robertx22.mine_and_slash.database.map_affixes.detrimental;

import com.robertx22.mine_and_slash.database.map_affixes.DetrimentalMapAffix;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.misc.CooldownReductionFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealPowerFlat;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;

import java.util.Arrays;
import java.util.List;

public class LessHealPowerAffix extends DetrimentalMapAffix {

    @Override
    public String GUID() {
        return "less_healpower_affix";
    }

    @Override
    public List<StatModData> Stats(int percent) {
        return Arrays.asList(StatModData.Load(new HealPowerFlat().size(StatMod.Size.DOUBLE_LESS), percent));

    }

    @Override
    public float lootMulti() {
        return 1.15F;
    }

}
