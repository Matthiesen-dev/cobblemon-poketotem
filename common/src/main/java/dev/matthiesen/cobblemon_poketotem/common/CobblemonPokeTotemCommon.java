package dev.matthiesen.cobblemon_poketotem.common;

import dev.matthiesen.cobblemon_poketotem.common.command.PokeToTotem;
import dev.matthiesen.cobblemon_poketotem.common.command.TotemToPoke;
import dev.matthiesen.cobblemon_poketotem.common.config.PokeTotemConfig;
import dev.matthiesen.cobblemon_poketotem.common.utility.PokeTotemItemHelper;
import dev.matthiesen.cobblemon_poketotem.common.molang.PlayerFunctionsExtension;
import dev.matthiesen.cobblemon_poketotem.common.registry.PermissionRegistry;
import dev.matthiesen.libs.faststats.Token;
import dev.matthiesen.matthiesen_core.common.AbstractCommonMod;
import dev.matthiesen.matthiesen_core.common.api.events.PlatformEvents;
import dev.matthiesen.matthiesen_core.common.api.permissions.Permission;
import dev.matthiesen.matthiesen_core.common.api.platform.loader.ModConfigType;
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

    public CobblemonPokeTotemCommon() {
        super(MOD_ID, MOD_NAME);
    }

    @Override
    public void initialize() {
        super.initialize();
        registerModConfig(MOD_ID, ModConfigType.STARTUP, PokeTotemConfig.SERVER_SPEC);

        PermissionRegistry.init();
        getCommandsRegistryManager().registerCommand(PokeToTotem.CMD);
        getCommandsRegistryManager().registerCommand(TotemToPoke.CMD);

        PlatformEvents.PLAYER_USE_ITEM.subscribe(event ->
                PokeTotemItemHelper.runInteraction(event.player(), event.level(), event.hand()));

        PlayerFunctionsExtension.register();

        createInfoLog("Initialized common");
    }

    @Override
    public @NotNull @Token String getMetricsToken() {
        return METRICS_TOKEN;
    }

    public PermissionRegistry.Permissions getPermissions() {
        return PermissionRegistry.getPermissions();
    }

    public boolean checkPermission(CommandSourceStack source, Permission permission) {
        return PermissionRegistry.checkPermission(source, permission);
    }
}
