package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.effects.game_changers.BloodMageRestoreManaEffect;
import com.robertx22.mine_and_slash.database.stats.effects.game_changers.BloodMageSpendBloodInsteadEffect;
import com.robertx22.mine_and_slash.database.stats.types.resources.Mana;
import com.robertx22.mine_and_slash.database.stats.types.resources.ManaRegen;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAffectsStats;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatMultipleEffects;

import java.util.Arrays;
import java.util.List;

public class BloodMage extends BaseGameChangerTrait implements IStatMultipleEffects, IAffectsStats {

    private BloodMage() {
    }

    public static final BloodMage INSTANCE = new BloodMage();

    @Override
    public String locDescForLangFile() {
        return "Your have no mana, you use blood instead. Max blood is half of your health. You replenish blood with "
                + "any non spell related health restoration method like hp regen or lifesteal.";
    }

    @Override
    public String getIconPath() {
        return "game_changers/blood_mage";
    }

    @Override
    public String locNameForLangFile() {
        return "Blood Mage";
    }

    @Override
    public String GUID() {
        return "blood_mage_trait";
    }

    @Override
    public List<IStatEffect> getEffects() {
        return Arrays.asList(BloodMageSpendBloodInsteadEffect.getInstance(), BloodMageRestoreManaEffect.getInstance());
    }

    @Override
    public void affectStats(EntityCap.UnitData data, StatData statData) {
        data.getUnit().getCreateStat(Mana.GUID).Flat -= Integer.MAX_VALUE;
        data.getUnit().getCreateStat(ManaRegen.GUID).Flat -= Integer.MAX_VALUE;
    }
}


