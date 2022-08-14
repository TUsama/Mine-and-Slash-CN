package com.robertx22.mine_and_slash.database.map_affixes.detrimental;

import com.robertx22.mine_and_slash.database.map_affixes.DetrimentalMapAffix;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.percent.HealthRegenPercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.MagicShieldRegenPercent;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;

import java.util.Arrays;
import java.util.List;

public class LessMagicShieldRegenAffix extends DetrimentalMapAffix {

    @Override
    public String GUID() {
        return "less_magic_shield_regen_affix";
    }

    @Override
    public List<StatModData> Stats(int percent) {
        return Arrays.asList(StatModData.Load(new MagicShieldRegenPercent().size(StatMod.Size.TRIPLE_LESS), percent));
    }

    @Override
    public float lootMulti() {
        return 1.1F;
    }

}
