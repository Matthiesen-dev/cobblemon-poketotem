package dev.matthiesen.common.cobblemon_poketotem.registry.command;

import ca.landonjw.gooeylibs2.api.UIManager;
import ca.landonjw.gooeylibs2.api.page.Page;
import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.storage.party.PartyStore;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.matthiesen.common.cobblemon_poketotem.CobblemonPokeTotem;
import dev.matthiesen.common.cobblemon_poketotem.menu.CPTMainCloneScreen;
import dev.matthiesen.common.cobblemon_poketotem.menu.CPTMainScreen;
import dev.matthiesen.common.cobblemon_poketotem.util.PokemonUtility;
import dev.matthiesen.common.matthiesen_lib_api.command.AbstractCommand;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

import java.util.function.Function;

/**
 * Command Structure:
 * - /poketototem
 * - /poketototem clone
 * - /poketototem server [player] [slot 0-5]
 */
public final class PokeToTotem extends AbstractCommand {
    public static final PokeToTotem CMD = new PokeToTotem();

    @Override
    public void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext registry, Commands.CommandSelection context) {
        var permissions = CobblemonPokeTotem.getPermissions();
        dispatcher.register(
                Commands.literal("poketototem")
                        .requires(src -> CobblemonPokeTotem.checkPermission(
                                src, permissions.POKETOTOTEM_PERMISSION
                        ))
                        .executes(this::action)
                        .then(
                                Commands.literal("clone")
                                        .executes(this::clone)
                        )
                        .then(
                                Commands.literal("server")
                                        .requires(src -> CobblemonPokeTotem.checkPermission(
                                                src, permissions.POKETOTOTEM_SERVER_PERMISSION
                                        ))
                                        .then(
                                                Commands.argument("player", EntityArgument.player())
                                                        .then(
                                                                Commands.argument("slot", IntegerArgumentType.integer(0, 5))
                                                                        .executes(this::server)
                                                        )
                                        )
                        )
        );
    }

    @Override
    public int action(CommandContext<CommandSourceStack> context) {
        runPlayer(context, player -> new CPTMainScreen(player).getPage());
        return 1;
    }

    public int clone(CommandContext<CommandSourceStack> context) {
        runPlayer(context, (player) -> new CPTMainCloneScreen(player).getPage());
        return 1;
    }

    private void runPlayer(CommandContext<CommandSourceStack> context, Function<ServerPlayer, Page> pageFunction) {
        if (context.getSource().getPlayer() != null) {
            ServerPlayer player = context.getSource().getPlayer();
            UIManager.openUIForcefully(player, pageFunction.apply(player));
        }
    }

    public int server(CommandContext<CommandSourceStack> context) {
        ServerPlayer senderPlayer = context.getSource().getPlayer();
        if (senderPlayer == null) {
            return 0;
        }

        ServerPlayer targetPlayer;
        int slot = IntegerArgumentType.getInteger(context, "slot");

        try {
            targetPlayer = EntityArgument.getPlayer(context, "player");
        } catch (CommandSyntaxException e) {
            throw new RuntimeException(e);
        }

        ServerPlayer[] needToMessage = {
                senderPlayer,
                targetPlayer
        };

        PartyStore storage = Cobblemon.INSTANCE.getStorage().getParty(targetPlayer);
        Pokemon pokemon = storage.get(slot);

        if (pokemon != null) {
            RegistryAccess registryAccess = targetPlayer.level().registryAccess();

            ItemStack pokemonItem = PokemonUtility.createCustomPokeTotem(pokemon, registryAccess, slot);

            PokemonUtility.givePlayerPokemonItem(targetPlayer, pokemonItem, storage, pokemon);

            for (ServerPlayer tPlayer : needToMessage) {
                tPlayer.displayClientMessage(
                        Component.literal("[§c§lCobblemonPokeTotem§f] §a§lPokémon has been converted to a Totem!"),
                        false
                );
            }
        } else {
            for (ServerPlayer tPlayer : needToMessage) {
                tPlayer.displayClientMessage(
                        Component.literal("[§c§lCobblemonPokeTotem§f] §c§lFailed to convert slot: " + slot)
                        , false
                );
            }
        }
        return 1;
    }
}
