package xyz.station48.common.cobblemon_poketotem.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.server.level.ServerPlayer;
import xyz.station48.common.cobblemon_poketotem.CobblemonPokeTotem;
import xyz.station48.common.cobblemon_poketotem.permissions.CobblemonPokeTotemPermissions;

public class TotemToPokeServer {
    public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands
                    .literal("totemtopoke-server")
                    .requires(
                            src -> CobblemonPokeTotemPermissions.checkPermission(
                                    src, CobblemonPokeTotem.permissions.TOTEMTOPOKE_SERVER_PERMISSION
                            ))
                    .then(
                            Commands
                                    .argument("player", EntityArgument.player())
                                    .executes(this::runner)
                    )
        );
    }

    private int runner(CommandContext<CommandSourceStack> ctx) {
        ServerPlayer target;
        try {
            target = EntityArgument.getPlayer(ctx, "player");
        } catch (CommandSyntaxException e) {
            return 0;
        }
        new TotemToPoke().shared(target);
        return 1;
    }
}