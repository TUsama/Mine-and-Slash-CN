package com.robertx22.mine_and_slash.database.stats.types.spell_calc;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.spell_calc.IncreasedDurationEffect;
import com.robertx22.mine_and_slash.database.stats.effects.spell_calc.IncreasedProjSpeedEffect;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;

public class IncreasedProjSpeedStat extends Stat implements IStatEffects {

    private IncreasedProjSpeedStat() {
        this.maximumValue = 75;
    }

    public static IncreasedProjSpeedStat getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public boolean IsPercent() {
        return true;
    }

    @Override
    public String getIconPath() {
        return "increased_proj_speed";
    }

    @Override
    public Elements getElement() {
        return null;
    }

    @Override
    public StatScaling getScaling() {
        return StatScaling.NONE;
    }

    @Override
    public String locDescForLangFile() {
        return "Increases the speed of your spell projectiles.";
    }

    @Override
    public String locNameForLangFile() {
        return "Increased Projectile Speed";
    }

    public static String GUID = "increased_proj_speed";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public IStatEffect getEffect() {
        return new IncreasedProjSpeedEffect();
    }

    private static class SingletonHolder {
        private static final IncreasedProjSpeedStat INSTANCE = new IncreasedProjSpeedStat();
    }
}
