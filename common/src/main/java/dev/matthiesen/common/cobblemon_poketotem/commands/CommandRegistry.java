package dev.matthiesen.common.cobblemon_poketotem.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import dev.matthiesen.common.cobblemon_poketotem.interfaces.ICommand;

import java.util.List;

public class CommandRegistry {
    public static final List<ICommand> COMMANDS = List.of(
            new PokeToTotem(),
            new PokeToTotemServer(),
            new TotemToPoke(),
            new TotemToPokeServer(),
            new TotemToPokeRedeem(),
            new TotemToPokeRedeemServer()
    );

    public static void init(CommandDispatcher<CommandSourceStack> dispatcher) {
        for (ICommand command : COMMANDS) {
            command.register(dispatcher);
        }
    }
}
