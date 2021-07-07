package com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Summon extends SpellCastType {

    @Override
    public boolean cast(SpellCastContext ctx) {
        /*World world = ctx.caster.world;

        RayTraceResult ray = ctx.caster.pick(10D, 0.0F, false);

        Vec3d pos = ray.getHitVec();

        if (ctx.caster instanceof PlayerEntity == false) {
            pos = ctx.caster.getPositionVector();
        }

        Entity en = SpellUtils.spawnSummon(ctx.configForSummonedEntities, ctx.spell.getImmutableConfigs()
            .newEntitySummoner()
            .apply(world), ctx.spell, ctx.caster);

        en.setPosition(pos.x, pos.y + 0.5F, pos.z);

        ctx.caster.world.addEntity(en);*/
        return true;
    }
}
