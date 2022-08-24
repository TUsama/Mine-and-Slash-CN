package com.robertx22.mine_and_slash.database.unique_items.gauntlets;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.Gauntlets;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.Sword;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.ArmorPenetrationFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalHitFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.PhysicalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.CriticalDamagePercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.CriticalHitPercent;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class GauntletsPhysical implements IUnique {
    public GauntletsPhysical() {

    }

    static StatReq req = new StatReq(LvlPointStat.VITALITY, StatReq.Size.MEDIUM, LvlPointStat.STRENGTH, StatReq.Size.TINY);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int getTier() {
        return 4;
    }

    @Override
    public String GUID() {
        return "gauntlets_unique";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new ArmorPenetrationFlat(), new CriticalHitFlat(), new CriticalDamageFlat());
    }

    @Override
    public GearItemSlot getGearSlot() {
        return Gauntlets.INSTANCE;
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new PhysicalDamageFlat().size(StatMod.Size.HALF_MORE));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Facebreaker";
    }

    @Override
    public String locDescForLangFile() {
        return "Move like an Enderman, strike like a Blaze.";
    }
}
