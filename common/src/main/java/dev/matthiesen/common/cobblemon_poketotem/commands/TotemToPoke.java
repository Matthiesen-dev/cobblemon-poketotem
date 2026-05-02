package dev.matthiesen.common.cobblemon_poketotem.commands;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.storage.party.PartyStore;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import dev.matthiesen.common.cobblemon_poketotem.CobblemonPokeTotem;
import dev.matthiesen.common.cobblemon_poketotem.Constants;
import dev.matthiesen.common.cobblemon_poketotem.interfaces.ICommand;
import dev.matthiesen.common.cobblemon_poketotem.permissions.CobblemonPokeTotemPermissions;
import dev.matthiesen.common.cobblemon_poketotem.util.PokemonUtility;

public class TotemToPoke implements ICommand {
    public TotemToPoke() {}

    public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("totemtopoke")
                        .requires(src -> CobblemonPokeTotemPermissions.checkPermission(
                                src,
                                CobblemonPokeTotem.permissions.TOTEMTOPOKE_PERMISSION
                        ))
                        .executes(this::runner)
        );
    }

    public static void shared(ServerPlayer target) {
        ItemStack item = target.getMainHandItem();

        // Make sure the item has CustomData
        CustomData customData = item.get(DataComponents.CUSTOM_DATA);
        if (customData != null) {
            CompoundTag tag = customData.copyTag();

            if (tag.contains(Constants.NBTCloneDataTag)) {
                target.displayClientMessage(Component.literal("[§c§lCobblemonPokeTotem§f] §c§lYou are holding a Totem that requires the '/totemtopoke-redeem' command!"), false);
                return;
            }

            // Check if it has a "pokemon" tag
            if (tag.contains(Constants.NBTPokemonDataTag)) {
                CompoundTag pokemonTag = tag.getCompound(Constants.NBTPokemonDataTag);
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
    }

    private int runner(CommandContext<CommandSourceStack> ctx) {
        ServerPlayer target = ctx.getSource().getPlayer();
        if (target == null) {
            return 0;
        }
        shared(target);
        return 1;
    }
}