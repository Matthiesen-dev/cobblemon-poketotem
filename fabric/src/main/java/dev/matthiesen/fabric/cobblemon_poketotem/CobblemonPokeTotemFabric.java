package dev.matthiesen.fabric.cobblemon_poketotem;

import dev.matthiesen.common.cobblemon_poketotem.CobblemonPokeTotem;
import net.fabricmc.api.ModInitializer;
import dev.matthiesen.common.cobblemon_poketotem.Constants;

public final class CobblemonPokeTotemFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Constants.createInfoLog("Loading for Fabric Mod Loader");
        CobblemonPokeTotem.initialize();
    }

}
