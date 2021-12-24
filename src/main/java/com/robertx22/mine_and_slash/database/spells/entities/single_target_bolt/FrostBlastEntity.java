package com.robertx22.mine_and_slash.database.spells.entities.single_target_bolt;

import com.robertx22.mine_and_slash.database.spells.entities.bases.BaseElementalBoltEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ParticleRegister;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GeometryUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.FMLPlayMessages;

import java.util.List;

public class FrostBlastEntity extends BaseElementalBoltEntity {

    public FrostBlastEntity(EntityType<? extends FrostBlastEntity> type, World world) {
        super(type, world);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public ItemStack getItem() {
        return new ItemStack(Items.AIR);
    }

    public FrostBlastEntity(World worldIn) {

        super(EntityRegister.FROST_BLAST, worldIn);

    }

    public FrostBlastEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.FROST_BLAST, world);
    }
    @Override
    public double radius() {
        return getSpellData().configs.get(SC.RADIUS) / 2;
    }

    @Override
    public void initSpellEntity() {
        this.setNoGravity(false);
        this.setDeathTime(40);
    }

    @Override
    public Elements element() {
        return Elements.Water;
    }

    @Override
    public void onHit(LivingEntity entity) {
        entity.playSound(SoundEvents.BLOCK_GLASS_BREAK, 0.8f, 1.4f);

        ParticlePacketData pdata = new ParticlePacketData(entity.getPosition(), ParticleEnum.FROST_NOVA);
        pdata.radius = (float) radius();
        ParticleEnum.FROST_NOVA.sendToClients(this, pdata);

        List<LivingEntity> entities = EntityFinder.start(getCaster(), LivingEntity.class, entity.getPositionVector())
                .radius(radius()).searchFor(EntityFinder.SearchFor.ENEMIES)
                .build();

        for (LivingEntity x : entities) {
            dealSpellDamageTo(x);
        }

    }

    @Override
    public void onTick() {

        if (world.isRemote) {
            if (this.ticksExisted > 1) {
                Vec3d p = GeometryUtils.getRandomPosInRadiusCircle(getPositionVector(), 0.3F);
                for (int i = 0; i < 20; i++) {
                    ParticleUtils.spawn(ParticleTypes.ITEM_SNOWBALL, world, p);
                }
                ParticleUtils.spawn(ParticleTypes.CLOUD, world, p);
            }

        }

    }
}