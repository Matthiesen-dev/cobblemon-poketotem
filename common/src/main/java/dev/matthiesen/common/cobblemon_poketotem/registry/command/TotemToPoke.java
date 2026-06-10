package dev.matthiesen.common.cobblemon_poketotem.registry.command;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.storage.party.PartyStore;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import dev.matthiesen.common.cobblemon_poketotem.CobblemonPokeTotem;
import dev.matthiesen.common.cobblemon_poketotem.Constants;
import dev.matthiesen.common.cobblemon_poketotem.registry.PermissionRegistry;
import dev.matthiesen.common.cobblemon_poketotem.util.CommandUtils;
import dev.matthiesen.common.cobblemon_poketotem.util.PokemonUtility;
import dev.matthiesen.common.matthiesen_lib_api.command.AbstractCommand;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;

/**
 * Command Structure:
 * - /totemtopoke
 * - /totemtopoke server [player]
 */
public final class TotemToPoke extends AbstractCommand {
    public static final TotemToPoke CMD = new TotemToPoke();

    @Override
    public void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext registry, Commands.CommandSelection context) {
        var permissions = CobblemonPokeTotem.getPermissions();
        dispatcher.register(
                Commands.literal("totemtopoke")
                        .requires(src -> CobblemonPokeTotem.checkPermission(
                                src, permissions.TOTEMTOPOKE_PERMISSION
                        ))
                        .executes(this::action)
                        .then(serverSubCMD(permissions))
        );
    }

    public LiteralArgumentBuilder<CommandSourceStack> serverSubCMD(PermissionRegistry.Permissions permissions) {
        return Commands.literal("server")
                .requires(src -> CobblemonPokeTotem.checkPermission(
                        src, permissions.TOTEMTOPOKE_SERVER_PERMISSION
                ))
                .then(
                        Commands.argument("player", EntityArgument.player())
                                .executes(this::server)
                );
    }

    public Void shared(ServerPlayer target) {
        ItemStack item = target.getMainHandItem();

        // Make sure the item has CustomData
        CustomData customData = item.get(DataComponents.CUSTOM_DATA);
        if (customData != null) {
            CompoundTag tag = customData.copyTag();

            if (tag.contains(Constants.NBT.CLONE_DATA_TAG)) {
                target.displayClientMessage(Component.literal("[§c§lCobblemonPokeTotem§f] §c§lYou are holding a Totem that requires the '/totemtopoke-redeem' command!"), false);
                return null;
            }

            // Check if it has a "pokemon" tag
            if (tag.contains(Constants.NBT.POKEMON_DATA_TAG)) {
                CompoundTag pokemonTag = tag.getCompound(Constants.NBT.POKEMON_DATA_TAG);
                var registryAccess = target.level().registryAccess();
                Pokemon pokemon = PokemonUtility.createPokemonFromNBT(registryAccess, pokemonTag);

                // Add to party
                PartyStore party = Cobblemon.INSTANCE.getStorage().getParty(target);
                boolean added = party.add(pokemon);

                if (added) {
                    target.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                    target.displayClientMessage(
                            Component.literal("[§c§lCobblemonPokeTotem§f] §a§lPokémon restored to your party or PC!"),
                            false
                    );
                } else {
                    target.displayClientMessage(
                            Component.literal("[§c§lCobblemonPokeTotem§f] §a§lPokémon failed to restore!"),
                            false
                    );
                }
            } else {
                target.displayClientMessage(
                        Component.literal("[§c§lCobblemonPokeTotem§f] §4§lThis item does not contain a Pokémon."),
                        false
                );
            }
        } else {
            target.displayClientMessage(
                    Component.literal("[§c§lCobblemonPokeTotem§f] §4§lNo Pokémon data found on this item."),
                    false
            );
        }
        return null;
    }

    @Override
    public int action(CommandContext<CommandSourceStack> context) {
        return CommandUtils.runSharedCommandSelfPlayer(context, this::shared);
    }

    public int server(CommandContext<CommandSourceStack> context) {
        return CommandUtils.runSharedCommandWithPlayerArg(context, this::shared);
    }
}
