package dev.matthiesen.common.cobblemon_poketotem.menu;

import ca.landonjw.gooeylibs2.api.page.Page;
import com.cobblemon.mod.common.api.storage.party.PartyStore;
import com.cobblemon.mod.common.pokemon.Pokemon;
import net.minecraft.ChatFormatting;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import dev.matthiesen.common.cobblemon_poketotem.util.PokemonUtility;

public class CPTPCCloneScreen extends CPTPCScreen {
    public CPTPCCloneScreen(ServerPlayer player, PartyStore storage) {
        super(player, storage);
    }

    @Override
    public Component getDisplayTitle() {
        return Component.literal(player.getName().getString() + "'s PC ").append(
                Component.literal("(Clone)").withStyle(
                        style -> style.withColor(ChatFormatting.DARK_RED).withBold(true)
                )
        );
    }

    @Override
    public Page getBackToPartyPage() {
        return new CPTMainCloneScreen(player).getPage();
    }

    @Override
    public ItemStack convertPokemonToItem(Pokemon pokemon, RegistryAccess registryAccess, Integer slot) {
        return PokemonUtility.createCustomPokeTotemClone(pokemon, registryAccess, slot);
    }
}
