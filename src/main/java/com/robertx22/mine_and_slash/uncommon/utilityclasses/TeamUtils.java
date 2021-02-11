package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import com.robertx22.mine_and_slash.uncommon.capability.server_wide.TeamCap;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TeamUtils {

    public static List<PlayerEntity> getOnlineTeamMembers(PlayerEntity player) {

        List<PlayerEntity> players = new ArrayList<>();
        //players.add(player);

        //System.out.println("Players = " + players);

        /*if (player.getTeam() != null) {
            try {
                player.getServer()
                    .getPlayerList()
                    .getPlayers()
                    .forEach(x -> {
                        if (player.getTeam()
                            .isSameTeam(x.getTeam())) {
                            players.add(x);
                        }
                    });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/

        //System.out.println("Players (Teams) = " + players);

        //if (players.size() < 2) {

            TeamCap.ITeamData team = TeamCap.getCapability();

            //List<PlayerEntity> list = new ArrayList<>();

            if (team.isPlayerInATeam((ServerPlayerEntity) player)) {
                players.addAll(team.getPlayersInTeam((ServerPlayerEntity) player)
                        .stream()
                        .filter(x -> x.getDistance(player) < 500)
                        .collect(Collectors.toList()));
            }
            else {
                players.add(player);
            }
        //}

        //if (players == null) {
        //    players = new ArrayList<>();
        //}
        //if (players.isEmpty()) {
        //    players.add(player);
        //}

        //System.out.println("Players (Slash) = " + players);

        return players;

    }

    public static boolean areOnSameTeam(PlayerEntity p1, PlayerEntity p2) {

        /*boolean vanilla = p1.getTeam() != null && p2.getTeam() != null && p1.getTeam()
            .isSameTeam(p2.getTeam());
        //System.out.println("Same team Vanilla: " + vanilla);

        if (vanilla) {
            return vanilla;
        }*/

        boolean mine = TeamCap.getCapability()
            .isOnSameTeam((ServerPlayerEntity) p1, (ServerPlayerEntity) p2);
        System.out.println("Same team Slash: " + mine);

        return mine;

    }

}
