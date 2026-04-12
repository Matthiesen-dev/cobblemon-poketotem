package xyz.station48.common.cobblemon_poketotem.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import xyz.station48.common.cobblemon_poketotem.Constants;
import xyz.station48.common.cobblemon_poketotem.interfaces.IConfigManager;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class ConfigManager implements IConfigManager {
    public static CobblemonPokeTotemConfig config;

    public ConfigManager() {}

    @Override
    public CobblemonPokeTotemConfig loadConfig() {
        String configFileLoc = System.getProperty("user.dir") + File.separator + "config" +
                File.separator + Constants.ModId + File.separator + "config.json";
        Constants.createInfoLog("Loading config file found at: " + configFileLoc);
        File configFile = new File(configFileLoc);
        configFile.getParentFile().mkdirs();

        // Check config existence and load if it exists, otherwise create default.
        if (configFile.exists()) {
            try {
                FileReader fileReader = new FileReader(configFile);

                // Create a default config instance
                CobblemonPokeTotemConfig defaultConfig = new CobblemonPokeTotemConfig();
                String defaultConfigJson = CobblemonPokeTotemConfig.GSON.toJson(defaultConfig);

                JsonElement fileConfigElement = JsonParser.parseReader(fileReader);

                // Convert default config JSON string to JsonElement
                JsonElement defaultConfigElement = JsonParser.parseString(defaultConfigJson);

                // Merge default config with the file config
                JsonElement mergedConfigElement = mergeConfigs(
                        defaultConfigElement.getAsJsonObject(),
                        fileConfigElement.getAsJsonObject()
                );

                // Deserialize the merged JsonElement back to PokemonToItemConfig
                CobblemonPokeTotemConfig finalConfig;
                finalConfig = CobblemonPokeTotemConfig.GSON.fromJson(
                        mergedConfigElement,
                        CobblemonPokeTotemConfig.class
                );

                config = finalConfig;

                fileReader.close();
            } catch (Exception e) {
                System.err.println(Constants.createLogString("Failed to load the config! Using default config as fallback"));
                e.printStackTrace();
                config = new CobblemonPokeTotemConfig();
            }
        } else {
            config = new CobblemonPokeTotemConfig();
        }

        saveConfig();

        return config;
    }

    @Override
    public JsonElement mergeConfigs(JsonObject defaultConfig, JsonObject fileConfig) {
        // For every entry in the default config, check if it exists in the file config
        Constants.createInfoLog("Checking for config merge.");
        boolean merged = false;

        for (String key : defaultConfig.keySet()) {
            if (!fileConfig.has(key)) {
                // If the file config does not have the key, add it from the default config
                fileConfig.add(key, defaultConfig.get(key));
                Constants.createInfoLog(key + " not found in file config, adding from default.");
                merged = true;
            } else {
                // If it's a nested object, recursively merge it
                if (defaultConfig.get(key).isJsonObject() && fileConfig.get(key).isJsonObject()) {
                    mergeConfigs(defaultConfig.getAsJsonObject(key), fileConfig.getAsJsonObject(key));
                }
            }
        }

        if (merged) {
            Constants.createInfoLog("Successfully merged config.");
        }

        return fileConfig;
    }

    @Override
    public void saveConfig() {
        try {
            String configFileLoc = System.getProperty("user.dir") + File.separator + "config" +
                    File.separator + Constants.ModId + File.separator + "config.json";
            Constants.createInfoLog("Saving config to: " + configFileLoc);
            File configFile = new File(configFileLoc);
            FileWriter fileWriter = new FileWriter(configFile);
            CobblemonPokeTotemConfig.GSON.toJson(config, fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            Constants.createErrorLog("Failed to save config");
            e.printStackTrace();
        }
    }
}
