package com.robertx22.mine_and_slash.database.rarities.mobs;

import com.robertx22.mine_and_slash.database.rarities.MobRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseBossRarity;

public class BossMobRarity extends BaseBossRarity implements MobRarity {

    private BossMobRarity() {
    }

    public static BossMobRarity getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public float StatMultiplier() {
        return 5.5F;
    }

    @Override
    public float DamageMultiplier() {
        return 5F;
    }

    @Override
    public float HealthMultiplier() {
        return 18F;
    }

    @Override
    public float LootMultiplier() {
        return 20F;
    }

    @Override
    public float ExpOnKill() {
        return 300;
    }

    @Override
    public float oneAffixChance() {
        return 100;
    }

    @Override
    public float bothAffixesChance() {
        return 100;
    }

    @Override
    public int Weight() {
        return 0;
    }

    private static class SingletonHolder {
        private static final BossMobRarity INSTANCE = new BossMobRarity();
    }
}

