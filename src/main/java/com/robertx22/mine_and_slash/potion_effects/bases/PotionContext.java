package com.robertx22.mine_and_slash.potion_effects.bases;

import com.robertx22.mine_and_slash.potion_effects.bases.data.ExtraPotionData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.LivingEntity;

public class PotionContext {
    public LivingEntity entity;
    public ExtraPotionData data;
    public LivingEntity caster;
    public EntityCap.UnitData casterData;
    public EntityCap.UnitData entityData;

    public PotionContext(LivingEntity entity, ExtraPotionData data, LivingEntity caster) {
        this.entity = entity;
        this.data = data;
        this.caster = caster;

        this.casterData = Load.Unit(caster);
        this.entityData = Load.Unit(entity);
    }
}
