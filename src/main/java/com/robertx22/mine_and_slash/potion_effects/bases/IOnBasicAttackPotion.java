package com.robertx22.mine_and_slash.potion_effects.bases;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;

public interface IOnBasicAttackPotion {

    void OnBasicAttack(EffectInstance instance, LivingEntity source, LivingEntity target);

}
