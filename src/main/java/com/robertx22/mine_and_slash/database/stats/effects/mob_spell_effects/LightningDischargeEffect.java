package com.robertx22.mine_and_slash.database.stats.effects.mob_spell_effects;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.SpellDamage;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModSounds;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
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

public class LightningDischargeEffect extends BaseDamageEffect {

    public static final LightningDischargeEffect INSTANCE = new LightningDischargeEffect();

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

        //source and target are reversed because the side is target

        SoundUtils.playSound(effect.source, ModSounds.THUNDER.get(), 1.25F, 1);

        float num = effect.number * 0.5F;
        float radius = 4F;

        ParticlePacketData pdata = new ParticlePacketData(effect.source.getPosition()
                .up(1), ParticleEnum.CHARGED_NOVA);
        pdata.radius = radius;
        ParticleEnum.CHARGED_NOVA.sendToClients(effect.source, pdata);

        List<LivingEntity> entities = EntityFinder.start(effect.source, LivingEntity.class, effect.source.getPositionVector())
                .radius(radius)
                .build();

        for (LivingEntity en : entities) {

            if (en != effect.source) {
                DamageEffect dmg = new DamageEffect(
                        null, effect.source, en, (int) num, EffectData.EffectTypes.SPELL, WeaponTypes.None);
                dmg.element = Elements.Thunder;
                dmg.Activate();
            }

        }

        return effect;
    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {
        return !(effect.getEffectType()
                .equals(EffectTypes.SPELL)); // activates on any damage type
    }

}