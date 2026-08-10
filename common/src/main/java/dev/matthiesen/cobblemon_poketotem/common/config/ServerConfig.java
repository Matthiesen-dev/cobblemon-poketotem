package dev.matthiesen.cobblemon_poketotem.common.config;

import dev.matthiesen.matthiesen_core.common.api.permissions.PermissionLevel;
import net.neoforged.neoforge.common.ModConfigSpec;

public final class ServerConfig {

    // Permission Levels
    public ModConfigSpec.EnumValue<PermissionLevel> permission_poketototem;
    public ModConfigSpec.EnumValue<PermissionLevel> permission_poketototem_server;
    public ModConfigSpec.EnumValue<PermissionLevel> permission_totemtopoke;
    public ModConfigSpec.EnumValue<PermissionLevel> permission_totemtopoke_server;
    public ModConfigSpec.EnumValue<PermissionLevel> permission_totemtopoke_redeem;
    public ModConfigSpec.EnumValue<PermissionLevel> permission_totemtopoke_redeem_server;

    public ServerConfig(ModConfigSpec.Builder builder) {
        builder.comment("Permission Levels for Commands")
                .push("permissions");
        permission_poketototem = builder.comment("Permission Level for /poketototem command")
                .defineEnum("command.poketototem", PermissionLevel.CHEAT_COMMANDS_AND_COMMAND_BLOCKS);
        permission_poketototem_server = builder.comment("Permission Level for /poketototem-server command")
                .defineEnum("command.poketototem-server", PermissionLevel.CHEAT_COMMANDS_AND_COMMAND_BLOCKS);
        permission_totemtopoke = builder.comment("Permission Level for /totemtopoke command")
                .defineEnum("command.totemtopoke", PermissionLevel.NONE);
        permission_totemtopoke_server = builder.comment("Permission Level for /totemtopoke-server command")
                .defineEnum("command.totemtopoke-server", PermissionLevel.CHEAT_COMMANDS_AND_COMMAND_BLOCKS);
        permission_totemtopoke_redeem = builder.comment("Permission Level for /totemtopoke-redeem command")
                .defineEnum("command.totemtopoke-redeem", PermissionLevel.NONE);
        permission_totemtopoke_redeem_server = builder.comment("Permission Level for /totemtopoke-redeem-server command")
                .defineEnum("command.totemtopoke-redeem-server", PermissionLevel.CHEAT_COMMANDS_AND_COMMAND_BLOCKS);
        builder.pop();
    }
}
