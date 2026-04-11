package xyz.station48.common.cobblemon_poketotem.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class CobblemonPokeTotemConfig {
    @SerializedName("permissionlevels")
    public PermissionLevels permissionLevels = new PermissionLevels();

    public static class PermissionLevels {
        @SerializedName("command.poketototem")
        public int COMMAND_POKETOTOTEM_PERMISSION_LEVEL = 2;

        @SerializedName("command.poketototem-server")
        public int COMMAND_POKETOTOTEM_SERVER_PERMISSION_LEVEL = 2;

        @SerializedName("command.totemtopoke")
        public int COMMAND_TOTEMTOPOKE_PERMISSION_LEVEL = 0;

        @SerializedName("command.totemtopoke-server")
        public int COMMAND_TOTEMTOPOKE_SERVER_LEVEL = 2;
    }

    public static final Gson GSON = new GsonBuilder()
            .disableHtmlEscaping()
            .setPrettyPrinting()
            .create();
}
