package xyz.station48.common.cobblemon_poketotem.menu;

import ca.landonjw.gooeylibs2.api.page.Page;
import com.cobblemon.mod.common.api.storage.party.PartyStore;
import com.cobblemon.mod.common.pokemon.Pokemon;
import net.minecraft.ChatFormatting;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import xyz.station48.common.cobblemon_poketotem.util.PokemonUtility;

public class CPTMainCloneScreen extends CPTMainScreen {
    public CPTMainCloneScreen(ServerPlayer player) {
        super(player);
    }

    @Override
    public Component getDisplayTitle() {
        return Component.literal(this.player.getName().getString() + "'s Party ").append(
                Component.literal("(Clone)").withStyle(
                        style -> style.withColor(ChatFormatting.DARK_RED).withBold(true)
                )
        );
    }

    @Override
    public ItemStack convertPokemonToItem(Pokemon pokemon, RegistryAccess registryAccess, Integer slot) {
        return PokemonUtility.createCustomPokeTotemClone(pokemon, registryAccess, slot);
    }

    @Override
    public Page getOpenPCScreen(ServerPlayer player, PartyStore storage) {
        return new CPTPCCloneScreen(player, storage).getPage();
    }
}
