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
    public static final String NBTCloneDataTag = "CPT_CLONE_NBT";
    public static final String NBTCloneFnData = "cpt-poke-totem-clone-fn";

    public enum PERMISSION_LEVELS {
        NONE(0),
        SPAWN_PROTECTION_BYPASS(1),
        CHEAT_COMMANDS_AND_COMMAND_BLOCKS(2),
        MULTIPLAYER_MANAGEMENT(3),
        ALL_COMMANDS(4);

        private final int level;

        PERMISSION_LEVELS(int level) {
            this.level = level;
        }

        public int getLevel() {
            return level;
        }
    }

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
