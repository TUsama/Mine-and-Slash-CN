package com.robertx22.mine_and_slash.database.unique_items.gauntlets;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.Gauntlets;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.misc.FasterCastRateFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalHitFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.PhysicalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.LifeOnHitFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.EnergyRegenPercent;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class GauntletsLife implements IUnique {
    public GauntletsLife() {

    }

    static StatReq req = new StatReq(LvlPointStat.VITALITY, StatReq.Size.MEDIUM, LvlPointStat.STRENGTH, StatReq.Size.TINY);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int getTier() {
        return 2;
    }

    @Override
    public String GUID() {
        return "gauntlets_life";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new FasterCastRateFlat().size(StatMod.Size.HALF), new LifeOnHitFlat().size(StatMod.Size.HALF_MORE), new EnergyRegenPercent().size(StatMod.Size.QUARTER_MORE));
    }

    @Override
    public GearItemSlot getGearSlot() {
        return Gauntlets.INSTANCE;
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new PhysicalDamageFlat());
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Radiance";
    }

    @Override
    public String locDescForLangFile() {
        return "One-two-punch!";
    }
}
