package xyz.station48.common.cobblemon_poketotem.commands;

import ca.landonjw.gooeylibs2.api.UIManager;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import xyz.station48.common.cobblemon_poketotem.CobblemonPokeTotem;
import xyz.station48.common.cobblemon_poketotem.interfaces.ICommand;
import xyz.station48.common.cobblemon_poketotem.menu.CPTMainCloneScreen;
import xyz.station48.common.cobblemon_poketotem.menu.CPTMainScreen;
import xyz.station48.common.cobblemon_poketotem.permissions.CobblemonPokeTotemPermissions;

public class PokeToTotem implements ICommand {
    public PokeToTotem() {}

    public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("poketototem")
                        .requires(src -> CobblemonPokeTotemPermissions.checkPermission(
                                src,
                                CobblemonPokeTotem.permissions.POKETOTOTEM_PERMISSION
                        ))
                        .executes(this::normal)
                        .then(
                                Commands.literal("clone")
                                        .executes(this::clone)
                        )
        );
    }

    private int normal(CommandContext<CommandSourceStack> ctx) {
        if (ctx.getSource().getPlayer() != null) {
            ServerPlayer player = ctx.getSource().getPlayer();
            UIManager.openUIForcefully(player, new CPTMainScreen(player).getPage());
        }
        return 1;
    }

    private int clone(CommandContext<CommandSourceStack> ctx) {
        if (ctx.getSource().getPlayer() != null) {
            ServerPlayer player = ctx.getSource().getPlayer();
            UIManager.openUIForcefully(player, new CPTMainCloneScreen(player).getPage());
        }
        return 1;
    }
}