package com.robertx22.mine_and_slash.onevent.player;

import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerMapCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.stats.ServerStatisticsManager;
import net.minecraft.stats.Stats;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OnDeath {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onPlayerDeath(LivingDeathEvent evt) {

        try {

            LivingEntity living = evt.getEntityLiving();

            if (living.world.isRemote) {
                return;
            }

            if (living instanceof PlayerEntity) {

                PlayerEntity player = (PlayerEntity) living;

                ServerStatisticsManager serverstatisticsmanager = ((ServerPlayerEntity)player).getStats();

                if (serverstatisticsmanager.getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_DEATH)) > 100)  {
                    Load.Unit(player)
                            .onDeath(player);
                }

                //if (WorldUtils.isMapWorldClass(living.world)) {

                //    PlayerMapCap.IPlayerMapData data = Load.playerMapData(player);

                    //data.onPlayerDeath(player);

                //}

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
