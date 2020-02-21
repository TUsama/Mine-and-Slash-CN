package com.robertx22.mine_and_slash.potion_effects.alchemy_pot_buffs;

import com.robertx22.mine_and_slash.potion_effects.bases.IDefaultApplyStatPotion;
import com.robertx22.mine_and_slash.potion_effects.bases.data.ExtraPotionData;
import com.robertx22.mine_and_slash.professions.blocks.bases.Professions;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BaseEffect extends Effect implements IAutoLocName, IDefaultApplyStatPotion {

    public String guid;
    public String name;
    public int level;
    public List<StatModData> mods;

    public BaseEffect(String guid, String name, int level, List<StatModData> mods) {
        super(EffectType.BENEFICIAL, 0);
        this.guid = guid;
        this.name = name;
        this.level = level;
        this.mods = mods;
    }

    public BaseEffect(String guid, String name, int level, StatModData mod) {
        this(guid, name, level, Arrays.asList(mod));
    }

    @Override
    public List<ExactStatData> getStatsAffected(EntityCap.UnitData data, ExtraPotionData extraData) {
        return new ArrayList<>();
    }

    public abstract Professions proffesion();

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Potions;
    }

    @Override
    public String locNameLangFileGUID() {
        return this.getRegistryName().toString();
    }

    @Override
    public String locNameForLangFile() {
        return name;
    }

    @Override
    public String GUID() {
        return guid;
    }

    @Override
    public List<StatModData> statsMods() {
        return mods;
    }

    @Override
    public int getLevel() {
        return this.level;
    }
}
