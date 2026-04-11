package xyz.station48.common.cobblemon_poketotem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Constants {
    public static final String ModId = "cobblemon_poketotem";
    public static final String ModName = "Cobblemon PokeTotem";
    public static final String LoggerPrefix = "[" + ModName + "]";
    public static final String NBTPokemonDataTag = "CPT_NBT";
    public static final String NBTStandardFnTag = "CPT_FN";
    public static final String NBTStandardFnData = "cpt-poke-totem-fn";

    public static Logger LOGGER = LogManager.getLogger(ModName);

    public static String createLogString(String message) {
        return LoggerPrefix + " " + message;
    }

    public static void createInfoLog(String message) {
        LOGGER.info(message);
    }

    public static void createErrorLog(String message) {
        LOGGER.error(message);
    }
}
