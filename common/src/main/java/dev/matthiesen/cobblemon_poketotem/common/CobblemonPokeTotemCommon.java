package dev.matthiesen.cobblemon_poketotem.common;

import dev.matthiesen.cobblemon_poketotem.common.command.PokeToTotem;
import dev.matthiesen.cobblemon_poketotem.common.command.TotemToPoke;
import dev.matthiesen.cobblemon_poketotem.common.config.MainConfig;
import dev.matthiesen.cobblemon_poketotem.common.utility.PokeTotemItemHelper;
import dev.matthiesen.cobblemon_poketotem.common.molang.PlayerFunctionsExtension;
import dev.matthiesen.cobblemon_poketotem.common.registry.PermissionRegistry;
import dev.matthiesen.common.matthiesen_lib_api.abstracts.AbstractCommonMod;
import dev.matthiesen.common.matthiesen_lib_api.config.ConfigManager;
import dev.matthiesen.common.matthiesen_lib_api.core.interfaces.MatthiesenLibPlayerEventHandler;
import dev.matthiesen.common.matthiesen_lib_api.permission.Permission;
import dev.matthiesen.libs.faststats.Token;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.Level;
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
        registerPlayerEventHandler(PlayerEventsHandler.INSTANCE);

        PlayerFunctionsExtension.register();

        createInfoLog("Initialized common");
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

    public static class PlayerEventsHandler implements MatthiesenLibPlayerEventHandler {
        public static final PlayerEventsHandler INSTANCE = new PlayerEventsHandler();

        @Override
        public InteractionResult onPlayerUseItemResult(ServerPlayer player, Level level, InteractionHand hand) {
            return PokeTotemItemHelper.runInteraction(player, level, hand);
        }
    }
}
