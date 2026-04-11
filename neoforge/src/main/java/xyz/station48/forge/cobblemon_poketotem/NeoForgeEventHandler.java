package xyz.station48.forge.cobblemon_poketotem;

import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStoppedEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;
import xyz.station48.common.cobblemon_poketotem.CobblemonPokeTotem;
import xyz.station48.common.cobblemon_poketotem.Constants;

public class NeoForgeEventHandler {
    public void register() {
        NeoForge.EVENT_BUS.register(this);
        Constants.createInfoLog("Registered NeoForge Event Handler");
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onServerStopped(ServerStoppedEvent event) {
        Constants.createErrorLog("Server Stopped");
        CobblemonPokeTotem.onShutdown();
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onServerStopping(ServerStoppingEvent event) {
        Constants.createErrorLog("Server stopping, shutting down CobblemonPokeTotem");
        CobblemonPokeTotem.onShutdown();
    }
}
