package dev.matthiesen.cobblemon_poketotem.common.utility;

import dev.matthiesen.cobblemon_poketotem.common.CobblemonPokeTotemCommon;
import dev.matthiesen.common.matthiesen_lib_api.utility.RunSlashCommand;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;

public record PokeTotemItemHelper(ItemStack source) {

    public CompoundTag getCustomData() {
        CustomData customData = source.get(DataComponents.CUSTOM_DATA);
        if (customData == null) {
            return null;
        }
        return customData.copyTag();
    }

    public boolean isPokeTotemItem() {
        var tag = getCustomData();
        if (tag == null) return false;
        return tag.contains(CobblemonPokeTotemCommon.NBT.CLONE_DATA_TAG)
                || tag.contains(CobblemonPokeTotemCommon.NBT.POKEMON_DATA_TAG);
    }

    public boolean isCloneTotem() {
        var tag = getCustomData();
        if (tag == null) return false;
        return tag.contains(CobblemonPokeTotemCommon.NBT.CLONE_DATA_TAG) && !tag.contains(CobblemonPokeTotemCommon.NBT.POKEMON_DATA_TAG);
    }

    public boolean isNormalTotem() {
        var tag = getCustomData();
        if (tag == null) return false;
        return !tag.contains(CobblemonPokeTotemCommon.NBT.CLONE_DATA_TAG) && tag.contains(CobblemonPokeTotemCommon.NBT.POKEMON_DATA_TAG);
    }

    public static InteractionResult runInteraction(ServerPlayer player, Level level, InteractionHand hand) {
        var itemStack = player.getItemInHand(hand);
        if (level.isClientSide) return InteractionResult.PASS;

        PokeTotemItemHelper pokeTotemItemHelper = new PokeTotemItemHelper(itemStack);

        if (!pokeTotemItemHelper.isPokeTotemItem()) return InteractionResult.PASS;

        MinecraftServer server = CobblemonPokeTotemCommon.INSTANCE.getMinecraftServer();

        if (pokeTotemItemHelper.isNormalTotem()) {
            RunSlashCommand.asPlayer(server, player, "totemtopoke");
        } else if (pokeTotemItemHelper.isCloneTotem()) {
            RunSlashCommand.asPlayer(server, player, "totemtopoke redeem");
        }
        return InteractionResult.FAIL; // Cancel action
    }
}
