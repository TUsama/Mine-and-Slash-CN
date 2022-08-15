package com.robertx22.mine_and_slash.database.stats.effects.defense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.database.stats.types.defense.BlockStrength;
import com.robertx22.mine_and_slash.database.stats.types.defense.DodgeRating;
import com.robertx22.mine_and_slash.database.stats.types.resources.Energy;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ShieldItem;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.util.UUID;

import static net.minecraft.entity.SharedMonsterAttributes.KNOCKBACK_RESISTANCE;

public class BlockEffect extends BaseDamageEffect {

    @Override
    public int GetPriority() {
        return Priority.Last.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Target;
    }

    public float getEnergyCost(int lvl) {
        return Energy.getInstance()
                .getScaling()
                .scale(8, lvl);
    }

    @Override
    public DamageEffect activate(DamageEffect effect, StatData data, Stat stat) {

        float blockval = MathHelper.clamp(data.getAverageValue(), stat.minimumValue, stat.maximumValue);

        float postblock = effect.number; // this is the damage

        float cost = getEnergyCost(effect.targetData.getLevel());

        ResourcesData.Context ctx = new ResourcesData.Context(effect.targetData, effect.target,
                ResourcesData.Type.ENERGY, cost,
                ResourcesData.Use.SPEND
        );

        if (isActivelyBlocking(effect.target, DamageSource.causeMobDamage(effect.source))) { // if actively blocking, block chance x2
            if (effect.targetData.getResources()
                    .hasEnough(ctx)) {
                effect.targetData.getResources()
                        .modify(ctx);
                effect.isActivelyBlocking = true;
                blockval = Math.min(blockval * 2, stat.maximumValue);
                if (RandomUtils.roll(blockval)) { // on success, nullify damage
                    postblock *= 0;
                    effect.isFullyBlocked = true;
                    SoundUtils.playSound(effect.source, SoundEvents.ITEM_SHIELD_BLOCK, 1.1F, 1);
                    SoundUtils.playSound(effect.target, SoundEvents.ITEM_SHIELD_BLOCK, 1.1F, 1);
                } else { // otherwise, reduce by half still
                    effect.isPartiallyBlocked = true;
                    postblock *= 0.5F;
                }
            }
        } else if (RandomUtils.roll(blockval)) { // but on success still reduce by 25 percent
            effect.removeKnockback();
            postblock *= 0.75F;
        }

        effect.number = postblock;

        return effect;
    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {
        return canBlockDamageSource(effect.target);
    }

    public static AttributeModifier MOD = new AttributeModifier(UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d"),
        Ref.MODID + "knockbackresist", 100,
        AttributeModifier.Operation.ADDITION
    );

    private boolean canBlockDamageSource(LivingEntity target) {

        ResourceLocation id = target.getHeldItemOffhand().getItem().getRegistryName();
        String namespace = id.getNamespace();

        return (target.getHeldItemOffhand().getItem() instanceof ShieldItem || namespace.contains("shield"));
    }

    private boolean isActivelyBlocking(LivingEntity target, DamageSource damageSourceIn) {
        if (target.isActiveItemStackBlocking()) {
            Vec3d vec3d = damageSourceIn.getDamageLocation();

            if (vec3d != null) {
                Vec3d vec3d1 = target.getLook(1.0F);
                Vec3d vec3d2 = vec3d.subtractReverse(new Vec3d(target.posX, target.posY, target.posZ))
                        .normalize();
                vec3d2 = new Vec3d(vec3d2.x, 0.0D, vec3d2.z);

                if (vec3d2.dotProduct(vec3d1) < 0.0D) {
                    return true;
                }
            }
        }
        return false;
    }


    public static void applyKnockbackResist(LivingEntity entity) {

        if (entity.getAttribute(KNOCKBACK_RESISTANCE)
                .hasModifier(MOD) == false) {
            entity.getAttribute(KNOCKBACK_RESISTANCE)
                    .applyModifier(MOD);
        }

    }

    public static void removeKnockbackResist(LivingEntity entity) {

        if (entity.getAttribute(KNOCKBACK_RESISTANCE)
                .hasModifier(MOD)) {
            entity.getAttribute(KNOCKBACK_RESISTANCE)
                    .removeModifier(MOD);
        }

    }
}
