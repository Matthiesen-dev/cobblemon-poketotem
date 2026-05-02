package dev.matthiesen.common.cobblemon_poketotem.util;

import com.cobblemon.mod.common.CobblemonItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class MenuUtilities {
    public static final Item BACKGROUND = Items.GRAY_STAINED_GLASS_PANE;
    public static final Item SEPARATOR = CobblemonItems.PC;
    public static final Item POKE_BALL = CobblemonItems.POKE_BALL;
    public static final Item PAGE_PLACEHOLDER = Items.PAPER;
    public static final Item NAV_ITEM = Items.ARROW;

    public static ItemStack getFrameItem() {
        return new ItemBuilder(BACKGROUND)
                .setCustomName(Component.literal(" "))
                .build();
    }

    public static ItemStack getSeparatorItem() {
        return new ItemBuilder(SEPARATOR)
                .hideAdditional()
                .setCustomName(Component.literal("Open PC")
                        .withStyle(style -> style.withColor(ChatFormatting.BLUE))
                )
                .build();
    }

    public static ItemStack getEmptyItem() {
        return new ItemBuilder(POKE_BALL)
                .hideAdditional()
                .setCustomName(Component.literal("Empty")
                        .withStyle(style -> style.withColor(ChatFormatting.GRAY))
                )
                .build();
    }

    public static ItemStack getPageItem(int currentPage, int pageLength) {
        return new ItemBuilder(PAGE_PLACEHOLDER)
                .setCustomName(
                        Component.literal("Page " + currentPage + "/" + pageLength).withStyle(style -> style.withColor(ChatFormatting.GOLD))
                )
                .build();
    }

    public static ItemStack getNavItem(String label) {
        return new ItemBuilder(NAV_ITEM)
                .hideAdditional()
                .setCustomName(
                        Component.literal(label)
                                .withStyle(
                                        style -> style.withColor(ChatFormatting.AQUA)
                                )
                )
                .build();
    }

    public static ItemStack getBackToPartyItem() {
        return new ItemBuilder(POKE_BALL)
                .hideAdditional()
                .setCustomName(
                        Component.literal("Back to party")
                                .withStyle(style -> style.withColor(ChatFormatting.BLUE))
                )
                .build();
    }
}
