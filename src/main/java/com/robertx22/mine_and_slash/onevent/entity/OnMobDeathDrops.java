package com.robertx22.mine_and_slash.onevent.entity;

import com.robertx22.mine_and_slash.config.whole_mod_entity_configs.ModEntityConfig;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.loot.LootUtils;
import com.robertx22.mine_and_slash.loot.MasterLootGen;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.registers.common.CriteriaRegisters;
import com.robertx22.mine_and_slash.packets.DmgNumPacket;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.capability.entity.BossCap;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.capability.world.AntiMobFarmCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.NumberUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TeamUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.system.CallbackI;

import java.util.ArrayList;
import java.util.List;

public class OnMobDeathDrops {

    @SubscribeEvent
    public static void mobOnDeathDrop(LivingDeathEvent event) {

        try {

            LivingEntity mobKilled = event.getEntityLiving();

            if (mobKilled.world.isRemote) {
                return;
            }

            if (!(mobKilled instanceof PlayerEntity)) {
                if (Load.hasUnit(mobKilled)) {

                    UnitData mobKilledData = Load.Unit(mobKilled);

                    Entity killerEntity = mobKilledData.getHighestDamageEntity(mobKilled);

                    if (killerEntity instanceof ServerPlayerEntity) {

                        ServerPlayerEntity player = (ServerPlayerEntity) killerEntity;
                        UnitData playerData = Load.Unit(player);

                        BossCap.IBossData boss = Load.boss(mobKilled);

                        CriteriaRegisters.DROP_LVL_PENALTY_TRIGGER.trigger(player, playerData, mobKilledData);

                        CriteriaRegisters.KILL_RARITY_MOB_TRIGGE.trigger(player, mobKilledData);

                        CriteriaRegisters.KILL_BOSS_TRIGGER.trigger(player, boss);

                        ModEntityConfig config = SlashRegistry.getEntityConfig(mobKilled, mobKilledData);

                        float loot_multi = (float) config.LOOT_MULTI;
                        float exp_multi = (float) config.EXP_MULTI;

                        if (loot_multi > 0 || exp_multi > 0) {
                            player.world.getCapability(AntiMobFarmCap.Data)
                                .ifPresent(x -> x.onValidMobDeathByPlayer(mobKilled));
                        }

                        if (loot_multi > 0) {
                            MasterLootGen.genAndDrop(mobKilledData, playerData, mobKilled, player);
                        }

                        if (exp_multi > 0) {
                            GiveExp(mobKilled, player, playerData, mobKilledData, exp_multi);
                        }
                    }
                }

            }

        } catch (

            Exception e) {
            e.printStackTrace();
        }

    }

    private static void GiveExp(LivingEntity victim, PlayerEntity killer, UnitData killerData, UnitData mobData, float multi) {

        int exp = (int) (mobData.getLevel() * Rarities.Mobs.get(mobData.getRarity())
            .ExpOnKill() * multi * SlashRegistry.getDimensionConfig(victim.world).EXP_MULTIPLIER);

        exp = (int) (exp * (1 + victim.getMaxHealth() / 20));

        if (victim instanceof SlimeEntity) {
            exp /= 10;
        }

        try {
            exp *= Load.antiMobFarm(victim.world)
                .getDropMultiForMob(victim);
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (WorldUtils.isMapWorldClass(victim.world)) {
            exp *= Load.world(killer.world)
                .getExpMultiplier(victim.getPosition(), victim.world);
        }

        if (exp > 0) {

            List<PlayerEntity> list = TeamUtils.getOnlineTeamMembers(killer); // list with ALL the members
            List<PlayerEntity> closeList = new ArrayList<>(); // list with only nearby members

            for (PlayerEntity p : list) {
                if (p.world == killer.world && p.getDistance(killer) <= 100) {
                    closeList.add(p);
                }
            }

            exp *= MathHelper.clamp(0.8F + 0.2F * closeList.size(), 1F, 2F); // cap bonus at 6 nearby party members

            exp /= closeList.size();

            for (PlayerEntity player : closeList) {
                int splitExp = (int) LootUtils.ApplyLevelDistancePunishment(mobData, Load.Unit(player), exp); // exp penalty individual to player

                if (splitExp > 0) {
                    DmgNumPacket packet = new DmgNumPacket(
                            victim, Elements.Nature, "+" + NumberUtils.formatNumber(splitExp) + " Exp!");
                    packet.isExp = true;
                    MMORPG.sendToClient(packet, (ServerPlayerEntity) player);

                    Load.Unit(player).PostGiveExpEvent(victim, player, splitExp);
                    }
                }
            }

        }
    }
