package com.robertx22.mine_and_slash.database.stats.effects.mob_spell_effects;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.database.stats.effects.offense.PhysicalToHighestEle;
import com.robertx22.mine_and_slash.database.stats.types.game_changers.RefreshingBreeze;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalSpellDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.database.stats.types.resources.Health;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModSounds;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.AttackSpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData.EffectTypes;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class FireStrikeEffect extends BaseDamageEffect {

    public static final FireStrikeEffect INSTANCE = new FireStrikeEffect();

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

        Vec3d look = effect.source.getLookVec()
                .scale(3);

        List<LivingEntity> list = EntityFinder.start(effect.source, LivingEntity.class, effect.source.getPositionVector()
                .add(look)
                .add(0, effect.source.getHeight() / 2, 0))
                .finder(EntityFinder.Finder.RADIUS)
                .searchFor(EntityFinder.SearchFor.ENEMIES)
                .radius(2)
                .height(2)
                .build();

        SoundUtils.playSound(effect.source, ModSounds.FIREBALL.get(), 1.25F, 1);

        float wepdmg = effect.sourceData.getUnit()
                .peekAtStat(PhysicalDamage.GUID)
                .getAverageValue();
        float elespelldmg = effect.sourceData.getUnit()
                .peekAtStat("spell_fire_damage")
                .getAverageValue();

        float num = wepdmg * 0.75F * (1 + elespelldmg / 100);

        for (LivingEntity en : list) {

            ParticleEnum.sendToClients(
                    en.getPosition(), en.world,
                    new ParticlePacketData(en.getPositionVector(), ParticleEnum.AOE).radius(1)
                            .motion(new Vec3d(0, 0, 0))
                            .type(ParticleTypes.FLAME)
                            .amount((int) (30)));

            if (en != effect.source) {
                DamageEffect dmg = new DamageEffect(
                        null, effect.source, en, (int) num, EffectData.EffectTypes.SPELL, WeaponTypes.None);
                dmg.element = Elements.Fire;
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