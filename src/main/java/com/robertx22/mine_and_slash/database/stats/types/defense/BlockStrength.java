package com.robertx22.mine_and_slash.database.stats.types.defense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.defense.BlockEffect;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class BlockStrength extends Stat implements IStatEffects {

    public static final BlockStrength INSTANCE = new BlockStrength();

    @Override
    public StatGroup statGroup() {
        return StatGroup.Defenses;
    }

    @Override
    public String getIcon() {
        return "\u56DE";
    }

    @Override
    public String getIconPath() {
        return "block";
    }

    @Override
    public String locDescForLangFile() {
        return "Percent chance to completely block a hit. Upon failure, still reduce damage by 50 percent.";
    }

    public static String GUID = "block_strength";

    public BlockStrength() {
        this.maximumValue = 75;
        this.minimumValue = 0;
    }

    @Override
    public String GUID() {
        return GUID;
    }

    public StatScaling getScaling() {
        return StatScaling.SLOW_SCALING;
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
        return "Block Chance";
    }

    @Override
    public IStatEffect getEffect() {
        return new BlockEffect();
    }
}
