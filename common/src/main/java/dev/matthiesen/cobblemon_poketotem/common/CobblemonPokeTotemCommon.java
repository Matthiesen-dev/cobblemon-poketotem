package dev.matthiesen.cobblemon_poketotem.common;

import dev.matthiesen.cobblemon_poketotem.common.command.PokeToTotem;
import dev.matthiesen.cobblemon_poketotem.common.command.TotemToPoke;
import dev.matthiesen.cobblemon_poketotem.common.config.MainConfig;
import dev.matthiesen.cobblemon_poketotem.common.molang.PlayerFunctionsExtension;
import dev.matthiesen.cobblemon_poketotem.common.registry.PermissionRegistry;
import dev.matthiesen.common.matthiesen_lib_api.abstracts.AbstractCommonMod;
import dev.matthiesen.common.matthiesen_lib_api.config.ConfigManager;
import dev.matthiesen.common.matthiesen_lib_api.permission.Permission;
import dev.matthiesen.libs.faststats.Token;
import net.minecraft.commands.CommandSourceStack;
import org.jetbrains.annotations.NotNull;

public final class CobblemonPokeTotemCommon extends AbstractCommonMod {
    public static final String MOD_ID = "cobblemon_poketotem";
    public static final String MOD_NAME = "Cobblemon PokeTotem";
    private static @Token final String METRICS_TOKEN = "3bef59eaa8edad9d5b767a1400619696";

    public static final class NBT {
        public static final String POKEMON_DATA_TAG = "CPT_NBT";
        public static final String STANDARD_FN_TAG = "CPT_FN";
        public static final String STANDARD_FN_DATA = "cpt-poke-totem-fn";
        public static final String CLONE_DATA_TAG = "CPT_CLONE_NBT";
        public static final String CLONE_FN_DATA = "cpt-poke-totem-clone-fn";
    }

    public static final CobblemonPokeTotemCommon INSTANCE = new CobblemonPokeTotemCommon();

    private static final ConfigManager<MainConfig> CONFIG_MANAGER =
            INSTANCE.createConfigManager(MainConfig.class, "config");

    public CobblemonPokeTotemCommon() {
        super(MOD_ID, MOD_NAME);
    }

    @Override
    public void initialize() {
        super.initialize();
        reload().run();
        PermissionRegistry.init();

        registerCommand(PokeToTotem.CMD);
        registerCommand(TotemToPoke.CMD);

        PlayerFunctionsExtension.register();
    }

    @Override
    public @NotNull @Token String getMetricsToken() {
        return METRICS_TOKEN;
    }

    @Override
    public Runnable reload() {
        return () -> {
            CONFIG_MANAGER.loadConfig();
            createInfoLog("Reloaded config");
        };
    }

    public MainConfig getConfig() {
        return CONFIG_MANAGER.getConfig();
    }

    public PermissionRegistry.Permissions getPermissions() {
        return PermissionRegistry.getPermissions();
    }

    public boolean checkPermission(CommandSourceStack source, Permission permission) {
        return PermissionRegistry.checkPermission(source, permission);
    }
}
