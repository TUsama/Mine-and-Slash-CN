package com.robertx22.mine_and_slash.database.stats.effects.mob_spell_effects;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.SpellDamage;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModSounds;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.all.MobChillEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData.EffectTypes;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class FrostNovaEffect extends BaseDamageEffect {

    public static final FrostNovaEffect INSTANCE = new FrostNovaEffect();

    @Override
    public int GetPriority() {
        return Priority.Second.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    @Override
    public DamageEffect activate(DamageEffect effect, StatData data, Stat stat) {

        SoundUtils.playSound(effect.source, SoundEvents.BLOCK_GLASS_BREAK, 1.25F, 1);

        float num = effect.number * 0.5F;
        float radius = 3F;

        ParticleEnum.sendToClients(effect.target,
                new ParticlePacketData(effect.target.getPosition(), ParticleEnum.NOVA).radius(
                        radius)
                        .type(ParticleTypes.CLOUD)
                        .amount(25)
        );

        List<LivingEntity> entities = EntityFinder.start(effect.source, LivingEntity.class, effect.target.getPositionVector())
                .radius(radius)
                .build();

        for (LivingEntity en : entities) {

            if (en != effect.source) {
                PotionEffectUtils.apply(MobChillEffect.INSTANCE, effect.source, effect.target);

                DamageEffect dmg = new DamageEffect(
                        null, effect.source, en, (int) num, EffectData.EffectTypes.SPELL, WeaponTypes.None);
                dmg.element = Elements.Water;
                dmg.Activate();
            }
        }

        return effect;
    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {
        return !(effect.getEffectType()
                .equals(EffectTypes.SPELL));
    }

}