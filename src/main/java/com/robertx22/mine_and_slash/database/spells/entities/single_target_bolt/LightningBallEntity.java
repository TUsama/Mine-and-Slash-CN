package com.robertx22.mine_and_slash.database.spells.entities.single_target_bolt;

import com.robertx22.mine_and_slash.database.spells.entities.bases.BaseElementalBoltEntity;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ParticleRegister;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GeometryUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class LightningBallEntity extends BaseElementalBoltEntity {

    public LightningBallEntity(EntityType<? extends LightningBallEntity> type, World world) {
        super(type, world);
    }

    public LightningBallEntity(World worldIn) {

        super(EntityRegister.LIGHTNING_BALL, worldIn);

    }

    public LightningBallEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.LIGHTNING_BALL, world);

    }

    @Override
    public Elements element() {
        return Elements.Thunder;
    }

    @Override
    public void onHit(LivingEntity entity) {

        SpellDamageEffect dmg = dealSpellDamageTo(entity, new Options().activatesEffect(false));

        dmg.Activate();

        SoundUtils.playSound(this, SoundEvents.ENTITY_GENERIC_HURT, 0.8F, 1F);

    }

    @Override
    public void onTick() {

        if (world.isRemote) {
            if (this.ticksExisted > 1) {
                for (int i = 0; i < 4; i++) {
                    Vec3d p = GeometryUtils.getRandomPosInRadiusCircle(getPositionVector(), 0.3F);
                    ParticleUtils.spawn(ParticleRegister.THUNDER, world, p);
                }
            }

        }

    }

}