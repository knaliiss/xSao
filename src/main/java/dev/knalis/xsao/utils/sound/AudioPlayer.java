package dev.knalis.xsao.utils.sound;

import dev.knalis.xsao.interfaces.IAudioPlayer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.IOException;
import java.net.URL;

public class AudioPlayer implements IAudioPlayer {

    final String SOUNDS_PATH = "/dev/knalis/xsao/sounds/";

    @Override
    public void play(String name, float volume) {
        name += ".wav";
        try {
            URL soundFileUrl = getClass().getResource(SOUNDS_PATH + name);
            if (soundFileUrl == null) {
                throw new IOException("Файл звука не найден: " + SOUNDS_PATH + name);
            }

            try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFileUrl)) {
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                setClipVolume(clip, volume);
                clip.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setClipVolume(Clip clip, float volume) {
        if (volume <= 0) return;
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
        gainControl.setValue(dB);
    }
}
