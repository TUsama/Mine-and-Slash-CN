package com.robertx22.mine_and_slash.database.items.unique_items.pants;

import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniquePants;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.DodgeFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalTransferFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.much_less.CrippleLifeOnHitPercent;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class PantsThunder extends BaseUniquePants {

    public PantsThunder() {

    }

    @Override
    public int Tier() {
        return 6;
    }

    @Override
    public String GUID() {
        return "pantsthunder0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ElementalSpellDamageFlat(Elements.Fire), new DodgeFlat(), new ElementalResistFlat(Elements.Fire), new ElementalTransferFlat(Elements.Fire, Elements.Thunder), new CrippleLifeOnHitPercent());
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new HealthFlat());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Lightning Coil Leggings";
    }

    @Override
    public String locDescForLangFile() {
        return "Swallow flames, harness Lightning.";
    }
}
