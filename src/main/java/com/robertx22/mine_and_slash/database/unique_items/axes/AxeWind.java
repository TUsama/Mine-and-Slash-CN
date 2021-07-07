package com.robertx22.mine_and_slash.database.unique_items.axes;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.Axe;
import com.robertx22.mine_and_slash.database.spells.spell_classes.divine.WhirlwindSpell;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.misc.CooldownReductionFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.misc.PlusAbiliyLevelFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.ArmorPenetrationFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.PhysicalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaOnHitFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalAttackDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.ArmorPercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.DodgeRatingPercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.HealthPercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.LifestealPercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.CriticalDamagePercent;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class AxeWind implements IUnique {
    public AxeWind() {

    }

    static StatReq req = new StatReq(
        LvlPointStat.STRENGTH, StatReq.Size.MEDIUM, LvlPointStat.STAMINA, StatReq.Size.SMALL);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int getTier() {
        return 3;
    }

    @Override
    public String GUID() {
        return "axewind0";
    }

    float multi = 0.7F;

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new PlusAbiliyLevelFlat(WhirlwindSpell.getInstance()), new ArmorPenetrationFlat().size(StatMod.Size.LOW),
            new CooldownReductionFlat().size(StatMod.Size.DOUBLE), new HealthPercent().size(StatMod.Size.ONE_LESS), new DodgeRatingPercent().size(StatMod.Size.ONE_LESS),
        new CriticalDamagePercent().size(StatMod.Size.DOUBLE_LESS)
        );
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new PhysicalDamageFlat().size(StatMod.Size.HALF_MORE)
        );
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Cyclonic Axe";
    }

    @Override
    public String locDescForLangFile() {
        return "Just keep spinning.";
    }

    @Override
    public GearItemSlot getGearSlot() {
        return Axe.INSTANCE;
    }
}




