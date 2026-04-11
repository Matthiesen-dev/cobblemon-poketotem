package xyz.station48.common.cobblemon_poketotem.util;

import com.cobblemon.mod.common.CobblemonItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class MenuUtilities {
    public static final Item BACKGROUND = Items.GRAY_STAINED_GLASS_PANE;
    public static final Item EMPTY_SLOT = CobblemonItems.POKE_BALL;

    public static final List<Item> MENU_ITEMS = List.of(
            BACKGROUND,
            EMPTY_SLOT
    );

    public static boolean isMenuItem(ItemStack item) {
        AtomicBoolean check = new AtomicBoolean(false);
        MENU_ITEMS.forEach(menuItem -> {
            if (item.is(menuItem)) {
                check.set(true);
            }
        });
        return check.get();
    }

    public static ItemStack getFrameItem() {
        return new ItemBuilder(BACKGROUND)
                .setCustomName(Component.literal(" "))
                .build();
    }

    public static ItemStack getEmptyItem() {
        return new ItemBuilder(EMPTY_SLOT)
                .hideAdditional()
                .setCustomName(Component.literal("Empty")
                        .withStyle(style -> style.withColor(ChatFormatting.GRAY))
                )
                .build();
    }
}
