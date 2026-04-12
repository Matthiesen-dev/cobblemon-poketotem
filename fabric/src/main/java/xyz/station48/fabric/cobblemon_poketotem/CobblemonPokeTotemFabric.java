package xyz.station48.fabric.cobblemon_poketotem;

import xyz.station48.common.cobblemon_poketotem.CobblemonPokeTotem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import xyz.station48.common.cobblemon_poketotem.Constants;

public class CobblemonPokeTotemFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        Constants.createInfoLog("Loading for Fabric Mod Loader");
        CobblemonPokeTotem.initialize();
        CommandRegistrationCallback.EVENT.register(
                (
                        dispatcher,
                        registryAccess,
                        environment
                ) ->
                CobblemonPokeTotem.registerCommands(dispatcher)
        );
        ServerLifecycleEvents.SERVER_STOPPING.register(server -> CobblemonPokeTotem.onShutdown());
    }

}
