package com.robertx22.mine_and_slash.database.spells.entities.summons;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.model.SkeletonModel;
import net.minecraft.util.ResourceLocation;

public class SkeletonPetRenderer extends MobRenderer<SkeletonPetEntity, SkeletonModel<SkeletonPetEntity>> {

    private static final ResourceLocation SKELETON_TEXTURES = new ResourceLocation(Ref.MODID, "textures/entity/skeleton.png");

    public SkeletonPetRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SkeletonModel<SkeletonPetEntity>(), 0.5F);
    }


    @Override
    protected float handleRotationFloat(SkeletonPetEntity livingBase, float partialTicks) {
        return 1.5393804F;
    }

    @Override
    public void render(SkeletonPetEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }


    @Override
    public ResourceLocation getEntityTexture(SkeletonPetEntity entity) {
        return SKELETON_TEXTURES;
    }

}