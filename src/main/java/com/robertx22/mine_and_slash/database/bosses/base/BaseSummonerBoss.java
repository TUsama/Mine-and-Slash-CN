package com.robertx22.mine_and_slash.database.bosses.base;

import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;

public abstract class BaseSummonerBoss extends com.robertx22.mine_and_slash.database.bosses.base.Boss {

    @Override
    public void onHealthTreshholdTriggered(LivingEntity en, BossData.HealthTreshhold t) {

        int amount = 2;
        if (t == BossData.HealthTreshhold.T_75) {
            amount = 3;
        } else if (t == BossData.HealthTreshhold.T_50) {
            amount = 4;
        } else if (t == BossData.HealthTreshhold.T_25) {
            amount = 8;
        } else if (t == BossData.HealthTreshhold.T_10) {
            amount = 10;
        }

        en.addPotionEffect(new EffectInstance(Effects.LEVITATION, 50, 1));
        en.addPotionEffect(new EffectInstance(Effects.SLOW_FALLING, 100, 10));
        en.addPotionEffect(new EffectInstance(Effects.SPEED, 150, 2));

        spawnMinions(en, amount);

    }

    public abstract EntityType<?> getMinionType(LivingEntity en);

    public void spawnMinions(LivingEntity en, int amount) {

        BlockPos p = en.getPosition();

        for (int i = 0; i < amount; i++) {

            MobEntity spawned = (MobEntity) getMinionType(en).create(en.world);

            spawnMinion(p, spawned, en.world);

        }
    }

    @Override
    public void applyStats(EntityCap.UnitData data, int level) {

        super.applyStats(data, level);

    }

}
