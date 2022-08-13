package com.robertx22.mine_and_slash.database.rarities.mobs;

import com.robertx22.mine_and_slash.database.rarities.MobRarity;
import com.robertx22.mine_and_slash.database.rarities.base.BaseBossRarity;

public class MinionMobRarity extends BaseBossRarity implements MobRarity {

    private MinionMobRarity() {
    }

    public static MinionMobRarity getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public float StatMultiplier() {
        return 1.75F;
    }

    @Override
    public float DamageMultiplier() {
        return 1.75F;
    }

    @Override
    public float HealthMultiplier() {
        return 4F;
    }

    @Override
    public float LootMultiplier() {
        return 4F;
    }

    @Override
    public float ExpOnKill() {
        return 15;
    }

    @Override
    public float oneAffixChance() {
        return 100;
    }

    @Override
    public float bothAffixesChance() {
        return 0;
    }

    @Override
    public int Weight() {
        return 0;
    }

    private static class SingletonHolder {
        private static final MinionMobRarity INSTANCE = new MinionMobRarity();
    }
}

