package com.robertx22.mine_and_slash.database.rarities.mobs;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.database.rarities.MobRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseUncommon;

public class UncommonMob extends BaseUncommon implements MobRarity {

    private UncommonMob() {
    }

    public static UncommonMob getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public float StatMultiplier() {
        return 1.2F;
    }

    @Override
    public float DamageMultiplier() {
        return 1.25F;
    }

    @Override
    public float HealthMultiplier() {
        return 1.35F;
    }

    @Override
    public String locNameForLangFile() {
        return "Rare";
    }

    @Override
    public float LootMultiplier() {
        return 1.25F;
    }

    @Override
    public float oneAffixChance() {
        return 50;
    }

    @Override
    public float bothAffixesChance() {
        return 0F;
    }

    @Override
    public float ExpOnKill() {
        return 10;
    }

    @Override
    public int Weight() {
        return ModConfig.INSTANCE.RarityWeightConfig.MOBS.UNCOMMON_WEIGHT.get();
    }

    private static class SingletonHolder {
        private static final UncommonMob INSTANCE = new UncommonMob();
    }
}
