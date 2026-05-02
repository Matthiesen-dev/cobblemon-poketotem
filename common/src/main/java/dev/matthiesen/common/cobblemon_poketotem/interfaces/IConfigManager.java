package dev.matthiesen.common.cobblemon_poketotem.interfaces;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.matthiesen.common.cobblemon_poketotem.config.CobblemonPokeTotemConfig;

public interface IConfigManager {
    CobblemonPokeTotemConfig loadConfig();
    JsonElement mergeConfigs(JsonObject defaultConfig, JsonObject fileConfig);
    void saveConfig();
}
