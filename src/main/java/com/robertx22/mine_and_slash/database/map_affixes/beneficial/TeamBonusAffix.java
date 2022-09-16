package com.robertx22.mine_and_slash.database.map_affixes.beneficial;

import com.robertx22.mine_and_slash.database.map_affixes.BeneficialMapAffix;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.DamageShieldFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.ArmorPenetrationFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.IncreaseDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.PhysicalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalPeneFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamagePercent;
import com.robertx22.mine_and_slash.database.stats.mods.mob_spells.SpeedyFlat;
import com.robertx22.mine_and_slash.database.stats.mods.multi.defense.HealthMulti;
import com.robertx22.mine_and_slash.database.stats.mods.percent.ArmorPercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.HealthPercent;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

import java.util.Arrays;
import java.util.List;

public class TeamBonusAffix extends BeneficialMapAffix {

    @Override
    public String GUID() {
        return "team_bonus";
    }

    @Override
    public List<StatModData> Stats(int percent) {
        return Arrays.asList(StatModData.Load(new HealthMulti().size(StatMod.Size.FIFTY), 100),
                StatModData.Load(new DamageShieldFlat().size(StatMod.Size.TEN), 100),
                StatModData.Load(new IncreaseDamageFlat().size(StatMod.Size.TENTEN), percent),
                StatModData.Load(new SpeedyFlat(), 100));
    }

    @Override
    public float lootMulti() {
        return 7.5F;
    }

    @Override
    public int Weight() { // shouldnt get this via rolls
        return 0;
    }

}
