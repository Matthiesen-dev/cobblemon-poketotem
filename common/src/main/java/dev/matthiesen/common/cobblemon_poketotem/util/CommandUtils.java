package dev.matthiesen.common.cobblemon_poketotem.util;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.matthiesen.common.cobblemon_poketotem.Constants;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.function.Function;

public final class CommandUtils {

    @FunctionalInterface
    private interface PlayerSupplier {
        ServerPlayer get() throws CommandSyntaxException;
    }

    private static int runCommand(CommandContext<CommandSourceStack> context,
                                  PlayerSupplier playerSupplier,
                                  String logMessage,
                                  String userMessage,
                                  Function<ServerPlayer, Void> sharedFn) {
        ServerPlayer player;
        try {
            player = playerSupplier.get();
        } catch (CommandSyntaxException e) {
            Constants.createErrorLog(logMessage, e);
            context.getSource().sendFailure(Component.literal(userMessage));
            return 0;
        }
        sharedFn.apply(player);
        return 1;
    }

    public static int runSharedCommandWithPlayerArg(CommandContext<CommandSourceStack> context, Function<ServerPlayer, Void> sharedFn) {
        return runCommand(context,
                () -> EntityArgument.getPlayer(context, "player"),
                "Failed to find target player for poketototem server command",
                "Failed to find target player.",
                sharedFn);
    }

    public static int runSharedCommandSelfPlayer(CommandContext<CommandSourceStack> context, Function<ServerPlayer, Void> sharedFn) {
        return runCommand(context,
                context.getSource()::getPlayerOrException,
                "Failed to find executing player for poketototem command",
                "Failed to find executing player.",
                sharedFn);
    }
}
