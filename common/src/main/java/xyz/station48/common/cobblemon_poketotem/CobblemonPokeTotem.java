package xyz.station48.common.cobblemon_poketotem;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import xyz.station48.common.cobblemon_poketotem.commands.*;
import xyz.station48.common.cobblemon_poketotem.config.CobblemonPokeTotemConfig;
import xyz.station48.common.cobblemon_poketotem.config.ConfigManager;
import xyz.station48.common.cobblemon_poketotem.molang.PlayerFunctionsExtension;
import xyz.station48.common.cobblemon_poketotem.permissions.CobblemonPokeTotemPermissions;

public class CobblemonPokeTotem {
    public static CobblemonPokeTotemPermissions permissions;
    public static CobblemonPokeTotemConfig config;

    public static void initialize() {
        Constants.createInfoLog("Initialized");
        config = new ConfigManager().loadConfig();
        permissions = new CobblemonPokeTotemPermissions();

        // Extend Cobblemon's Molang functions
        PlayerFunctionsExtension.register();
    }

    public static void onShutdown() {
        Constants.createInfoLog("Shutting Down");
    }

    public static void registerCommands(CommandDispatcher<CommandSourceStack> dispatcher) {
        Constants.createInfoLog("Registering Commands");
        CommandRegistry.init(dispatcher);
    }
}
