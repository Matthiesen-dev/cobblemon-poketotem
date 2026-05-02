package dev.matthiesen.fabric.cobblemon_poketotem;

import net.minecraft.server.MinecraftServer;
import dev.matthiesen.common.cobblemon_poketotem.CobblemonPokeTotem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import dev.matthiesen.common.cobblemon_poketotem.Constants;

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
        ServerLifecycleEvents.SERVER_STARTING.register(server -> {
            MinecraftServer runningServer = server.createCommandSourceStack().getServer();
            CobblemonPokeTotem.onStartup(runningServer);
        });
        ServerLifecycleEvents.SERVER_STOPPING.register(server -> CobblemonPokeTotem.onShutdown());
    }

}
