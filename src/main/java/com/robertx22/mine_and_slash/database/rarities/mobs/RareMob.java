package com.robertx22.mine_and_slash.database.rarities.mobs;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.database.rarities.MobRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseRare;

public class RareMob extends BaseRare implements MobRarity {

    private RareMob() {
    }

    public static RareMob getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public float StatMultiplier() {
        return 2.1F;
    }

    @Override
    public float DamageMultiplier() {
        return 2F;
    }

    @Override
    public float HealthMultiplier() {
        return 2F;
    }

    @Override
    public String locNameForLangFile() {
        return "Veteran";
    }

    @Override
    public float LootMultiplier() {
        return 2F;
    }

    @Override
    public float oneAffixChance() {
        return 50;
    }

    @Override
    public float bothAffixesChance() {
        return 12.5F;
    }

    @Override
    public float ExpOnKill() {
        return 12;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.MOBS.RARE_WEIGHT.get();
    }

    private static class SingletonHolder {
        private static final RareMob INSTANCE = new RareMob();
    }
}
