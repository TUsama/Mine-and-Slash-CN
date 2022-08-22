package com.robertx22.mine_and_slash.onevent.world;

import net.minecraft.world.GameRules;
import net.minecraft.world.IWorld;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class WorldEvents {

    @SubscribeEvent
    public static void onWorldLoad(WorldEvent.Load event) {
        if (event.getWorld().isRemote()) return;
        IWorld world = event.getWorld();
        WorldInfo info = world.getWorldInfo();
        GameRules rules = info.getGameRulesInstance();

        rules.get(GameRules.KEEP_INVENTORY).set(true, world.getWorld().getServer());
    }
}