package com.robertx22.mine_and_slash.database.spells.entities.single_target_bolt;

import com.robertx22.mine_and_slash.database.spells.entities.bases.BaseElementalBoltEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GeometryUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class RimeballEntity extends BaseElementalBoltEntity {

    public RimeballEntity(EntityType<? extends RimeballEntity> type, World world) {
        super(type, world);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public ItemStack getItem() {
        return new ItemStack(Items.AIR);
    }

    public RimeballEntity(World worldIn) {

        super(EntityRegister.RIMEBALL, worldIn);

    }

    public RimeballEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.RIMEBALL, world);
    }

    @Override
    public void initSpellEntity() {
        this.setNoGravity(true);
        this.setDeathTime(getSpellData().configs.get(SC.DURATION_TICKS)
                .intValue());
    }

    @Override
    public Elements element() {
        return Elements.Water;
    }

    @Override
    public void onHit(LivingEntity entity) {
        entity.playSound(SoundEvents.BLOCK_GLASS_BREAK, 0.5f, 1.4f);
        SpellDamageEffect dmg = this.getSetupSpellDamage(entity);
        dmg.number *= 0.5F; //halves damage after adding imbue, if available
        dmg.Activate();

    }

    @Override
    public void onTick() {

        if (world.isRemote) {
            if (this.ticksExisted > 1) {
                for (int i = 0; i < 5; i++) {
                    Vec3d p = GeometryUtils.getRandomPosInRadiusCircle(getPositionVector(), 0.2F);
                    ParticleUtils.spawn(ParticleTypes.ITEM_SNOWBALL, world, p);
                }
            }

        }
    }
}