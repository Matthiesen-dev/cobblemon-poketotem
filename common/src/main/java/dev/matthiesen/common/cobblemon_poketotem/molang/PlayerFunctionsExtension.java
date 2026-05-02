package dev.matthiesen.common.cobblemon_poketotem.molang;

import com.bedrockk.molang.runtime.MoParams;
import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.molang.MoLangFunctions;
import com.cobblemon.mod.common.api.storage.party.PartyStore;
import com.cobblemon.mod.common.pokemon.Pokemon;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import dev.matthiesen.common.cobblemon_poketotem.Constants;
import dev.matthiesen.common.cobblemon_poketotem.util.PokemonUtility;

import java.util.HashMap;
import java.util.function.Function;

public class PlayerFunctionsExtension {
    public static void register() {
        Constants.createInfoLog("Registering Cobblemon Molang Player function extensions");

        MoLangFunctions.INSTANCE.getPlayerFunctions().add(player -> {
            HashMap<String, Function<MoParams, Object>> map = new HashMap<>();

            // q.player.poketototem(<slot_number>)
            map.put("poketototem", params -> {
                int slot = params.getInt(0);

                // Get pokemon from player's Party
                PartyStore storage = Cobblemon.INSTANCE.getStorage().getParty((ServerPlayer) player);
                Pokemon pokemon = storage.get(slot);

                // Convert to an Item
                RegistryAccess registryAccess = player.level().registryAccess();

                // Give player the item if pokemon is not null
                if (pokemon != null) {
                    ItemStack pokemonItem = PokemonUtility.createCustomPokeTotem(pokemon, registryAccess, slot);
                    PokemonUtility.givePlayerPokemonItem((ServerPlayer) player, pokemonItem, storage, pokemon);
                    return 0;
                }
                return 0;
            });

            return map;
        });
    }
}
