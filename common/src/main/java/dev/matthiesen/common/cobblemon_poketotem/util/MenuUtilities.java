package dev.matthiesen.common.cobblemon_poketotem.util;

import com.cobblemon.mod.common.CobblemonItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import dev.matthiesen.common.matthiesen_lib_api.utility.ItemBuilder;

public final class MenuUtilities {
    public static final Item BACKGROUND = Items.GRAY_STAINED_GLASS_PANE;
    public static final Item SEPARATOR = CobblemonItems.PC;
    public static final Item POKE_BALL = CobblemonItems.POKE_BALL;
    public static final Item PAGE_PLACEHOLDER = Items.PAPER;
    public static final Item NAV_ITEM = Items.ARROW;

    private static ItemStack builder(Item item, Component name) {
        return new ItemBuilder(item)
                .hideAdditional()
                .setCustomName(name)
                .build();
    }

    public static ItemStack getFrameItem() {
        return builder(BACKGROUND, Component.literal(" "));
    }

    public static ItemStack getSeparatorItem() {
        return builder(SEPARATOR, Component.literal("Open PC")
                .withStyle(style -> style.withColor(ChatFormatting.BLUE)));
    }

    public static ItemStack getEmptyItem() {
        return builder(POKE_BALL, Component.literal("Empty Slot")
                .withStyle(style -> style.withColor(ChatFormatting.GRAY)));
    }

    public static ItemStack getPageItem(int currentPage, int pageLength) {
        return builder(PAGE_PLACEHOLDER, Component.literal("Page " + currentPage + "/" + pageLength)
                .withStyle(style -> style.withColor(ChatFormatting.GOLD)));
    }

    public static ItemStack getNavItem(String label) {
        return builder(NAV_ITEM, Component.literal(label)
                .withStyle(style -> style.withColor(ChatFormatting.AQUA)));
    }

    public static ItemStack getBackToPartyItem() {
        return builder(POKE_BALL, Component.literal("Back to party")
                .withStyle(style -> style.withColor(ChatFormatting.BLUE)));
    }
}
