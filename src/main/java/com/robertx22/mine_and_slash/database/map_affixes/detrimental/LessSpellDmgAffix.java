package com.robertx22.mine_and_slash.database.map_affixes.detrimental;

import com.robertx22.mine_and_slash.database.map_affixes.DetrimentalMapAffix;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.percent.SpellStealPercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.SpellDamagePercent;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;

import java.util.Arrays;
import java.util.List;

public class LessSpellDmgAffix extends DetrimentalMapAffix {

    @Override
    public String GUID() {
        return "less_spell_damage_affix";
    }

    @Override
    public List<StatModData> Stats(int percent) {
        return Arrays.asList(StatModData.Load(new SpellDamagePercent().size(StatMod.Size.TRIPLE_LESS), percent));

    }

    @Override
    public float lootMulti() {
        return 1.3F;
    }

}
