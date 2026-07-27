package dev.matthiesen.cobblemon_poketotem.common.config;

import com.google.gson.annotations.SerializedName;
import dev.matthiesen.matthiesen_core.common.api.permissions.PermissionLevel;

public final class MainConfig {
    @SerializedName("permissionlevels")
    public PermissionLevels permissionLevels = new PermissionLevels();

    public static class PermissionLevels {
        @SerializedName("command.poketototem")
        public int COMMAND_POKETOTOTEM_PERMISSION_LEVEL =
                PermissionLevel.CHEAT_COMMANDS_AND_COMMAND_BLOCKS.getLevel();

        @SerializedName("command.poketototem-server")
        public int COMMAND_POKETOTOTEM_SERVER_PERMISSION_LEVEL =
                PermissionLevel.CHEAT_COMMANDS_AND_COMMAND_BLOCKS.getLevel();

        @SerializedName("command.totemtopoke")
        public int COMMAND_TOTEMTOPOKE_PERMISSION_LEVEL =
                PermissionLevel.NONE.getLevel();

        @SerializedName("command.totemtopoke-redeem")
        public int COMMAND_TOTEMTOPOKE_REDEEM_PERMISSION_LEVEL =
                PermissionLevel.NONE.getLevel();

        @SerializedName("command.totemtopoke-redeem-server")
        public int COMMAND_TOTEMTOPOKE_REDEEM_SERVER_PERMISSION_LEVEL =
                PermissionLevel.CHEAT_COMMANDS_AND_COMMAND_BLOCKS.getLevel();

        @SerializedName("command.totemtopoke-server")
        public int COMMAND_TOTEMTOPOKE_SERVER_LEVEL =
                PermissionLevel.CHEAT_COMMANDS_AND_COMMAND_BLOCKS.getLevel();
    }
}
