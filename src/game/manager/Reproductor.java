package game.manager;

import javafx.scene.media.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

public class Reproductor {
    private AudioClip audioClip;

    public Reproductor() {
        audioClip = null;
    }

    public void play() {
        if (audioClip != null) {
            audioClip.play();
        }
    }

    public void openFile(String path) {
        try {
            File file = new File(path);
            String mediaUrl = file.toURI().toURL().toString();
            audioClip = new AudioClip(mediaUrl);
        } catch (MalformedURLException e) {
            e.getStackTrace();
        }
    }

    public void resume() {
        if (audioClip != null) {
            audioClip.play();
        }
    }

    public void stop() {
        if (audioClip != null) {
            audioClip.stop();
            audioClip = null;
        }
    }
}