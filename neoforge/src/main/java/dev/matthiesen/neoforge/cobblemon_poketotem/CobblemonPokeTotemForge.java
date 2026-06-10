package dev.matthiesen.neoforge.cobblemon_poketotem;

import dev.matthiesen.common.cobblemon_poketotem.CobblemonPokeTotem;
import dev.matthiesen.common.cobblemon_poketotem.Constants;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public final class CobblemonPokeTotemForge {
    public CobblemonPokeTotemForge() {
        Constants.createInfoLog("Loading for NeoForge Mod Loader");
        CobblemonPokeTotem.initialize();
    }
}
