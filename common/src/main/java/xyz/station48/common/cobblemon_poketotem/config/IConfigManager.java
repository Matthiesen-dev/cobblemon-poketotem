package xyz.station48.common.cobblemon_poketotem.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public interface IConfigManager {
    CobblemonPokeTotemConfig loadConfig();
    JsonElement mergeConfigs(JsonObject defaultConfig, JsonObject fileConfig);
    void saveConfig();
}
