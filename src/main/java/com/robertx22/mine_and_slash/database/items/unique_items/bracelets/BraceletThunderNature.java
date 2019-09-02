package com.robertx22.mine_and_slash.database.items.unique_items.bracelets;

import com.robertx22.mine_and_slash.database.items.unique_items.bases.BaseUniqueBracelet;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.resources.ManaFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalTransferFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.less.LessCriticalHitPercent;
import com.robertx22.mine_and_slash.database.stats.stat_mods.percent.less.LessDodgePercent;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class BraceletThunderNature extends BaseUniqueBracelet {

    public BraceletThunderNature() {

    }

    @Override
    public int Tier() {
        return 16;
    }

    @Override
    public String GUID() {
        return "braceletthundernature0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ElementalTransferFlat(Elements.Thunder, Elements.Nature), new ManaFlat(), new LessCriticalHitPercent(), new LessDodgePercent());
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalResistFlat(Elements.Nature), new ElementalResistFlat(Elements.Thunder));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Rooted Thunder Bracers";
    }

    @Override
    public String locDescForLangFile() {
        return "Heavenly Lightning? I call it mana.";
    }
}