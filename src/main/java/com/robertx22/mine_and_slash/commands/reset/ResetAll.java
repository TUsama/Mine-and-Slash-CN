package com.robertx22.mine_and_slash.commands.reset;

import com.mojang.brigadier.CommandDispatcher;
import com.robertx22.mine_and_slash.commands.CommandRefs;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerTalentsCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;

import javax.annotation.Nullable;

import static net.minecraft.command.Commands.literal;

public class ResetAll {
    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(
            literal(CommandRefs.ID)
                .then(literal("reset").requires(e -> e.hasPermissionLevel(2))
                    .then(literal("all")
                        .then(Commands.argument("target", EntityArgument.entity())
                            .executes(
                                ctx -> run(EntityArgument.getPlayer(ctx, "target")))))));
    }

    private static int run(@Nullable PlayerEntity en) {

        try {

            Load.statPoints(en)
                    .resetStats();

            Load.spells(en)
                .reset();

            PlayerTalentsCap.IPlayerTalentsData data = Load.talents(en);
            data.reset();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }
}
