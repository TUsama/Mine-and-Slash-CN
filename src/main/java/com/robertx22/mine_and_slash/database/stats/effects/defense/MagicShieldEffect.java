package com.robertx22.mine_and_slash.database.stats.effects.defense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TeamUtils;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.MathHelper;

public class MagicShieldEffect extends BaseDamageEffect {

    public static final MagicShieldEffect INSTANCE = new MagicShieldEffect();

    @Override
    public int GetPriority() {
        return Priority.Last.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Target;
    }

    @Override
    public DamageEffect activate(DamageEffect effect, StatData data, Stat stat) {

        float dmgReduced = MathHelper.clamp(effect.number, 0, effect.targetData.getResources()
            .getMagicShield());

        // should prevent allies magic shield from getting hit
        if (effect.source instanceof ServerPlayerEntity && effect.target instanceof ServerPlayerEntity) {
            if (TeamUtils.areOnSameTeam((ServerPlayerEntity) effect.source, (ServerPlayerEntity) effect.target)) {
                BlockEffect.applyKnockbackResist(effect.target);
                dmgReduced = 0;
            }
        } else {
            if (effect instanceof SpellDamageEffect) {
                if (effect.target instanceof TameableEntity) {
                    if (effect.source instanceof PlayerEntity) {
                        TameableEntity tame = (TameableEntity) effect.target;
                        if (tame.isOwner(effect.source)) {
                            dmgReduced = 0;
                        }
                    }
                }
            }
        }

        if (dmgReduced > 0) {

            effect.number -= dmgReduced;

            ResourcesData.Context ctx = new ResourcesData.Context(effect.targetData, effect.target,
                ResourcesData.Type.MAGIC_SHIELD, dmgReduced,
                ResourcesData.Use.SPEND
            );

            effect.targetData.getResources()
                .modify(ctx);

        }
        return effect;
    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {
        return effect.targetData.getResources()
            .getMagicShield() > 0;
    }

}

