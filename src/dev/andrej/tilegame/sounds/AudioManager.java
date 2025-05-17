package dev.andrej.tilegame.sounds;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AudioManager {
    public static final String TREE_HIT = "treeHit";
    public static final String TREE_CHOPPED = "treeChopped";

    private static AudioManager instance;
    private Map<String, Clip> sounds = new HashMap<>();

    public AudioManager() {
        loadAllSoundEffects();
    }

    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }

    private void loadAllSoundEffects() {
        loadSound(new File("res/sounds/effects/tree_hit.wav").getAbsolutePath(), TREE_HIT);
        loadSound(new File("res/sounds/effects/tree_chopped.wav").getAbsolutePath(), TREE_CHOPPED);
        // ... load other sounds
    }

    public void playSound(String soundKey) {
        Clip clip = sounds.get(soundKey);
        if (clip != null) {
            // Rewind the clip to the beginning if it's already playing or has finished
            clip.setFramePosition(0);
            clip.start();
        } else {
            System.err.println("Sound not loaded: " + soundKey);
        }
    }

    private void loadSound(String filePath, String soundKey) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            sounds.put(soundKey, clip);
            System.out.println("Loaded sound: " + soundKey + " from " + filePath);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error loading sound: " + soundKey + " from " + filePath + " - " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void stopSound(String soundKey) {
        Clip clip = sounds.get(soundKey);
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.setFramePosition(0); // Optionally rewind when stopped
        }
    }

    // Optional: Method to unload a sound to free up memory
    public void unloadSound(String soundKey) {
        Clip clip = sounds.remove(soundKey);
        if (clip != null) {
            clip.close();
            System.out.println("Unloaded sound: " + soundKey);
        }
    }
}
