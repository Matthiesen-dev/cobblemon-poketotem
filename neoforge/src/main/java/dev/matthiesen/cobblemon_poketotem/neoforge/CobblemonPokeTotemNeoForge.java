package dev.matthiesen.cobblemon_poketotem.neoforge;

import dev.matthiesen.cobblemon_poketotem.common.CobblemonPokeTotemCommon;
import net.neoforged.fml.common.Mod;

@Mod(CobblemonPokeTotemCommon.MOD_ID)
public final class CobblemonPokeTotemNeoForge {
    public CobblemonPokeTotemNeoForge() {
        var instance = CobblemonPokeTotemCommon.INSTANCE;
        instance.createInfoLog("Loading for NeoForge Mod Loader");
        instance.initialize();
    }
}
