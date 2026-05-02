package dev.matthiesen.common.cobblemon_poketotem.menu;

import ca.landonjw.gooeylibs2.api.UIManager;
import ca.landonjw.gooeylibs2.api.button.Button;
import ca.landonjw.gooeylibs2.api.button.GooeyButton;
import ca.landonjw.gooeylibs2.api.button.PlaceholderButton;
import ca.landonjw.gooeylibs2.api.button.linked.LinkType;
import ca.landonjw.gooeylibs2.api.button.linked.LinkedPageButton;
import ca.landonjw.gooeylibs2.api.helpers.PaginationHelper;
import ca.landonjw.gooeylibs2.api.page.LinkedPage;
import ca.landonjw.gooeylibs2.api.page.Page;
import ca.landonjw.gooeylibs2.api.template.slot.TemplateSlotDelegate;
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

public class CPTPCScreen {
    public final ServerPlayer player;
    public final PartyStore storage;

    public CPTPCScreen(ServerPlayer player, PartyStore storage) {
        this.player = player;
        this.storage = storage;
    }

    public Component getDisplayTitle() {
        return Component.literal(player.getName().getString() + "'s PC");
    }

    public ItemStack convertPokemonToItem(Pokemon pokemon, RegistryAccess registryAccess, Integer slot) {
        return PokemonUtility.createCustomPokeTotem(pokemon, registryAccess, slot);
    }

    public List<Button> getPokeButtons(List<Pokemon> pokemons) {
        List<Button> buttonList = new ArrayList<>();

        for (Pokemon pokemon : pokemons) {
            RegistryAccess registryAccess = player.level().registryAccess();
            ItemStack finalItem = convertPokemonToItem(pokemon, registryAccess, 0);

            Button button = GooeyButton.builder()
                    .display(finalItem)
                    .onClick((action) -> {
                        if (!finalItem.is(MenuUtilities.POKE_BALL)) {
                            new SoundsPlayer(CobblemonSounds.POKEDEX_CLICK).play(player);
                            PokemonUtility.givePlayerPokemonItem(player, finalItem, storage, pokemon);
                            UIManager.closeUI(player);
                            UIManager.openUIForcefully(player, getPage());
                        }
                    })
                    .build();

            buttonList.add(button);
        }

        return buttonList;
    }

    public Page getBackToPartyPage() {
        return new CPTMainScreen(player).getPage();
    }

    public Page getPage() {
        PlaceholderButton placeholder = new PlaceholderButton();

        Button frame = GooeyButton.builder()
                .display(MenuUtilities.getFrameItem())
                .build();

        List<Pokemon> pokemons = new ArrayList<>();
        var pc = Cobblemon.INSTANCE.getStorage().getPC(this.player);
        for (Pokemon pokemon : pc) {
            if (pokemon != null) {
                pokemons.add(pokemon);
            }
        }

        List<Button> pokemonButtons = getPokeButtons(pokemons);

        LinkedPageButton previous = LinkedPageButton.builder()
                .display(MenuUtilities.getNavItem("Previous"))
                .linkType(LinkType.Previous)
                .onClick((action) -> new SoundsPlayer(CobblemonSounds.PC_CLICK).play(player))
                .build();

        LinkedPageButton next = LinkedPageButton.builder()
                .display(MenuUtilities.getNavItem("Next"))
                .linkType(LinkType.Next)
                .onClick((action) -> new SoundsPlayer(CobblemonSounds.PC_CLICK).play(player))
                .build();

        Button backToParty = GooeyButton.builder()
                .display(MenuUtilities.getBackToPartyItem())
                .onClick((action) -> {
                    new SoundsPlayer(CobblemonSounds.PC_OFF).play(player);
                    UIManager.openUIForcefully(player, getBackToPartyPage());
                })
                .build();

        ChestTemplate template = ChestTemplate.builder(6)
                .rectangle(0, 0, 5, 9, placeholder)
                .set(53, next)
                .set(47, backToParty)
                .set(49, getInfoButton(1, 1))
                .set(45, previous)
                .fill(frame)
                .build();

        LinkedPage page = PaginationHelper.createPagesFromPlaceholders(template, pokemonButtons, null);
        setPageTitleRecursive(page);

        return page;
    }

    private Button getInfoButton(int currentPage, int pageLength) {
        return GooeyButton.builder()
                .display(MenuUtilities.getPageItem(currentPage, pageLength))
                .build();
    }

    private TemplateSlotDelegate getInfoButtonTemplate(int currentPage, int pageLength) {
        Button infoButton = getInfoButton(pageLength, currentPage);
        return new TemplateSlotDelegate(infoButton, 49);
    }

    private void setPageTitleInternal(LinkedPage page, int pageLength) {
        int currentPage = page.getCurrentPage();
        page.setTitle(getDisplayTitle());
        page.getTemplate().setSlot(49, getInfoButtonTemplate(pageLength, currentPage));
    }

    private void setPageTitleRecursive(LinkedPage page) {
        int pageLength = page.getTotalPages();
        setPageTitleInternal(page, pageLength);
        LinkedPage next = page.getNext();
        if (next != null) {
            setPageTitleInternal(next, pageLength);
            setPageTitleRecursive(next);
        }
    }
}
