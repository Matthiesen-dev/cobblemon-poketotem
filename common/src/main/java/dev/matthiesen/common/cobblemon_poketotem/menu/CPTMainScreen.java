package dev.matthiesen.common.cobblemon_poketotem.menu;

import ca.landonjw.gooeylibs2.api.UIManager;
import ca.landonjw.gooeylibs2.api.button.Button;
import ca.landonjw.gooeylibs2.api.button.GooeyButton;
import ca.landonjw.gooeylibs2.api.button.PlaceholderButton;
import ca.landonjw.gooeylibs2.api.helpers.PaginationHelper;
import ca.landonjw.gooeylibs2.api.page.GooeyPage;
import ca.landonjw.gooeylibs2.api.page.Page;
import ca.landonjw.gooeylibs2.api.template.types.ChestTemplate;
import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.CobblemonSounds;
import com.cobblemon.mod.common.api.storage.party.PartyStore;
import com.cobblemon.mod.common.pokemon.Pokemon;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import dev.matthiesen.common.cobblemon_poketotem.util.MenuUtilities;
import dev.matthiesen.common.cobblemon_poketotem.util.PokemonUtility;
import dev.matthiesen.common.cobblemon_poketotem.util.SoundsPlayer;

import java.util.ArrayList;
import java.util.List;

public class CPTMainScreen {
    public final ServerPlayer player;
    public final PartyStore storage;

    public CPTMainScreen(ServerPlayer player) {
        this.player = player;
        storage = Cobblemon.INSTANCE.getStorage().getParty(player);
    }

    public Component getDisplayTitle() {
        return Component.literal(player.getName().getString() + "'s Party");
    }

    public ItemStack convertPokemonToItem(Pokemon pokemon, RegistryAccess registryAccess, Integer slot) {
        return PokemonUtility.createCustomPokeTotem(pokemon, registryAccess, slot);
    }

    public List<Button> getPokeButtons() {
        List<Button> buttonList = new ArrayList<>();

        for (int j = 0; j <= 5; j++) {
            Pokemon pokemon = storage.get(j);

            ItemStack finalItem;

            if (pokemon != null) {
                RegistryAccess registryAccess = player.level().registryAccess();
                finalItem = convertPokemonToItem(pokemon, registryAccess, j);
            } else {
                finalItem = MenuUtilities.getEmptyItem();
            }

            Button button = GooeyButton.builder()
                    .display(finalItem)
                    .onClick((action) -> {
                        ServerPlayer sender = action.getPlayer();
                        new SoundsPlayer(CobblemonSounds.POKE_BALL_HIT)
                                .playIfMatchesRequirements(sender, finalItem.is(MenuUtilities.POKE_BALL));

                        if (!finalItem.is(MenuUtilities.POKE_BALL)) {
                            new SoundsPlayer(CobblemonSounds.POKEDEX_CLICK).play(sender);
                            PokemonUtility.givePlayerPokemonItem(sender, finalItem, storage, pokemon);
                            UIManager.closeUI(player);
                            UIManager.openUIForcefully(player, getPage());
                        }
                    })
                    .build();

            buttonList.add(button);
        }

        return buttonList;
    }

    public Page getOpenPCScreen(ServerPlayer player, PartyStore storage) {
        return new CPTPCScreen(player, storage).getPage();
    }

    public Page getPage() {
        PlaceholderButton placeholder = new PlaceholderButton();
        List<Button> buttons = getPokeButtons();

        Button frame = GooeyButton.builder()
                .display(MenuUtilities.getFrameItem())
                .build();

        Button openPC = GooeyButton.builder()
                .display(MenuUtilities.getSeparatorItem())
                .onClick((action) -> {
                    ServerPlayer sender = action.getPlayer();
                    new SoundsPlayer(CobblemonSounds.PC_ON).play(sender);
                    UIManager.openUIForcefully(player, getOpenPCScreen(player, storage));
                })
                .build();

        ChestTemplate template = ChestTemplate.builder(3)
                .row(0, frame)
                .set(1, 0, frame)
                .set(1, 1, placeholder)
                .set(1, 2, placeholder)
                .set(1, 3, placeholder)
                .set(1, 4, openPC)
                .set(1, 5, placeholder)
                .set(1, 6, placeholder)
                .set(1, 7, placeholder)
                .set(1, 8, frame)
                .row(2, frame)
                .build();

        GooeyPage page = PaginationHelper.createPagesFromPlaceholders(template, buttons, null);

        page.setTitle(getDisplayTitle());

        return page;
    }
}
