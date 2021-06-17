package com.robertx22.mine_and_slash.database.stats.types.spell_calc;

import com.robertx22.mine_and_slash.database.stats.IAfterStatCalc;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.spells.StatScaling;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Masteries;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.List;

public class PlusLevelToAllAbilitiesStat extends Stat implements IAfterStatCalc, IGenerated<PlusLevelToAllAbilitiesStat> {

    @Override
    public boolean IsPercent() {
        return false;
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
    public String locDescForLangFile() {
        return "Increase all allocated spell levels.";
    }

    @Override
    public String locNameForLangFile() {
        return "To All Spell Levels";
    }

    @Override
    public String locDescLangFileGUID() {
        return Ref.MODID + ".stat_desc." + "plus_all_all_skill_levels";
    }

    @Override
    public String GUID() {
        return "plus_all_skill_lvls";
    }

    @Override
    public List<PlusLevelToAllAbilitiesStat> generateAllPossibleStatVariations() {
        List<PlusLevelToAllAbilitiesStat> list = new ArrayList<>();

        list.add(new PlusLevelToAllAbilitiesStat());

        return list;
    }

    @Override
    public void doAfterStatCalc(StatData data, EntityCap.UnitData unit, PlayerSpellCap.ISpellsCap spells) {
        int lvls = (int) data
            .getAverageValue();
        if (lvls > 0) {
            for (Masteries x : Masteries.values()) {
                spells.getAbilitiesData().addBonusAbilityLevelsTo(x, lvls);
            }
        }

    }

    @Override
    public boolean IsShownOnStatGui() {
        return false;
    }
}
