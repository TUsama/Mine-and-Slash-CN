package com.robertx22.mine_and_slash.database.stats.mods.generated;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.generated.LootTypeBonus;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LootTypeBonusFlat extends StatMod implements IGenerated<StatMod> {

    public LootType type;

    public LootTypeBonusFlat(LootType type) {
        this.type = type;

    }

    @Override
    public Stat GetBaseStat() {
        return new LootTypeBonus(type);
    }

    @Override
    public float Min() {
        return 14;
    }

    @Override
    public float Max() {
        return 18;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Flat;
    }

    @Override
    public List<StatMod> generateAllPossibleStatVariations() {
        List<StatMod> list = new ArrayList<>();
        Arrays.asList(LootType.values()).forEach(x -> list.add(new LootTypeBonusFlat(x)));
        return list;

    }
}
