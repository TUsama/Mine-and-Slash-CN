package com.robertx22.mine_and_slash.database.stats.types.core_stats;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamagePercent;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.base.BaseCoreStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.List;

public class Intelligence extends BaseCoreStat {

    private Intelligence() {

    }

    public static final Intelligence INSTANCE = new Intelligence();
    public static String GUID = "intelligence";

    @Override
    public TextFormatting getIconFormat() {
        return TextFormatting.BLUE;
    }

    @Override
    public String getIconPath() {
        return "core/int";
    }

    @Override
    public String locDescForLangFile() {
        return "Increase Elemental Spell Damage, Mana, and Mana Regen";
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> statsThatBenefit() {
        return Arrays.asList(
                new ElementalSpellDamageFlat(Elements.Elemental).size(StatMod.Size.HALF),
                new ManaFlat().size(StatMod.Size.LOW),
                new ManaRegenFlat().size(StatMod.Size.HALF));
    }

    @Override
    public String locNameForLangFile() {
        return "Intelligence";
    }
}




