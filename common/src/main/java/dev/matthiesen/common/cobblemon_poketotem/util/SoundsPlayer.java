package dev.matthiesen.common.cobblemon_poketotem.util;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;

public final class SoundsPlayer extends dev.matthiesen.common.matthiesen_lib_api.utility.SoundsPlayer {

    /**
     * Default constructor for the SoundsPlayer class. This constructor is used when creating a new instance of the SoundsPlayer class.
     * It takes in a SoundEvent and initializes the sound player with that sound event. The volume and pitch are set to their default values
     * of 1.0F, which means the sound will be played at full volume and normal pitch unless they are changed using the setVolume and setPitch methods.
     *
     * @param sound The SoundEvent that this SoundsPlayer will play when the play method is called. This should be a valid SoundEvent instance that
     *              represents the sound you want to play to the player.
     */
    public SoundsPlayer(SoundEvent sound) {
        super(sound);
    }

    public void playIfMatchesRequirements(ServerPlayer player, boolean bool) {
        if (bool) {
            play(player);
        }
    }
}
