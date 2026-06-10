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
    public static int runSharedCommandWithPlayerArg(CommandContext<CommandSourceStack> context, Function<ServerPlayer, Void> sharedFn) {
        ServerPlayer target;
        try {
            target = EntityArgument.getPlayer(context, "player");
        } catch (CommandSyntaxException e) {
            Constants.createErrorLog("Failed to find target player for poketototem server command", e);
            context.getSource().sendFailure(Component.literal("Failed to find target player."));
            return 0;
        }
        sharedFn.apply(target);
        return 1;
    }

    public static int runSharedCommandSelfPlayer(CommandContext<CommandSourceStack> context, Function<ServerPlayer, Void> sharedFn) {
        ServerPlayer player;
        try {
            player = context.getSource().getPlayerOrException();
        } catch (CommandSyntaxException e) {
            Constants.createErrorLog("Failed to find executing player for poketototem command", e);
            context.getSource().sendFailure(Component.literal("Failed to find executing player."));
            return 0;
        }
        sharedFn.apply(player);
        return 1;
    }
}
