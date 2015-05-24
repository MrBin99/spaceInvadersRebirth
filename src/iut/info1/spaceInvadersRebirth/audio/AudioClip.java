/*
 * AudioManager.java
 */
package iut.info1.spaceInvadersRebirth.audio;

import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Repr�sente un clip audio (effet audio ou musique) � jouer � un moment du jeu.
 * @author
 * @version dev
 */
public class AudioClip {

    /** Le clip audio � jouer. */
    private Clip audioClip;

    /** 
     * Construit un nouveau clip audio. 
     * @param clip le clip audio � construire.
     * @throws NullPointerException si clip == null.
     */
    public AudioClip(Clip clip) throws NullPointerException {
        // Precondition
        if (clip == null) {
            throw new NullPointerException();
        }

        audioClip = clip;
    }

    /**
     * Charge un fichier audio contenu dans le dossier des ressources.
     * @param path le chemin dans le dossier des ressources 
     *             du fichier � charger.
     * @return le fichier charg�.
     * @throws UnsupportedAudioFileException si le format du fichier audio 
     *                                       n'est pas pris en charge.
     * @throws IOException si un probl�me est survenu lors 
     *                     du chargement du fichier.
     * @throws LineUnavailableException si le fichier ne peut pas �tre ouvert.
     */
    public static Clip loadAudioClip(String path)
    throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        
        // Charge le fichier
        AudioInputStream ais = AudioSystem.getAudioInputStream(AudioClip.class
                                          .getResourceAsStream(path));
        
        // Sauvegarde le format du fichier
        AudioFormat baseFormat = ais.getFormat();
        
        // Conversion du fichier en un format utilisable
        AudioFormat decodeFormat = new AudioFormat(
                AudioFormat.Encoding.PCM_SIGNED,
                baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
                baseFormat.getChannels() * 2, baseFormat.getSampleRate(),
                false);
        
        // Recharge le nouveau format du fichier
        AudioInputStream dais = AudioSystem.getAudioInputStream(
                decodeFormat, ais);
        
        // Cr�� le clip audio
        Clip clip = AudioSystem.getClip();
        clip.open(dais);

        return clip;
    }

    /** 
     * Stoppe la lecture du fichier audio si il est en lecture 
     * et lit le fichier depuis le d�but. 
     */
    public void play() {
        stop();
        audioClip.setFramePosition(0);
        audioClip.start();
    }

    /**
     * Si le fichier est en train d'�tre lu, stoppe la lecture.
     */
    public void stop() {
        if (audioClip.isRunning()) {
            audioClip.stop();
        }
    }
    
    /**
     * Change le volume de lecture du fichier audio.
     * @param volume le nouveau volume de lecture.
     */
    public void setVolume(float volume) {
        FloatControl gainControl = (FloatControl) audioClip
                .getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(volume);
    }
    
    /**
     * Si le fichier audio doit �tre lu continuellement, 
     * c'est-�-dire que � la fin de sa lecture recommence 
     * la lecture depuis le d�but.
     * @param mustLoop true si le fichier doit �tre lu continuellement, 
     *                 false si il doit �tre lu qu'une seule fois.
     */
    public void mustLoop(boolean mustLoop) {
        if (mustLoop) {
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            audioClip.loop(1);
        }
    }
}
