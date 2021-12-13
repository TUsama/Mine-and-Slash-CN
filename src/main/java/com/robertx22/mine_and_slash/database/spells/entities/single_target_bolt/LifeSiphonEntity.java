package com.robertx22.mine_and_slash.database.spells.entities.single_target_bolt;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.entities.bases.BaseElementalBoltEntity;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
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

public class LifeSiphonEntity extends BaseElementalBoltEntity {

    public LifeSiphonEntity(EntityType<? extends LifeSiphonEntity> type, World world) {
        super(type, world);
    }

    public LifeSiphonEntity(World worldIn) {

        super(EntityRegister.LIFE_SIPHON, worldIn);

    }

    public LifeSiphonEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.LIFE_SIPHON, world);

    }

    @Override
    public void initSpellEntity() {
        this.setDeathTime(60);
    }

    @Override
    public Elements element() {
        return Elements.Physical;
    }

    @Override
    public void onHit(LivingEntity entity) {

        SpellDamageEffect dmg = dealSpellDamageTo(entity, new Options().activatesEffect(false));

        entity.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0f, 0.7f);

        SpellUtils.heal(this.getSpellData().getSpell(), this.getSpellData().getCaster(world), dmg.number);

        dmg.Activate();


    }

    @Override
    public void onTick() {

        if (world.isRemote) {
            if (this.ticksExisted > 1) {
                for (int i = 0; i < 4; i++) {
                    Vec3d p = GeometryUtils.getRandomPosInRadiusCircle(getPositionVector(), 0.2F);
                    ParticleUtils.spawn(ParticleTypes.WITCH, world, p);
                }
            }

        }

    }

}