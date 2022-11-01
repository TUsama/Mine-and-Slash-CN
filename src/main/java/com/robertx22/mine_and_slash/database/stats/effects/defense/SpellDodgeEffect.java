package com.robertx22.mine_and_slash.database.stats.effects.defense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData.EffectTypes;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;

import java.util.UUID;

import static net.minecraft.entity.SharedMonsterAttributes.KNOCKBACK_RESISTANCE;

public class SpellDodgeEffect extends BaseDamageEffect {

    @Override
    public int GetPriority() {
        return Priority.AlmostLast.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Target;
    }

    @Override
    public DamageEffect activate(DamageEffect effect, StatData data, Stat stat) {
        if (RandomUtils.roll(data.getAverageValue())) {
            effect.number = 0;
            effect.isDodged = true;
            applyKnockbackResist(effect.target);
            SoundUtils.playSound(effect.source, SoundEvents.ENTITY_HORSE_BREATHE, 2.0F, 1.5F);
            SoundUtils.playSound(effect.target, SoundEvents.ENTITY_HORSE_BREATHE, 2.0F, 1.5F);

            ParticleEnum.sendToClients(
                    effect.target, new ParticlePacketData(effect.target.getPosition(), ParticleEnum.AOE).type(
                                    ParticleTypes.POOF)
                            .motion(new Vec3d(0, 0, 0))
                            .amount(10));
        }
        return effect;
    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {
        return effect.getEffectType()
            .equals(EffectTypes.SPELL);
    }

    public static AttributeModifier MOD = new AttributeModifier(UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d"),
            Ref.MODID + "knockbackresist", 100,
            AttributeModifier.Operation.ADDITION
    );

    public static void applyKnockbackResist(LivingEntity entity) {

        if (entity.getAttribute(KNOCKBACK_RESISTANCE)
                .hasModifier(MOD) == false) {
            entity.getAttribute(KNOCKBACK_RESISTANCE)
                    .applyModifier(MOD);
        }

    }

}
