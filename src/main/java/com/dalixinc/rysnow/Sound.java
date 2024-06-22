package com.dalixinc.rysnow;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip;
    URL[] soundURLs = new URL[30];

    public Sound() {

            soundURLs[0] = getClass().getResource("/sound/BlueBoyAdventure.wav");
            soundURLs[1] = getClass().getResource("/sound/coin.wav");
            soundURLs[2] = getClass().getResource("/sound/powerup.wav");
            soundURLs[3] = getClass().getResource("/sound/unlock.wav");
            soundURLs[4] = getClass().getResource("/sound/fanfare.wav");

    }

    public void setFile(int i) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURLs[i]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
