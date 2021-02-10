package com.robertx22.mine_and_slash.commands.party;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.robertx22.mine_and_slash.uncommon.capability.server_wide.TeamCap;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.List;
import java.util.stream.Collectors;

import static net.minecraft.command.Commands.literal;

public class PartyCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(
            literal("slash")
                .then(
                    literal("party").requires(e -> e.hasPermissionLevel(0))
                        .then(literal("list").executes(c -> {

                            try {
                                ServerPlayerEntity player = c.getSource()
                                    .asPlayer();
                                if (TeamCap.getCapability()
                                    .isPlayerInATeam(player)) {
                                    ITextComponent text = new SText(TextFormatting.GREEN + "Party List: ");
                                    List<ITextComponent> list = TeamCap.getCapability()
                                        .getPlayersInTeam(player)
                                        .stream()
                                        .map(x -> x.getDisplayName())
                                        .collect(Collectors.toList());
                                    list.forEach(x -> x.appendText(" "));
                                    list.forEach(x -> text.appendSibling(x));

                                    player
                                        .sendMessage(text);
                                } else {
                                    player
                                        .sendMessage(new SText(TextFormatting.GREEN + "You aren't in a party."));
                                }
                            } catch (CommandSyntaxException e) {
                                e.printStackTrace();
                            }
                            return 0;
                        }))
                        .then(literal("create").executes(c -> {

                            ServerPlayerEntity player = c.getSource()
                                .asPlayer();
                            TeamCap.getCapability()
                                .createTeam(player);
                            player.sendMessage(new SText(TextFormatting.GREEN + "Party created. You may now invite players to your party."));
                            return 0;
                        }))
                        .then(literal("leave").executes(c -> {

                            ServerPlayerEntity player = c.getSource()
                                .asPlayer();
                            TeamCap.getCapability()
                                .leaveTeam(player);
                            player.sendMessage(new SText(TextFormatting.GREEN + "You are no longer in a party."));
                            return 0;
                        }))
                        .then(literal("join").then(Commands.argument("target", EntityArgument.player())
                            .executes(c -> {
                                ServerPlayerEntity player = c.getSource()
                                    .asPlayer();
                                ServerPlayerEntity player2 = EntityArgument.getPlayer(c, "target");

                                TeamCap.ITeamData cap = TeamCap.getCapability();
                                cap.joinTeam(player, cap.getTeamId(player2));

                                player2.sendMessage(new SText(TextFormatting.GREEN + "A member has joined the party."));
                                player.sendMessage(new SText(TextFormatting.GREEN + "You have joined the party."));

                                return 0;
                            })))
                        .then(literal("invite").then(Commands.argument("target", EntityArgument.player())
                            .executes(c -> {
                                ServerPlayerEntity player = c.getSource()
                                    .asPlayer();
                                ServerPlayerEntity player2 = EntityArgument.getPlayer(c, "target");
                                TeamCap.ITeamData cap = TeamCap.getCapability();
                                cap.invite(player2, cap.getTeamId(player));

                                player.sendMessage(new SText(TextFormatting.GREEN + "Invitation sent."));
                                player2.sendMessage(new SText(TextFormatting.GREEN + "You have been invited to a party!"));
                                player2.sendMessage(new SText(TextFormatting.GREEN + "Type" +
                                        " '/slash party join <player>'" + TextFormatting.GREEN + " to accept."));

                                return 0;
                            })))
                )
        );

    }

}
