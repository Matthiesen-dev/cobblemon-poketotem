package xyz.station48.common.cobblemon_poketotem.commands;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.storage.party.PartyStore;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import xyz.station48.common.cobblemon_poketotem.CobblemonPokeTotem;
import xyz.station48.common.cobblemon_poketotem.permissions.PokemonToTotemPermissions;
import xyz.station48.common.cobblemon_poketotem.util.PokemonUtility;

public class PokeToTotemServer {
    public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("poketototem-server")
                        .requires(src -> PokemonToTotemPermissions.checkPermission(
                                src,
                                CobblemonPokeTotem.permissions.POKETOTOTEM_SERVER_PERMISSION
                        ))
                        .then(
                                Commands.argument("player", EntityArgument.player())
                                        .then(
                                                Commands.argument("slot", IntegerArgumentType.integer(0, 5))
                                                        .executes(this::self)
                                        )
                        )
        );
    }

    private int self(CommandContext<CommandSourceStack> ctx) {
        ServerPlayer targetPlayer;
        int slot = IntegerArgumentType.getInteger(ctx, "slot");

        try {
            targetPlayer = EntityArgument.getPlayer(ctx, "player");
        } catch (CommandSyntaxException e) {
            throw new RuntimeException(e);
        }

        PartyStore storage = Cobblemon.INSTANCE.getStorage().getParty(targetPlayer);
        Pokemon pokemon = storage.get(slot);

        if (pokemon != null) {
            RegistryAccess registryAccess = targetPlayer.level().registryAccess();

            ItemStack pokemonItem = PokemonUtility.createCustomPokeTotem(pokemon, registryAccess, slot);

            PokemonUtility.givePlayerPokemonItem(targetPlayer, pokemonItem, storage, pokemon);
            return 1;
        }
        return 0;
    }
}