package xyz.station48.common.cobblemon_poketotem;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import xyz.station48.common.cobblemon_poketotem.commands.PokeToTotem;
import xyz.station48.common.cobblemon_poketotem.commands.PokeToTotemServer;
import xyz.station48.common.cobblemon_poketotem.commands.TotemToPoke;
import xyz.station48.common.cobblemon_poketotem.commands.TotemToPokeServer;
import xyz.station48.common.cobblemon_poketotem.config.CobblemonPokeTotemConfig;
import xyz.station48.common.cobblemon_poketotem.molang.PlayerFunctionsExtension;
import xyz.station48.common.cobblemon_poketotem.permissions.PokemonToTotemPermissions;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class CobblemonPokeTotem {
    public static PokemonToTotemPermissions permissions;
    public static CobblemonPokeTotemConfig config;

    public static void initialize() {
        Constants.createInfoLog("Initialized");
        loadConfig();
        permissions = new PokemonToTotemPermissions();

        // Extend Cobblemon's Molang functions
        PlayerFunctionsExtension.register();
    }

    public static void onShutdown() {
        System.out.println(Constants.createLogString("Shutting Down"));
    }

    public static void loadConfig() {
        String configFileLoc = System.getProperty("user.dir") + File.separator + "config" +
                File.separator + Constants.ModId + File.separator + "config.json";
        System.out.println(Constants.createLogString("Loading config file found at: " + configFileLoc));
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
    }

    private static JsonElement mergeConfigs(JsonObject defaultConfig, JsonObject fileConfig) {
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

    private static void saveConfig() {
        try {
            String configFileLoc = System.getProperty("user.dir") + File.separator + "config" +
                    File.separator + Constants.ModId + File.separator + "config.json";
            System.out.println(Constants.createLogString("Saving config to: " + configFileLoc));
            File configFile = new File(configFileLoc);
            FileWriter fileWriter = new FileWriter(configFile);
            CobblemonPokeTotemConfig.GSON.toJson(config, fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            System.err.println(Constants.createLogString("Failed to save config"));
            e.printStackTrace();
        }
    }

    public static void registerCommands(CommandDispatcher<CommandSourceStack> dispatcher) {
        Constants.createInfoLog("Registering Commands");
        new PokeToTotem().register(dispatcher);
        new PokeToTotemServer().register(dispatcher);
        new TotemToPoke().register(dispatcher);
        new TotemToPokeServer().register(dispatcher);
    }
}
