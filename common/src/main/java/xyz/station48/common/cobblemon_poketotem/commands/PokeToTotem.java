package xyz.station48.common.cobblemon_poketotem.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import xyz.station48.common.cobblemon_poketotem.CobblemonPokeTotem;
import xyz.station48.common.cobblemon_poketotem.menu.PokeToTotemCloneMenu;
import xyz.station48.common.cobblemon_poketotem.menu.PokeToTotemMenu;
import xyz.station48.common.cobblemon_poketotem.permissions.PokemonToTotemPermissions;

public class PokeToTotem {
    public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("poketototem")
                        .requires(src -> PokemonToTotemPermissions.checkPermission(
                                src,
                                CobblemonPokeTotem.permissions.POKETOTOTEM_PERMISSION
                        ))
                        .executes(this::self)
                        .then(
                                Commands.literal("clone")
                                        .executes(this::clone)
                        )
        );
    }

    private int self(CommandContext<CommandSourceStack> ctx) {
        if (ctx.getSource().getPlayer() != null) {
            ServerPlayer player = ctx.getSource().getPlayer();
            player.openMenu(new PokeToTotemMenu(player));
        }
        return 1;
    }

    private int clone(CommandContext<CommandSourceStack> ctx) {
        if (ctx.getSource().getPlayer() != null) {
            ServerPlayer player = ctx.getSource().getPlayer();
            player.openMenu(new PokeToTotemCloneMenu(player));
        }
        return 1;
    }
}