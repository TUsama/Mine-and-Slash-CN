package com.robertx22.mine_and_slash.database.stats.types.resources;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.resource.LifestealEffect;
import com.robertx22.mine_and_slash.database.stats.effects.resource.SpellStealEffect;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;
import net.minecraft.util.text.TextFormatting;

public class SpellSteal extends Stat implements IStatEffects {

    public static String GUID = "spellsteal";

    public static SpellSteal getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public TextFormatting getIconFormat() {
        return TextFormatting.RED;
    }

    @Override
    public String getIcon() {
        return "\u2764";
    }

    @Override
    public String getIconPath() {
        return "resource/spellsteal";
    }

    @Override
    public StatGroup statGroup() {
        return StatGroup.Regeneration;
    }

    @Override
    public String locDescForLangFile() {
        return "Percent of spell DMG added to health";
    }

    @Override
    public IStatEffect getEffect() {
        return new SpellStealEffect();
    }

    private SpellSteal() {
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public StatScaling getScaling() {
        return StatScaling.NONE;
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
    public String locNameForLangFile() {
        return "Spellsteal";
    }

    private static class SingletonHolder {
        private static final SpellSteal INSTANCE = new SpellSteal();
    }
}
