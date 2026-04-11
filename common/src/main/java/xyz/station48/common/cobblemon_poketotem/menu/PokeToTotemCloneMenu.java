package xyz.station48.common.cobblemon_poketotem.menu;

import com.cobblemon.mod.common.api.storage.party.PartyStore;
import com.cobblemon.mod.common.pokemon.Pokemon;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import xyz.station48.common.cobblemon_poketotem.util.MenuUtilities;
import xyz.station48.common.cobblemon_poketotem.util.PokemonUtility;

public class PokeToTotemCloneMenu extends CPTMenuBase {

    public PokeToTotemCloneMenu(ServerPlayer playerToView) {
        super(playerToView);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.literal(playerToView.getName().getString() + " Party (Clone Mode)");
    }

    @Override
    public void refresh(ServerPlayer player, PartyStore storage) {
        for (int j = 0; j <= 5; j++) {
            Pokemon pokemon = storage.get(j);
            int slotIndex = 10 + j + (j >= 3 ? 1 : 0);

            if (pokemon != null) {
                RegistryAccess registryAccess = player.level().registryAccess();
                ItemStack finalItem = PokemonUtility.createCustomPokeTotemClone(pokemon, registryAccess, j);
                container.setItem(slotIndex, finalItem);
            } else {
                ItemStack emptyItem = MenuUtilities.getEmptyItem();
                container.setItem(slotIndex, emptyItem);
            }
        }
    }
}
