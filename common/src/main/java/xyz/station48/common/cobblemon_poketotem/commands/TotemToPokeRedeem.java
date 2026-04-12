package xyz.station48.common.cobblemon_poketotem.commands;

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
import xyz.station48.common.cobblemon_poketotem.CobblemonPokeTotem;
import xyz.station48.common.cobblemon_poketotem.Constants;
import xyz.station48.common.cobblemon_poketotem.permissions.CobblemonPokeTotemPermissions;
import xyz.station48.common.cobblemon_poketotem.util.PokemonUtility;

public class TotemToPokeRedeem implements ICommand {
    public TotemToPokeRedeem() {}

    public void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("totemtopoke-redeem")
                        .requires(src -> CobblemonPokeTotemPermissions.checkPermission(
                                src,
                                CobblemonPokeTotem.permissions.TOTEMTOPOKE_REDEEM_PERMISSION
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

            if (tag.contains(Constants.NBTPokemonDataTag)) {
                target.displayClientMessage(Component.literal("[§c§lCobblemonPokeTotem§f] §c§lYou are holding a Totem that requires the '/totemtopoke' command!"), false);
                return;
            }

            // Check if it has a "pokemon" tag
            if (tag.contains(Constants.NBTCloneDataTag)) {
                CompoundTag pokemonTag = tag.getCompound(Constants.NBTCloneDataTag);
                var registryAccess = target.level().registryAccess();
                Pokemon pokemon = PokemonUtility.clonePokemonNBT(registryAccess, pokemonTag, target);

                // Add to party
                PartyStore party = Cobblemon.INSTANCE.getStorage().getParty(target);
                boolean added = party.add(pokemon);

                if (added) {
                    target.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                    target.displayClientMessage(
                            Component.literal("[§c§lCobblemonPokeTotem§f] §a§lPokémon added to your party or PC!"),
                            false
                    );
                } else {
                    target.displayClientMessage(
                            Component.literal("[§c§lCobblemonPokeTotem§f] §a§lPokémon failed to add!"),
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