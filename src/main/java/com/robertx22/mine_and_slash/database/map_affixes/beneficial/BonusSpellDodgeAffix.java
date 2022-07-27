package com.robertx22.mine_and_slash.database.map_affixes.beneficial;

import com.robertx22.mine_and_slash.database.map_affixes.BeneficialMapAffix;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.DodgeRatingFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.SpellDodgeFlat;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;

import java.util.Arrays;
import java.util.List;

public class BonusSpellDodgeAffix extends BeneficialMapAffix {

    @Override
    public String GUID() {
        return "bonus_spell_dodge";
    }

    @Override
    public List<StatModData> Stats(int percent) {
        return Arrays.asList(StatModData.Load(new SpellDodgeFlat().size(StatMod.Size.TRIPLE), percent));
    }

    @Override
    public float lootMulti() {
        return 1.6F;
    }

}
