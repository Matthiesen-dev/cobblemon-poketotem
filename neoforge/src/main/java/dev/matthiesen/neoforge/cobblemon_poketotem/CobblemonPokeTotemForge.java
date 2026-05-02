package dev.matthiesen.neoforge.cobblemon_poketotem;

import net.minecraft.server.MinecraftServer;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;
import dev.matthiesen.common.cobblemon_poketotem.CobblemonPokeTotem;
import dev.matthiesen.common.cobblemon_poketotem.Constants;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@Mod(Constants.ModId)
public class CobblemonPokeTotemForge {
    public CobblemonPokeTotemForge() {
        Constants.createInfoLog("Loading for NeoForge Mod Loader");
        CobblemonPokeTotem.initialize();
        NeoForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        MinecraftServer server = event.getServer();
        CobblemonPokeTotem.onStartup(server);
    }

    @SubscribeEvent
    public void onCommandRegistration(RegisterCommandsEvent event) {
        CobblemonPokeTotem.registerCommands(event.getDispatcher());
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onServerStopping(ServerStoppingEvent event) {
        CobblemonPokeTotem.onShutdown();
    }
}
