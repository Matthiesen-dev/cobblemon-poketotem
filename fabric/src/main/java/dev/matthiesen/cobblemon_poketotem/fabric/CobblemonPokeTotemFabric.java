package dev.matthiesen.cobblemon_poketotem.fabric;

import dev.matthiesen.cobblemon_poketotem.common.CobblemonPokeTotemCommon;
import net.fabricmc.api.ModInitializer;

public final class CobblemonPokeTotemFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        var instance = CobblemonPokeTotemCommon.INSTANCE;
        instance.createInfoLog("Loading for Fabric Mod Loader");
        instance.initialize();
    }

}
