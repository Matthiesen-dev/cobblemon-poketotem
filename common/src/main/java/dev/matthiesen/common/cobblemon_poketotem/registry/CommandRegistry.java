package dev.matthiesen.common.cobblemon_poketotem.registry;

import dev.matthiesen.common.cobblemon_poketotem.registry.command.PokeToTotem;
import dev.matthiesen.common.cobblemon_poketotem.registry.command.TotemToPoke;
import dev.matthiesen.common.cobblemon_poketotem.registry.command.TotemToPokeRedeem;
import dev.matthiesen.common.matthiesen_lib_api.registry.AbstractCommandRegistry;

public final class CommandRegistry extends AbstractCommandRegistry {
    private static final CommandRegistry INSTANCE = new CommandRegistry();

    public CommandRegistry() {
        super();
    }

    static {
        INSTANCE.register(PokeToTotem.CMD);
        INSTANCE.register(TotemToPoke.CMD);
        INSTANCE.register(TotemToPokeRedeem.CMD);
    }

    public static void init() {}
}
