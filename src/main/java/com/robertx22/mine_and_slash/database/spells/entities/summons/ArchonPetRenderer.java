package com.robertx22.mine_and_slash.database.spells.entities.summons;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.BlazeModel;
import net.minecraft.util.ResourceLocation;

public class ArchonPetRenderer extends MobRenderer<ArchonPetEntity, BlazeModel<ArchonPetEntity>> {

    private static final ResourceLocation ARCHON_TEXTURES = new ResourceLocation(Ref.MODID, "textures/entity/archon.png");

    public ArchonPetRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new BlazeModel<ArchonPetEntity>(), 0.5F);
    }


    @Override
    protected float handleRotationFloat(ArchonPetEntity livingBase, float partialTicks) {
        return 1.5393804F;
    }

    @Override
    public void render(ArchonPetEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }


    @Override
    public ResourceLocation getEntityTexture(ArchonPetEntity entity) {
        return ARCHON_TEXTURES;
    }

}