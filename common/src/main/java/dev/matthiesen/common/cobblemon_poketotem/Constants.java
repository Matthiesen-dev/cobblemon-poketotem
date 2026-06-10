package dev.matthiesen.common.cobblemon_poketotem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Constants {
    public static final String MOD_ID = "cobblemon_poketotem";
    public static final String MOD_NAME = "Cobblemon PokeTotem";

    public static final class NBT {
        public static final String POKEMON_DATA_TAG = "CPT_NBT";
        public static final String STANDARD_FN_TAG = "CPT_FN";
        public static final String STANDARD_FN_DATA = "cpt-poke-totem-fn";
        public static final String CLONE_DATA_TAG = "CPT_CLONE_NBT";
        public static final String CLONE_FN_DATA = "cpt-poke-totem-clone-fn";
    }

    public static Logger LOGGER = LogManager.getLogger(MOD_NAME);

    public static void createInfoLog(String message) {
        LOGGER.info(message);
    }

    public static void createErrorLog(String message) {
        LOGGER.error(message);
    }

    public static void createErrorLog(String message, Throwable throwable) {
        LOGGER.error(message, throwable);
    }
}
