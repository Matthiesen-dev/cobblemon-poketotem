package dev.matthiesen.cobblemon_poketotem.common.registry;

import dev.matthiesen.cobblemon_poketotem.common.CobblemonPokeTotemCommon;
import dev.matthiesen.matthiesen_core.common.api.permissions.Permission;
import dev.matthiesen.matthiesen_core.common.api.permissions.PermissionLevel;
import dev.matthiesen.matthiesen_core.common.utility.AbstractPermission;
import net.minecraft.commands.CommandSourceStack;

public final class PermissionRegistry {
    public static Permission POKETOTOTEM_PERMISSION = register("command.poketototem",
            CobblemonPokeTotemCommon.INSTANCE.getConfig().permissionLevels.COMMAND_POKETOTOTEM_PERMISSION_LEVEL);
    public static Permission POKETOTOTEM_SERVER_PERMISSION = register("command.poketototem-server",
            CobblemonPokeTotemCommon.INSTANCE.getConfig().permissionLevels.COMMAND_POKETOTOTEM_SERVER_PERMISSION_LEVEL);
    public static Permission TOTEMTOPOKE_PERMISSION = register("command.totemtopoke",
            CobblemonPokeTotemCommon.INSTANCE.getConfig().permissionLevels.COMMAND_TOTEMTOPOKE_PERMISSION_LEVEL);
    public static Permission TOTEMTOPOKE_SERVER_PERMISSION = register("command.totemtopoke-server",
            CobblemonPokeTotemCommon.INSTANCE.getConfig().permissionLevels.COMMAND_TOTEMTOPOKE_SERVER_LEVEL);
    public static Permission TOTEMTOPOKE_REDEEM_PERMISSION = register("command.totemtopoke-redeem",
            CobblemonPokeTotemCommon.INSTANCE.getConfig().permissionLevels.COMMAND_TOTEMTOPOKE_REDEEM_PERMISSION_LEVEL);
    public static Permission TOTEMTOPOKE_REDEEM_SERVER_PERMISSION = register("command.totemtopoke-redeem-server",
            CobblemonPokeTotemCommon.INSTANCE.getConfig().permissionLevels.COMMAND_TOTEMTOPOKE_REDEEM_SERVER_PERMISSION_LEVEL);

    public static class Permissions {
        public Permission POKETOTOTEM_PERMISSION = PermissionRegistry.POKETOTOTEM_PERMISSION;
        public Permission POKETOTOTEM_SERVER_PERMISSION = PermissionRegistry.POKETOTOTEM_SERVER_PERMISSION;
        public Permission TOTEMTOPOKE_PERMISSION = PermissionRegistry.TOTEMTOPOKE_PERMISSION;
        public Permission TOTEMTOPOKE_SERVER_PERMISSION = PermissionRegistry.TOTEMTOPOKE_SERVER_PERMISSION;
        public Permission TOTEMTOPOKE_REDEEM_PERMISSION = PermissionRegistry.TOTEMTOPOKE_REDEEM_PERMISSION;
        public Permission TOTEMTOPOKE_REDEEM_SERVER_PERMISSION = PermissionRegistry.TOTEMTOPOKE_REDEEM_SERVER_PERMISSION;
    }

    public static Permissions PERMISSIONS = null;

    public static Permissions getPermissions() {
        if (PERMISSIONS == null) {
            PERMISSIONS = new Permissions();
        }
        return PERMISSIONS;
    }

    public static void init() {}

    public static boolean checkPermission(CommandSourceStack source, Permission permission) {
        return CobblemonPokeTotemCommon.INSTANCE.getPermissionsManager().getPermissionValidator().hasPermission(source, permission);
    }

    public static PermissionLevel toPermLevel(int permLevel) {
        for (PermissionLevel value : PermissionLevel.values()) {
            if (value.ordinal() == permLevel) {
                return value;
            }
        }
        return PermissionLevel.CHEAT_COMMANDS_AND_COMMAND_BLOCKS;
    }

    private static Permission register(String node, int level) {
        var newPermission = modPermission(node, toPermLevel(level));
        CobblemonPokeTotemCommon.INSTANCE.getPermissionsManager().registerPermission(newPermission);
        return newPermission;
    }

    private static Permission modPermission(String node, PermissionLevel level) {
        return new AbstractPermission(node, level) {
            @Override
            protected String getModId() {
                return CobblemonPokeTotemCommon.MOD_ID;
            }

            @Override
            protected String getPermissionNamespace() {
                return "CobblemonPokeTotem";
            }
        };
    }
}
