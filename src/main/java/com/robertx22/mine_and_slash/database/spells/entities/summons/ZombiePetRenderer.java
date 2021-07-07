package com.robertx22.mine_and_slash.database.spells.entities.summons;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.SkeletonModel;
import net.minecraft.util.ResourceLocation;

public class ZombiePetRenderer extends MobRenderer<ZombiePetEntity, SkeletonModel<ZombiePetEntity>> {

    private static final ResourceLocation ZOMBIE_TEXTURES = new ResourceLocation(Ref.MODID, "textures/entity/zombie.png");

    public ZombiePetRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SkeletonModel<ZombiePetEntity>(), 0.5F);
    }


    @Override
    protected float handleRotationFloat(ZombiePetEntity livingBase, float partialTicks) {
        return 1.5393804F;
    }

    @Override
    public void render(ZombiePetEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }


    @Override
    public ResourceLocation getEntityTexture(ZombiePetEntity entity) {
        return ZOMBIE_TEXTURES;
    }

}