package dev.matthiesen.common.cobblemon_poketotem.interfaces;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;

public interface ICommand {
    void register(CommandDispatcher<CommandSourceStack> dispatcher);
}
