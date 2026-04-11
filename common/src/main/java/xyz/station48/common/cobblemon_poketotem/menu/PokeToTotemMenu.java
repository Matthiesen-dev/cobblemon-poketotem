package xyz.station48.common.cobblemon_poketotem.menu;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

public class PokeToTotemMenu extends CPTMenuBase {

    public PokeToTotemMenu(ServerPlayer playerToView) {
        super(playerToView);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.literal(playerToView.getName().getString() + " Party");
    }
}
