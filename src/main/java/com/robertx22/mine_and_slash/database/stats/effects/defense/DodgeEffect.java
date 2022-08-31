package com.robertx22.mine_and_slash.database.stats.effects.defense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseStatEffect;
import com.robertx22.mine_and_slash.database.stats.types.defense.DodgeRating;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData.EffectTypes;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.IIgnorable;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.IPenetrable;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.util.SoundEvents;

import java.util.UUID;

import static net.minecraft.entity.SharedMonsterAttributes.KNOCKBACK_RESISTANCE;

public class DodgeEffect extends BaseStatEffect<DamageEffect> {

    public DodgeEffect() {
        super(DamageEffect.class);
    }

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
        //int pene = 0;

        //if (effect instanceof IIgnorable) {
        //    IIgnorable ipen = (IIgnorable) effect;
        //    pene = ipen.GetDodgeIgnore();
        //    pene = 1 - pene / 100;
        //}

        DodgeRating dodge = (DodgeRating) stat;

        float chance = dodge.GetUsableValue(effect.targetData.getLevel(), (int) data.getAverageValue()) * 100;
        //float chance = dodge.GetUsableValue(effect.targetData.getLevel(), (int) data.getAverageValue() * pene) * 100;

        if (RandomUtils.roll(chance)) {
            DamageEffect dmgeffect = effect;

            effect.number = 0;
            dmgeffect.isDodged = true;
            applyKnockbackResist(effect.target);
            SoundUtils.playSound(effect.source, SoundEvents.ENTITY_HORSE_BREATHE, 1.5F, 1.5F);
            SoundUtils.playSound(effect.target, SoundEvents.ENTITY_HORSE_BREATHE, 1.5F, 1.5F);

        }
        return effect;
    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {
        return !effect.getEffectType()
            .equals(EffectTypes.SPELL) && !effect.getEffectType()
                .equals(EffectTypes.DOT_DMG) && !effect.getEffectType()
                .equals(EffectTypes.BOLT) && !effect.getEffectType()
                .equals(EffectTypes.REFLECT);
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
