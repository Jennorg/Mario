package game.manager;

import javafx.scene.media.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class Reproductor {
    private Map<String, AudioClip> audioClips;

    public Reproductor() {
        audioClips = new HashMap<>();
    }

    public void play(String nombreArchivo) {
        AudioClip audioClip = audioClips.get(nombreArchivo);
        if (audioClip != null) {
            audioClip.play();
        }
    }

    public void openFile(String nombreArchivo, String ruta) {
        try {
            File file = new File(ruta);
            String mediaUrl = file.toURI().toURL().toString();
            AudioClip audioClip = new AudioClip(mediaUrl);
            audioClips.put(nombreArchivo, audioClip);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void stop(String nombreArchivo) {
        AudioClip audioClip = audioClips.get(nombreArchivo);
        if (audioClip != null) {
            audioClip.stop();
            audioClips.remove(nombreArchivo);
        }
    }

    public void stopAll() {
        for (AudioClip audioClip : audioClips.values()) {
            audioClip.stop();
        }
        audioClips.clear();
    }
}
