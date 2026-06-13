package dev.matthiesen.common.cobblemon_poketotem.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import dev.matthiesen.common.matthiesen_lib_api.permission.PermissionLevel;

public final class CobblemonPokeTotemConfig {
    @SerializedName("permissionlevels")
    public PermissionLevels permissionLevels = new PermissionLevels();

    public static class PermissionLevels {
        @SerializedName("command.poketototem")
        public int COMMAND_POKETOTOTEM_PERMISSION_LEVEL =
                PermissionLevel.CHEAT_COMMANDS_AND_COMMAND_BLOCKS.getNumericalValue();

        @SerializedName("command.poketototem-server")
        public int COMMAND_POKETOTOTEM_SERVER_PERMISSION_LEVEL =
                PermissionLevel.CHEAT_COMMANDS_AND_COMMAND_BLOCKS.getNumericalValue();

        @SerializedName("command.totemtopoke")
        public int COMMAND_TOTEMTOPOKE_PERMISSION_LEVEL =
                PermissionLevel.NONE.getNumericalValue();

        @SerializedName("command.totemtopoke-redeem")
        public int COMMAND_TOTEMTOPOKE_REDEEM_PERMISSION_LEVEL =
                PermissionLevel.NONE.getNumericalValue();

        @SerializedName("command.totemtopoke-redeem-server")
        public int COMMAND_TOTEMTOPOKE_REDEEM_SERVER_PERMISSION_LEVEL =
                PermissionLevel.CHEAT_COMMANDS_AND_COMMAND_BLOCKS.getNumericalValue();

        @SerializedName("command.totemtopoke-server")
        public int COMMAND_TOTEMTOPOKE_SERVER_LEVEL =
                PermissionLevel.CHEAT_COMMANDS_AND_COMMAND_BLOCKS.getNumericalValue();
    }

    @SuppressWarnings("unused")
    public static final Gson GSON = new GsonBuilder()
            .disableHtmlEscaping()
            .setPrettyPrinting()
            .create();
}
