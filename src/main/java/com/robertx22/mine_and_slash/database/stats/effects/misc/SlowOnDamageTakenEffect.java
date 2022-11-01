package com.robertx22.mine_and_slash.database.stats.effects.misc;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class SlowOnDamageTakenEffect extends BaseDamageEffect {

    public static final SlowOnDamageTakenEffect INSTANCE = new SlowOnDamageTakenEffect();

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

        ParticleEnum.sendToClients(effect.target,
                new ParticlePacketData(effect.target.getPosition(), ParticleEnum.NOVA).radius(
                        1)
                        .type(ParticleTypes.INSTANT_EFFECT)
                        .amount(5)
        );

        effect.target.addPotionEffect(new EffectInstance(Effects.SPEED, 60, 0));

        return effect;
    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {
        return effect.isDmgAllowed();
    }

}