package com.robertx22.mine_and_slash.database.stats.types.core_stats;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.ArmorFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.DodgeRatingFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.ArmorPenetrationFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalHitFlat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.base.BaseCoreStat;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.List;

public class Dexterity extends BaseCoreStat {

    public static final String GUID = "dexterity";

    public static final Dexterity INSTANCE = new Dexterity();

    private Dexterity() {

    }

    @Override
    public TextFormatting getIconFormat() {
        return TextFormatting.GREEN;
    }

    @Override
    public String getIconPath() {
        return "core/dex";
    }

    @Override
    public String locDescForLangFile() {
        return "Increases Dodge Rating, Critical Hit, and Armor Pen.";
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> statsThatBenefit() {
        return Arrays.asList(
                new DodgeRatingFlat(),
                new CriticalHitFlat().size(StatMod.Size.QUARTER),
                new ArmorPenetrationFlat().size(StatMod.Size.QUARTER));
    }

    @Override
    public String locNameForLangFile() {
        return "Dexterity";
    }
}
