package xyz.station48.forge.cobblemon_poketotem;

import xyz.station48.common.cobblemon_poketotem.CobblemonPokeTotem;
import xyz.station48.common.cobblemon_poketotem.Constants;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@Mod(Constants.ModId)
public class CobblemonPokeTotemForge {
    final NeoForgeEventHandler neoForgeEventHandler = new NeoForgeEventHandler();

    public CobblemonPokeTotemForge() {
        Constants.createInfoLog("Loading for NeoForge Mod Loader");
        CobblemonPokeTotem.initialize();
        NeoForge.EVENT_BUS.register(this);
        neoForgeEventHandler.register();
    }

    @SubscribeEvent
    public void onCommandRegistration(RegisterCommandsEvent event) {
        CobblemonPokeTotem.registerCommands(event.getDispatcher());
    }
}
