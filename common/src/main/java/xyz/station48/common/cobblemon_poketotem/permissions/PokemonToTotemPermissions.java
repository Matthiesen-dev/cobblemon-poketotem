package xyz.station48.common.cobblemon_poketotem.permissions;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.permission.PermissionLevel;
import net.minecraft.commands.CommandSourceStack;
import xyz.station48.common.cobblemon_poketotem.CobblemonPokeTotem;
import xyz.station48.common.cobblemon_poketotem.Constants;

public class PokemonToTotemPermissions {
    public final PokemonToTotemPermission POKETOTOTEM_PERMISSION;
    public final PokemonToTotemPermission POKETOTOTEM_SERVER_PERMISSION;
    public final PokemonToTotemPermission TOTEMTOPOKE_PERMISSION;
    public final PokemonToTotemPermission TOTEMTOPOKE_SERVER_PERMISSION;
    public final PokemonToTotemPermission TOTEMTOPOKE_REDEEM_PERMISSION;
    public final PokemonToTotemPermission TOTEMTOPOKE_REDEEM_SERVER_PERMISSION;

    public PokemonToTotemPermissions() {
        this.POKETOTOTEM_PERMISSION = new PokemonToTotemPermission(
                Constants.ModId + ".command.poketototem",
                toPermLevel(CobblemonPokeTotem.config.permissionLevels.COMMAND_POKETOTOTEM_PERMISSION_LEVEL)
        );
        this.POKETOTOTEM_SERVER_PERMISSION = new PokemonToTotemPermission(
                Constants.ModId + ".command.poketototem-server",
                toPermLevel(CobblemonPokeTotem.config.permissionLevels.COMMAND_POKETOTOTEM_SERVER_PERMISSION_LEVEL)
        );
        this.TOTEMTOPOKE_PERMISSION = new PokemonToTotemPermission(
                Constants.ModId + ".command.totemtopoke",
                toPermLevel(CobblemonPokeTotem.config.permissionLevels.COMMAND_TOTEMTOPOKE_PERMISSION_LEVEL)
        );
        this.TOTEMTOPOKE_SERVER_PERMISSION = new PokemonToTotemPermission(
                Constants.ModId + ".command.totemtopoke-server",
                toPermLevel(CobblemonPokeTotem.config.permissionLevels.COMMAND_TOTEMTOPOKE_SERVER_LEVEL)
        );
        this.TOTEMTOPOKE_REDEEM_PERMISSION = new PokemonToTotemPermission(
                Constants.ModId + ".command.totemtopoke-redeem",
                toPermLevel(CobblemonPokeTotem.config.permissionLevels.COMMAND_TOTEMTOPOKE_REDEEM_PERMISSION_LEVEL)
        );
        this.TOTEMTOPOKE_REDEEM_SERVER_PERMISSION = new PokemonToTotemPermission(
                Constants.ModId + ".command.totemtopoke-redeem-server",
                toPermLevel(CobblemonPokeTotem.config.permissionLevels.COMMAND_TOTEMTOPOKE_REDEEM_SERVER_PERMISSION_LEVEL)
        );
    }

    public PermissionLevel toPermLevel(int permLevel) {
        for (PermissionLevel value : PermissionLevel.values()) {
            if (value.ordinal() == permLevel) {
                return value;
            }
        }
        return PermissionLevel.CHEAT_COMMANDS_AND_COMMAND_BLOCKS;
    }

    public static boolean checkPermission(CommandSourceStack source, PokemonToTotemPermission permission) {
        return Cobblemon.INSTANCE.getPermissionValidator().hasPermission(source, permission);
    }
}