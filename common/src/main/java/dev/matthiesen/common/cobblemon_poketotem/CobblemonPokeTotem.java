package dev.matthiesen.common.cobblemon_poketotem;

import com.mojang.brigadier.CommandDispatcher;
import dev.matthiesen.common.cobblemon_poketotem.commands.CommandRegistry;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.MinecraftServer;
import dev.matthiesen.common.cobblemon_poketotem.config.CobblemonPokeTotemConfig;
import dev.matthiesen.common.cobblemon_poketotem.config.ConfigManager;
import dev.matthiesen.common.cobblemon_poketotem.molang.PlayerFunctionsExtension;
import dev.matthiesen.common.cobblemon_poketotem.permissions.CobblemonPokeTotemPermissions;

public class CobblemonPokeTotem {
    public static CobblemonPokeTotemPermissions permissions;
    public static CobblemonPokeTotemConfig config;
    public static MinecraftServer currentServer;

    public static void initialize() {
        Constants.createInfoLog("Initialized");
        config = new ConfigManager().loadConfig();
        permissions = new CobblemonPokeTotemPermissions();

        // Extend Cobblemon's Molang functions
        PlayerFunctionsExtension.register();
    }

    public static void onStartup(MinecraftServer server) {
        Constants.createInfoLog("Server starting, Setting up");
        currentServer = server;
    }

    public static void onShutdown() {
        Constants.createInfoLog("Server stopping, shutting down");
        new ConfigManager().saveConfig();
    }

    public static void registerCommands(CommandDispatcher<CommandSourceStack> dispatcher) {
        Constants.createInfoLog("Registering Commands");
        CommandRegistry.init(dispatcher);
    }
}
