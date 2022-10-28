package com.robertx22.mine_and_slash.onevent.player;

import com.robertx22.mine_and_slash.new_content.chests.MapChestBlock;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.block.Block;
import net.minecraft.block.TorchBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnDungeonBlockEvents {

    public static boolean canBreakBlock(Block block) {

        try {
            ResourceLocation id = block.getRegistryName();

            String namespace = id.getNamespace();

            if (namespace.contains("grave") || namespace.contains("tomb")) {
                return true;
            }

            //hardcoded allowables

            //if (namespace.contains("turtle_egg")) {
            //    return true;
            //}

        } catch (Exception e) {
        }

        if (block instanceof MapChestBlock) {
            return true;
        }

        return false;

    }

    private static boolean isDungeon(Entity entity) {
        return entity != null && WorldUtils.isMapWorldClass(entity.world);
    }

    // disable all block breaking in maps, except stuff like my chests
    @SubscribeEvent
    public static void onBreak(BlockEvent.BreakEvent event) {

        try {

            if (isDungeon(event.getPlayer())) {

                if (event.getPlayer()
                    .isCreative()) {
                    return;
                }

                boolean can = canBreakBlock(event.getState()
                    .getBlock());

                if (!can) {
                    event.setCanceled(true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // stop all block placing in dungeons
    @SubscribeEvent
    public static void onPlace(BlockEvent.EntityPlaceEvent event) {
        if (isDungeon(event.getEntity())) {

            if (event.getEntity() instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) event.getEntity();

                if (player.isCreative()) {
                    return;
                }
            }

            if (!(event.getState().getBlock() instanceof TorchBlock)) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public void teleportStart(EnderTeleportEvent event)
    {
        // restrict player teleports
        if (event.getEntity() instanceof ServerPlayerEntity)
        {
        }
    }

    @SubscribeEvent
    public void useItem(LivingEntityUseItemEvent.Start event)
    {
        ItemStack stack = event.getItem();

        // do not run this function on non-players
        if (!(event.getEntityLiving() instanceof ServerPlayerEntity))
        {
            return;
        }

        // cancel chorus fruits in any of my dimensions
        if (isDungeon(event.getEntity()))
        {
            if (stack.getItem() == Items.CHORUS_FRUIT)
            {
                event.setDuration(-1); // stop the chorus fruit from being used
                event.setCanceled(true);
                //DimDungeons.LOGGER.info("CANCELLING CHORUS FRUIT at START!");
            }
        }
    }

}
