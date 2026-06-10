package dev.matthiesen.common.cobblemon_poketotem;

import dev.matthiesen.common.cobblemon_poketotem.config.PokeTotemConfigManager;
import dev.matthiesen.common.cobblemon_poketotem.registry.CommandRegistry;
import dev.matthiesen.common.cobblemon_poketotem.registry.PermissionRegistry;
import dev.matthiesen.common.matthiesen_lib_api.MatthiesenLibApi;
import dev.matthiesen.common.matthiesen_lib_api.permission.Permission;
import net.minecraft.commands.CommandSourceStack;
import dev.matthiesen.common.cobblemon_poketotem.config.CobblemonPokeTotemConfig;
import dev.matthiesen.common.cobblemon_poketotem.molang.PlayerFunctionsExtension;

public final class CobblemonPokeTotem {
    private static final PokeTotemConfigManager<CobblemonPokeTotemConfig> CONFIG_MANAGER =
            new PokeTotemConfigManager<>(CobblemonPokeTotemConfig.class, "config");

    public static void initialize() {
        reloadConfig();
        PermissionRegistry.init();
        CommandRegistry.init();

        // Extend Cobblemon's Molang functions
        PlayerFunctionsExtension.register();

        // Event Listener registration
        MatthiesenLibApi.registerReloadRunnable(Constants.MOD_ID, CobblemonPokeTotem::reloadConfig);

        Constants.createInfoLog("Initialized");
    }

    public static CobblemonPokeTotemConfig getConfig() {
        return CONFIG_MANAGER.getConfig();
    }

    public static void reloadConfig() {
        CONFIG_MANAGER.loadConfig();
    }

    public static PermissionRegistry.Permissions getPermissions() {
        return PermissionRegistry.getPermissions();
    }

    public static boolean checkPermission(CommandSourceStack source, Permission permission) {
        return PermissionRegistry.checkPermission(source, permission);
    }
}
