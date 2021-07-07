package com.robertx22.mine_and_slash.database.stats.types.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.offense.AllSpellDamageEffect;
import com.robertx22.mine_and_slash.database.stats.effects.offense.AllSummonDamageEffect;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;
import net.minecraft.util.text.TextFormatting;

public class SummonDamage extends Stat implements IStatEffects {

    private SummonDamage() {

    }

    public static String GUID = "summon_damage";

    public static SummonDamage getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public StatScaling getScaling() {
        return StatScaling.SLOW_SCALING;
    }

    @Override
    public String getIconPath() {
        return "summon_dmg";
    }

    @Override
    public TextFormatting getIconFormat() {
        return TextFormatting.GOLD;
    }

    @Override
    public String getIcon() {
        return "\u262F";
    }

    @Override
    public String locDescForLangFile() {
        return "Increases the damage of summons.";
    }

    @Override
    public StatGroup statGroup() {
        return StatGroup.SpellDamage;
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public Elements getElement() {
        return null;
    }

    @Override
    public boolean IsPercent() {
        return true;
    }

    @Override
    public IStatEffect getEffect() {
        return AllSummonDamageEffect.INSTANCE;
    }

    @Override
    public String locNameForLangFile() {
        return "Summon Damage";
    }

    private static class SingletonHolder {
        private static final SummonDamage INSTANCE = new SummonDamage();
    }
}
