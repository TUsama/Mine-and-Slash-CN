package com.robertx22.mine_and_slash.database.stats.types.core_stats;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.HealPowerFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicShieldFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicShieldRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalResistFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalSpellDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.MagicShieldPercent;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.base.BaseCoreStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.List;

public class Wisdom extends BaseCoreStat {
    public static final String GUID = "wisdom";
    public static final Wisdom INSTANCE = new Wisdom();

    private Wisdom() {

    }

    @Override
    public TextFormatting getIconFormat() {
        return TextFormatting.LIGHT_PURPLE;
    }

    @Override
    public String getIconPath() {
        return "core/wis";
    }

    @Override
    public String locDescForLangFile() {
        return "Increases Heal Power, Magic Shield Regen, and Mana Regen.";
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public List<StatMod> statsThatBenefit() {
        return Arrays.asList(
                new HealPowerFlat().size(StatMod.Size.LOW),
                new MagicShieldRegenFlat(),
                new ManaRegenFlat().size(StatMod.Size.HALF));
    }

    @Override
    public String locNameForLangFile() {
        return "Wisdom";
    }
}
