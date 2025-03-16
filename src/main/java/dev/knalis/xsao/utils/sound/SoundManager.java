package dev.knalis.xsao.utils.sound;

import dev.knalis.xsao.interfaces.ISound;
import lombok.Getter;

@Getter
public class SoundManager implements ISound {
    private float volume = 1f;
    private static SoundManager instance;
    AudioPlayer player = new AudioPlayer();

    public static SoundManager getInstance() {
        if (instance == null) instance = new SoundManager();
        return instance;
    }

    @Override
    public void playSound(String sound) {
        if (sound == null) throw new IllegalArgumentException("Звук не установлен.");
        player.play(sound, volume);
    }

    @Override
    public void setVolume(float volume) {
        this.volume = volume;
    }


}
