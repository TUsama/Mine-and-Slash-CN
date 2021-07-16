package com.robertx22.mine_and_slash.database.spells.entities.trident;

import com.robertx22.mine_and_slash.mmorpg.registers.common.EntityRegister;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class ThunderspearEntity extends BaseTridentEntity {

    public ThunderspearEntity(World world) {
        super(EntityRegister.THUNDER_SPEAR, world);
    }

    public ThunderspearEntity(EntityType type, World world) {
        super(type, world);
    }

    public ThunderspearEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super(EntityRegister.THUNDER_SPEAR, world);
    }

    @Override
    public void onHit(LivingEntity en) {

        LivingEntity shooter = this.getCaster();

        shooter.addPotionEffect(new EffectInstance(Effects.SPEED, 40, 0));

        DamageEffect dmg = dealSpellDamageTo((LivingEntity) en, new Options().activatesEffect(false));
        dmg.Activate();
    }

}
