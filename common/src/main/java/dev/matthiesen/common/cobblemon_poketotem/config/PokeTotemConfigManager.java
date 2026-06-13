package dev.matthiesen.common.cobblemon_poketotem.config;

import dev.matthiesen.common.cobblemon_poketotem.Constants;
import dev.matthiesen.common.matthiesen_lib_api.config.ConfigManager;

public final class PokeTotemConfigManager<T> extends ConfigManager<T> {
    public PokeTotemConfigManager(Class<T> configClass, String configName) {
        super(configClass, configName, Constants.MOD_ID);
    }
}
