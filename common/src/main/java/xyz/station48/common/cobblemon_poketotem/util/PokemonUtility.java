package xyz.station48.common.cobblemon_poketotem.util;

import com.cobblemon.mod.common.api.pokemon.stats.Stats;
import com.cobblemon.mod.common.api.storage.party.PartyStore;
import com.cobblemon.mod.common.item.PokemonItem;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.cobblemon.mod.common.util.LocalizationUtilsKt;
import net.minecraft.ChatFormatting;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import xyz.station48.common.cobblemon_poketotem.Constants;

import java.util.Objects;

public class PokemonUtility {
    public static Pokemon createPokemonFromNBT(RegistryAccess registryAccess, CompoundTag nbt) {
        Pokemon pokemon = new Pokemon();
        return pokemon.loadFromNBT(registryAccess, nbt);
    }

    public static Pokemon clonePokemonNBT(RegistryAccess registryAccess, CompoundTag nbt) {
        Pokemon pokemonToClone = createPokemonFromNBT(registryAccess, nbt);
        return pokemonToClone.clone(true, registryAccess);
    }

    public static void givePlayerPokemonItem(Player player, ItemStack pokemonItem, PartyStore storage, Pokemon pokemon) {
        if (!player.getInventory().add(pokemonItem)) {
            player.drop(pokemonItem, false);
        }
        storage.remove(pokemon);
    }

    public static ItemStack createCustomPokeTotem(Pokemon pokemon, RegistryAccess registryAccess, Integer slot) {
        ItemStack initialItem = pokemonToItem(pokemon);
        CompoundTag pokemonNBT = pokemon.saveToNBT(registryAccess, new CompoundTag());
        CompoundTag customDataTag = new CompoundTag();

        customDataTag.putInt("slot", slot);
        customDataTag.put(Constants.NBTPokemonDataTag, pokemonNBT);
        customDataTag.putString(Constants.NBTStandardFnTag, Constants.NBTStandardFnData);

        CustomData customData = CustomData.of(customDataTag);

        return new ItemBuilder(initialItem).setCustomData(customData).build();
    }

    public static ItemStack pokemonToItem(Pokemon pokemon) {
        String moveOne = !pokemon.getMoveSet().getMoves().isEmpty() ?
                Objects.requireNonNull(pokemon.getMoveSet().get(0)).getDisplayName().getString() : "Empty";
        String moveTwo = pokemon.getMoveSet().getMoves().size() >= 2 ?
                Objects.requireNonNull(pokemon.getMoveSet().get(1)).getDisplayName().getString() : "Empty";
        String moveThree = pokemon.getMoveSet().getMoves().size() >= 3 ?
                Objects.requireNonNull(pokemon.getMoveSet().get(2)).getDisplayName().getString() : "Empty";
        String moveFour = pokemon.getMoveSet().getMoves().size() >= 4 ?
                Objects.requireNonNull(pokemon.getMoveSet().get(3)).getDisplayName().getString() : "Empty";

        ItemStack itemstack;
        itemstack = new ItemBuilder(PokemonItem.from(pokemon, 1))
                .hideAdditional()
                .addLore(new Component[]{
                        Component.literal(pokemon.getCaughtBall().item().getDefaultInstance().getDisplayName().getString())
                                .setStyle(Style.EMPTY.withItalic(true).withColor(ChatFormatting.DARK_GRAY)),
                        Component.literal("Level: ").withStyle(ChatFormatting.AQUA)
                                .append(Component.literal(String.valueOf(pokemon.getLevel())).withStyle(ChatFormatting.WHITE)),
                        Component.literal("Nickname: ").withStyle(ChatFormatting.DARK_GREEN)
                                .append(Component.literal(
                                pokemon.getNickname() != null ? pokemon.getNickname().getString() : "No nickname"
                        ).withStyle(ChatFormatting.WHITE)),
                        Component.literal("Nature: ").withStyle(ChatFormatting.YELLOW)
                                .append(LocalizationUtilsKt.lang(pokemon.getNature().getDisplayName().replace("cobblemon.", ""))
                                .withStyle(ChatFormatting.WHITE)),
                        Component.literal("Ability: ").withStyle(ChatFormatting.GOLD)
                                .append(LocalizationUtilsKt.lang(pokemon.getAbility().getDisplayName().replace("cobblemon.", ""))
                                .withStyle(ChatFormatting.WHITE)),
                        Component.literal("IVs: ").withStyle(ChatFormatting.LIGHT_PURPLE),
                        Component.literal("  HP: ").withStyle(ChatFormatting.RED)
                                .append(Component.literal(String.valueOf(pokemon.getIvs().getOrDefault(Stats.HP)))
                                        .withStyle(ChatFormatting.WHITE))
                                .append(Component.literal("  Atk: ").withStyle(ChatFormatting.BLUE)
                                        .append(Component.literal(String.valueOf(pokemon.getIvs().getOrDefault(Stats.ATTACK)))
                                                .withStyle(ChatFormatting.WHITE)))
                                .append(Component.literal("  Def: ").withStyle(ChatFormatting.GRAY)
                                .append(Component.literal(String.valueOf(pokemon.getIvs().getOrDefault(Stats.DEFENCE)))
                                        .withStyle(ChatFormatting.WHITE))),
                        Component.literal("  SpAtk: ").withStyle(ChatFormatting.AQUA)
                                .append(Component.literal(String.valueOf(pokemon.getIvs().getOrDefault(Stats.SPECIAL_ATTACK)))
                                        .withStyle(ChatFormatting.WHITE))
                                .append(Component.literal("  SpDef: ").withStyle(ChatFormatting.YELLOW)
                                        .append(Component.literal(String.valueOf(pokemon.getIvs().getOrDefault(Stats.SPECIAL_DEFENCE)))
                                                .withStyle(ChatFormatting.WHITE)))
                                .append(Component.literal("  Spd: ").withStyle(ChatFormatting.GREEN)
                                .append(Component.literal(String.valueOf(pokemon.getIvs().getOrDefault(Stats.SPEED)))
                                        .withStyle(ChatFormatting.WHITE))),
                        Component.literal("EVs: ").withStyle(ChatFormatting.DARK_AQUA),
                        Component.literal("  HP: ").withStyle(ChatFormatting.RED)
                                .append(Component.literal(String.valueOf(pokemon.getEvs().getOrDefault(Stats.HP)))
                                        .withStyle(ChatFormatting.WHITE))
                                .append(Component.literal("  Atk: ").withStyle(ChatFormatting.BLUE)
                                        .append(Component.literal(String.valueOf(pokemon.getEvs().getOrDefault(Stats.ATTACK)))
                                                .withStyle(ChatFormatting.WHITE)))
                                .append(Component.literal("  Def: ").withStyle(ChatFormatting.GRAY)
                                .append(Component.literal(String.valueOf(pokemon.getEvs().getOrDefault(Stats.DEFENCE)))
                                        .withStyle(ChatFormatting.WHITE))),
                        Component.literal("  SpAtk: ").withStyle(ChatFormatting.AQUA)
                                .append(Component.literal(String.valueOf(pokemon.getEvs().getOrDefault(Stats.SPECIAL_ATTACK)))
                                        .withStyle(ChatFormatting.WHITE))
                                .append(Component.literal("  SpDef: ").withStyle(ChatFormatting.YELLOW)
                                        .append(Component.literal(String.valueOf(pokemon.getEvs().getOrDefault(Stats.SPECIAL_DEFENCE)))
                                                .withStyle(ChatFormatting.WHITE)))
                                .append(Component.literal("  Spd: ").withStyle(ChatFormatting.GREEN)
                                .append(Component.literal(String.valueOf(pokemon.getEvs().getOrDefault(Stats.SPEED)))
                                        .withStyle(ChatFormatting.WHITE))),
                        Component.literal("Moves: ").withStyle(ChatFormatting.DARK_GREEN),
                        Component.literal(" ").append(Component.literal(moveOne).withStyle(ChatFormatting.WHITE)),
                        Component.literal(" ").append(Component.literal(moveTwo).withStyle(ChatFormatting.WHITE)),
                        Component.literal(" ").append(Component.literal(moveThree).withStyle(ChatFormatting.WHITE)),
                        Component.literal(" ").append(Component.literal(moveFour).withStyle(ChatFormatting.WHITE)),
                        Component.literal("Form: ").withStyle(ChatFormatting.GOLD).append(pokemon.getForm().getName())
                })
                .setCustomName(
                        pokemon.getShiny() ?
                                pokemon.getSpecies().getTranslatedName().copy().withStyle(ChatFormatting.GRAY)
                                .append(Component.literal(" ★").withStyle(ChatFormatting.GOLD)) :
                                pokemon.getSpecies().getTranslatedName().copy().withStyle(ChatFormatting.GRAY)
                )
                .build();

        return itemstack;
    }
}
