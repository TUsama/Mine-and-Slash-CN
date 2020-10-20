package com.robertx22.mine_and_slash.database.talent_tree.data;

import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealthFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.BlockStrengthPercent;
import com.robertx22.mine_and_slash.database.stats.types.defense.BlockStrength;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalHit;
import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.SpellDamage;
import com.robertx22.mine_and_slash.database.talent_tree.PerkEffect;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

public class StartPerkEffects {

    public static PerkEffect MAGE;
    public static PerkEffect THIEF;
    public static PerkEffect GUARDIAN;
    public static PerkEffect WARRIOR;

    public static void create() {
        MAGE = new PerkEffect("mage", new ExactStatData(2, StatModTypes.Flat, SpellDamage.GUID), "starts/mage");
        THIEF = new PerkEffect("thief", new ExactStatData(1, StatModTypes.Flat, CriticalHit.GUID), "starts/thief");
        GUARDIAN = new PerkEffect("guardian", new ExactStatData(3, StatModTypes.Percent, BlockStrength.GUID), "starts/guardian");
        WARRIOR = new PerkEffect("warrior", new ExactStatData(2, StatModTypes.Percent, PhysicalDamage.GUID), "starts/warrior");

    }

}
