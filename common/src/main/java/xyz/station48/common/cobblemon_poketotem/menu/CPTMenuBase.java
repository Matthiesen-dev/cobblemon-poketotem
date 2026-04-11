package xyz.station48.common.cobblemon_poketotem.menu;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.storage.party.PartyStore;
import com.cobblemon.mod.common.pokemon.Pokemon;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import org.jetbrains.annotations.NotNull;
import xyz.station48.common.cobblemon_poketotem.util.MenuUtilities;
import xyz.station48.common.cobblemon_poketotem.util.PokemonUtility;

public class CPTMenuBase implements MenuProvider {
    public final Container container = new SimpleContainer(9 * 3);
    public final ServerPlayer playerToView;

    public CPTMenuBase(ServerPlayer playerToView) {
        this.playerToView = playerToView;
        setupContainer();
    }

    public void fillWithPanes() {
        for (int i = 0; i < container.getContainerSize(); i++) {
            container.setItem(i, MenuUtilities.getFrameItem());
        }
    }

    public void setSeparator() {
        container.setItem(13, MenuUtilities.getSeparatorItem());
    }

    public void setupContainer() {
        fillWithPanes();
        setSeparator();
    }

    public void refresh(ServerPlayer player, PartyStore storage) {
        for (int j = 0; j <= 5; j++) {
            Pokemon pokemon = storage.get(j);
            int slotIndex = 10 + j + (j >= 3 ? 1 : 0);

            if (pokemon != null) {
                RegistryAccess registryAccess = player.level().registryAccess();
                ItemStack finalItem = PokemonUtility.createCustomPokeTotem(pokemon, registryAccess, j);
                container.setItem(slotIndex, finalItem);
            } else {
                ItemStack emptyItem = MenuUtilities.getEmptyItem();
                container.setItem(slotIndex, emptyItem);
            }
        }
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.literal("Base Menu");
    }

    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        ServerPlayer serverPlayer = (ServerPlayer) player;
        if (this.playerToView != null) {
            serverPlayer = this.playerToView;
        }

        PartyStore storage = Cobblemon.INSTANCE.getStorage().getParty(serverPlayer);
        refresh(serverPlayer, storage);

        final ServerPlayer finalServerPlayer = serverPlayer;

        return new ChestMenu(MenuType.GENERIC_9x3, i, inventory, container, 3) {
            @Override
            public void clicked(int slotIndex, int buttonNo, ClickType clickType, Player player) {
                ItemStack clickedItem = container.getItem(slotIndex);

                // Skip if the item is a placeholder or empty
                if (clickedItem.isEmpty() || MenuUtilities.isMenuItem(clickedItem)) {
                    return;
                }

                // Read custom data from item to get the Pokémon slot index
                CustomData customData = clickedItem.get(DataComponents.CUSTOM_DATA);
                int slot = customData != null ? customData.copyTag().getInt("slot") : -1;

                if (slot == -1) {
                    return;
                }

                PartyStore storage = Cobblemon.INSTANCE.getStorage().getParty(finalServerPlayer);
                Pokemon pokemon = storage.get(slot);

                if (pokemon != null) {
                    // Clone the Clicked Item
                    ItemStack clonedItem = clickedItem.copy();

                    // Add to inventory or drop if full and clear from user storage
                    PokemonUtility.givePlayerPokemonItem(player, clonedItem, storage, pokemon);

                    // Clear the item from the container
                    container.setItem(slotIndex, ItemStack.EMPTY);
                }

                // Handle default click behavior
                super.clicked(slotIndex, buttonNo, clickType, player);
                refresh(finalServerPlayer, storage);
            }

            @Override
            public @NotNull ItemStack quickMoveStack(Player player, int i) {
                return ItemStack.EMPTY;
            }

            @Override
            public boolean stillValid(Player player) {
                return true;
            }
        };
    }
}
