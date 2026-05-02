package dev.matthiesen.common.cobblemon_poketotem.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import dev.matthiesen.common.cobblemon_poketotem.Constants;

public class CobblemonPokeTotemConfig {
    @SerializedName("permissionlevels")
    public PermissionLevels permissionLevels = new PermissionLevels();

    public static class PermissionLevels {
        @SerializedName("command.poketototem")
        public int COMMAND_POKETOTOTEM_PERMISSION_LEVEL =
                Constants.PERMISSION_LEVELS.CHEAT_COMMANDS_AND_COMMAND_BLOCKS.getLevel();

        @SerializedName("command.poketototem-server")
        public int COMMAND_POKETOTOTEM_SERVER_PERMISSION_LEVEL =
                Constants.PERMISSION_LEVELS.CHEAT_COMMANDS_AND_COMMAND_BLOCKS.getLevel();

        @SerializedName("command.totemtopoke")
        public int COMMAND_TOTEMTOPOKE_PERMISSION_LEVEL =
                Constants.PERMISSION_LEVELS.NONE.getLevel();

        @SerializedName("command.totemtopoke-redeem")
        public int COMMAND_TOTEMTOPOKE_REDEEM_PERMISSION_LEVEL =
                Constants.PERMISSION_LEVELS.NONE.getLevel();

        @SerializedName("command.totemtopoke-redeem-server")
        public int COMMAND_TOTEMTOPOKE_REDEEM_SERVER_PERMISSION_LEVEL =
                Constants.PERMISSION_LEVELS.CHEAT_COMMANDS_AND_COMMAND_BLOCKS.getLevel();

        @SerializedName("command.totemtopoke-server")
        public int COMMAND_TOTEMTOPOKE_SERVER_LEVEL =
                Constants.PERMISSION_LEVELS.CHEAT_COMMANDS_AND_COMMAND_BLOCKS.getLevel();
    }

    public static final Gson GSON = new GsonBuilder()
            .disableHtmlEscaping()
            .setPrettyPrinting()
            .create();
}
