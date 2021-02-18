package com.robertx22.mine_and_slash.saveclasses;

import com.robertx22.mine_and_slash.dimensions.MapManager;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.system.CallbackI;

import javax.xml.soap.Text;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Storable
public class PlayerTeamsData {

    @Store
    public HashMap<String, String> playerIDxTeamIDMap = new HashMap<>();

    @Store
    public HashMap<String, Team> teamIDxTeamDataMap = new HashMap<>();

    public String getTeamId(PlayerEntity player) {
        return playerIDxTeamIDMap.getOrDefault(getPlayerId(player), "");
    }

    public static String getPlayerId(PlayerEntity player) {
        return player.getUniqueID()
            .toString();
    }

    @Storable
    public static class Team {

        public Team() {

        }

        public void invite(ServerPlayerEntity player) {
            invites.add(getPlayerId(player));

            player.sendMessage(new SText(TextFormatting.GREEN + "You have been invited to a party!"));
            player.sendMessage(new SText(TextFormatting.GREEN + "Type" +
                    " '/slash party join <player>'" + TextFormatting.GREEN + " to accept."));
            getPlayer(owner).sendMessage(new SText(TextFormatting.GREEN + "Invitation sent!"));
        }

        public boolean tryJoin(ServerPlayerEntity player) {
            String playerID = getPlayerId(player);

            if (players.contains(playerID)) {
                player.sendMessage(new SText(TextFormatting.RED + "You are already in a party."));
                return false;
            } else if (invites.contains(playerID)) {
                invites.removeIf(x -> playerID.equals(x));
                players.add(playerID);
                player.sendMessage(new SText(TextFormatting.GREEN + "You have joined the party."));

                getPlayerIds().forEach(x -> {
                    if (x != playerID) {
                        PlayerEntity p = MapManager.getServer()
                                .getPlayerList()
                                .getPlayerByUUID(UUID.fromString(x));
                        p.sendMessage(new SText(TextFormatting.GREEN + "A player has joined the party!"));
                    }
                });
                return true;
            } else {
                player.sendMessage(new SText(TextFormatting.RED + "You must be invited before joining the party."));
                return false;
            }
        }

        public void addPlayer(ServerPlayerEntity player) {
            players.add(getPlayerId(player));

            if (owner.isEmpty()) {
                owner = getPlayerId(player);
            }

        }

        public PlayerEntity getPlayer(String id) {
            return MapManager.getServer()
                .getPlayerList()
                .getPlayerByUUID(UUID.fromString(id));
        }

        public void removePlayer(ServerPlayerEntity player) {
            players.removeIf(x -> getPlayerId(player).equals(x));
        }

        public Set<String> getPlayerIds() {
            return players;
        }

        @Store
        String owner = "";

        @Store
        Set<String> players = new HashSet<>();
        @Store
        Set<String> invites = new HashSet<>();
    }

}
