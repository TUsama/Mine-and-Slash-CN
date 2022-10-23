package com.robertx22.mine_and_slash.database.map_affixes.detrimental;

import com.robertx22.mine_and_slash.database.map_affixes.DetrimentalMapAffix;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.generated.LootTypeBonusFlat;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;

import java.util.Arrays;
import java.util.List;

public class OtherTeamBonusAffix extends DetrimentalMapAffix {

    @Override
    public String GUID() {
        return "other_team_bonus";
    }

    @Override
    public List<StatModData> Stats(int percent) {
        return Arrays.asList(StatModData.Load(new LootTypeBonusFlat(LootType.UniqueItem).size(StatMod.Size.DOUBLE), percent));
    }

    @Override
    public float lootMulti() {
        return 1F;
    }

    @Override
    public int Weight() { // shouldnt get this via rolls
        return 0;
    }

}
