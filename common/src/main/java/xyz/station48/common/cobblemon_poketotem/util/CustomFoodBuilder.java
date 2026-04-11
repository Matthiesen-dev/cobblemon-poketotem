package xyz.station48.common.cobblemon_poketotem.util;

import com.google.common.collect.ImmutableList;
import net.minecraft.world.food.FoodConstants;
import net.minecraft.world.food.FoodProperties;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class CustomFoodBuilder {
    private final ImmutableList.Builder<FoodProperties.PossibleEffect> effects = ImmutableList.builder();

    public @NotNull FoodProperties build() {
        int nutrition = 0;
        float saturationModifier = 0.0f;
        boolean canAlwaysEat = true;
        float eatSeconds = 1000000.0f;
        float f = FoodConstants.saturationByModifier(nutrition, saturationModifier);
        return new FoodProperties(nutrition, f, canAlwaysEat, eatSeconds, Optional.empty(), this.effects.build());
    }
}
