package com.robertx22.mine_and_slash.database.map_affixes.detrimental;

import com.robertx22.mine_and_slash.database.map_affixes.DetrimentalMapAffix;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.percent.HealthPercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.MagicShieldPercent;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;

import java.util.Arrays;
import java.util.List;

public class LessMagicShieldAffix extends DetrimentalMapAffix {

    @Override
    public String GUID() {
        return "less_magic_shield_affix";
    }

    @Override
    public List<StatModData> Stats(int percent) {
        return Arrays.asList(StatModData.Load(new MagicShieldPercent().size(StatMod.Size.DOUBLE_LESS), percent));
    }

    @Override
    public float lootMulti() {
        return 1.15F;
    }

}
