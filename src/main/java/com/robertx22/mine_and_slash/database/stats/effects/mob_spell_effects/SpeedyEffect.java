package com.robertx22.mine_and_slash.database.stats.effects.mob_spell_effects;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;

public class SpeedyEffect extends BaseDamageEffect {

    public static final SpeedyEffect INSTANCE = new SpeedyEffect();

    @Override
    public int GetPriority() {
        return Priority.Second.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Target;
    }

    @Override
    public DamageEffect activate(DamageEffect effect, StatData data, Stat stat) {

        SoundUtils.playSound(effect.target, SoundEvents.ENTITY_SPLASH_POTION_BREAK, 1.5F, 1.25F);

        ParticleEnum.sendToClients(effect.target,
                new ParticlePacketData(effect.target.getPosition(), ParticleEnum.NOVA).radius(
                        1)
                        .type(ParticleTypes.INSTANT_EFFECT)
                        .amount(5)
        );

        effect.target.addPotionEffect(new EffectInstance(Effects.SPEED, 100, 0));

        return effect;
    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {
        return true;
    }

}