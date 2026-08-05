package dev.matthiesen.cobblemon_poketotem.common.config;

import dev.matthiesen.matthiesen_core.common.api.permissions.PermissionLevel;
import net.neoforged.neoforge.common.ModConfigSpec;

public final class ServerConfig {

    // Permission Levels
    public ModConfigSpec.IntValue permission_poketototem;
    public ModConfigSpec.IntValue permission_poketototem_server;
    public ModConfigSpec.IntValue permission_totemtopoke;
    public ModConfigSpec.IntValue permission_totemtopoke_server;
    public ModConfigSpec.IntValue permission_totemtopoke_redeem;
    public ModConfigSpec.IntValue permission_totemtopoke_redeem_server;

    public ServerConfig(ModConfigSpec.Builder builder) {
        builder.comment("Permission Levels for Commands")
                .push("permissions");
        permission_poketototem = builder.comment("Permission Level for /poketototem command")
                .defineInRange("command.poketototem", PermissionLevel.CHEAT_COMMANDS_AND_COMMAND_BLOCKS.getLevel(), 0, 4);
        permission_poketototem_server = builder.comment("Permission Level for /poketototem-server command")
                .defineInRange("command.poketototem-server", PermissionLevel.CHEAT_COMMANDS_AND_COMMAND_BLOCKS.getLevel(), 0, 4);
        permission_totemtopoke = builder.comment("Permission Level for /totemtopoke command")
                .defineInRange("command.totemtopoke", PermissionLevel.NONE.getLevel(), 0, 4);
        permission_totemtopoke_server = builder.comment("Permission Level for /totemtopoke-server command")
                .defineInRange("command.totemtopoke-server", PermissionLevel.CHEAT_COMMANDS_AND_COMMAND_BLOCKS.getLevel(), 0, 4);
        permission_totemtopoke_redeem = builder.comment("Permission Level for /totemtopoke-redeem command")
                .defineInRange("command.totemtopoke-redeem", PermissionLevel.NONE.getLevel(), 0, 4);
        permission_totemtopoke_redeem_server = builder.comment("Permission Level for /totemtopoke-redeem-server command")
                .defineInRange("command.totemtopoke-redeem-server", PermissionLevel.CHEAT_COMMANDS_AND_COMMAND_BLOCKS.getLevel(), 0, 4);
        builder.pop();
    }
}
