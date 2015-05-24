/*
 * Resources.java
 */
package iut.info1.spaceInvadersRebirth.res;

import iut.info1.spaceInvadersRebirth.audio.AudioClip;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Classe contenant que des champs et méthodes de classes 
 * permettant de charger et d'accéder aux ressources du jeu
 * (images des sprites, fichiers de son).
 * @author
 * @version dev 0.3
 */
public class Resources {

    /** Le chemin dans les ressources vers l'image d'arrière-plan. */
    public static final String BACKGROUND_PATH = "/HUD/back.png";
    
    /** Le chemin dans les ressources vers l'icône du jeu. */
    public static final String ICON_PATH = "/HUD/icon.png";
    
    /** Le chemin dans les ressources vers l'image du logo du jeu. */
    public static final String LOGO_PATH = "/HUD/logo.png";
    
    /** Le chemin dans les ressources vers la police utilisée dans le jeu. */
    public static final String FONT_PATH = "/HUD/minecraftia.ttf";
    
    /** Le chemin dans les ressources vers le sprite du joueur. */
    public static final String PLAYER_SPRITE_PATH = "/player/player.png";
    
    /** Le chemin dans les ressources vers le sprite des barricades. */
    public static final String SHELTER_SPRITE_PATH = "/shelter/shelters.png";
    
    /** Le chemin dans les ressources vers le sprite du "Little Invader". */
    public static final String LITTLE_INVADER_SPRITE_PATH 
                                = "/enemies/littleInvader.png";
    
    /** Le chemin dans les ressources vers le sprite du "Medium Invader". */
    public static final String MEDIUM_INVADER_SPRITE_PATH 
                                = "/enemies/mediumInvader.png";
    
    /** Le chemin dans les ressources vers le sprite du "Big Invader". */
    public static final String BIG_INVADER_SPRITE_PATH 
                                = "/enemies/bigInvader.png";
    
    /** Le chemin dans les ressources vers le sprite du "Mystery Ship". */
    public static final String MYSTERY_SHIP_SPRITE_PATH 
                                = "/enemies/mysteryShip.png";
    
    /** Le chemin dans les ressources vers le sprite du projectile du joueur.*/
    public static final String PLAYER_SHOT_SPRITE_PATH 
                               = "/player/playerShot.png";
    
    /** Le chemin dans les ressources vers le sprite du projectile des enemis.*/
    public static final String ENEMIES_SHOT_SPRITE_PATH 
                               = "/enemies/enemyShot.png";
    
    /** Le chemin dans les ressources vers le son du projectile du joueur. */
    public static final String PLAYER_SHOT_SOUND_PATH 
                                = "/sounds/sfx/playerShot.mp3";
    
    /** Le chemin dans les ressources vers le son d'explosion d'un élément. */
    public static final String EXPLOSION_SOUND_PATH 
                                = "/sounds/sfx/explosionSound.mp3";
    
    /** Le chemin dans les ressources vers la musique du menu du jeu. */
    public static final String MENU_MUSIC_PATH 
                                = "/sounds/musics/menuMusic.mp3";
    
    /** Le chemin dans les ressources vers la musique du jeu. */
    public static final String GAME_MUSIC_PATH 
                                = "/sounds/musics/gameMusic.mp3";
    
    /** La couleur du texte affiché dans le jeu. */
    public static final Color COLOR_TEXT = Color.WHITE;
    
    /** La couleur du texte affiché et sélectionné dans le jeu. */
    public static final Color COLOR_TEXT_SELECTED = Color.RED;
    
    /** L'arrière-plan du jeu. */
    public static BufferedImage background;
    
    /** L'icône du jeu. */
    public static BufferedImage icon;
    
    /** Le logo du jeu. */
    public static BufferedImage logo;
    
    /** La police de caractère utilisée dans le jeu. */
    public static Font font;
    
    /** Le sprite du joueur. */
    public static BufferedImage playerSprite;
    
    /** Le sprite des barricades. */
    public static BufferedImage shelterSprite;

    /** Le sprite du "Little Invader". */
    public static BufferedImage littleInvaderSprite;
    
    /** Le sprite du "Medium Invader". */
    public static BufferedImage mediumInvaderSprite;
    
    /** Le sprite du "Big Invader". */
    public static BufferedImage bigInvaderSprite;
    
    /** Le sprite du "Mystery Ship". */
    public static BufferedImage mysteryShipSprite;
    
    /** Le sprite du projectile du joueur. */
    public static BufferedImage playerShotSprite;
    
    /** Le sprite du projectile des enemis. */
    public static BufferedImage enemiesShotSprite;
    
    /** La musique du menu du jeu. */
    public static AudioClip menuMusic;
    
    /** La musique du jeu. */
    public static AudioClip gameMusic;
    
    /** Le son du joueur quand il tire. */
    public static AudioClip playerShoot;
    
    /** Le son lors d'une destruction d'un GameObject. */
    public static AudioClip explosionSound;
    
    /** 
     * Charge les ressources du jeu au chemin spécifiés 
     * dans les champs de classes (chaines de caractères) 
     * dans les champs de classes. 
     */
    public static void loadResources() {
        try {
            // Le background
            background = loadImage(BACKGROUND_PATH);
            
            // L'icône
            icon = loadImage(ICON_PATH);
            
            // Le logo
            logo = loadImage(LOGO_PATH);
            
            // La police
            font = loadFont(FONT_PATH);
            
            // Le joueur
            playerSprite = loadImage(PLAYER_SPRITE_PATH);
            
            // Les barricades
            shelterSprite = loadImage(SHELTER_SPRITE_PATH);
            
            // Le little invader
            littleInvaderSprite = loadImage(LITTLE_INVADER_SPRITE_PATH);
            
            // Le medium invader
            mediumInvaderSprite = loadImage(MEDIUM_INVADER_SPRITE_PATH);
            
            // Le big invader
            bigInvaderSprite = loadImage(BIG_INVADER_SPRITE_PATH);
            
            // Le mystery ship
            mysteryShipSprite = loadImage(MYSTERY_SHIP_SPRITE_PATH);
            
            // Le projectile du joueur
            playerShotSprite = loadImage(PLAYER_SHOT_SPRITE_PATH);
            
            // Le projectile des enemis
            enemiesShotSprite = loadImage(ENEMIES_SHOT_SPRITE_PATH);
            
            // Le son du joueur qui tire.
            playerShoot = new AudioClip(AudioClip.loadAudioClip(PLAYER_SHOT_SOUND_PATH));
            playerShoot.setVolume(-5f);
            
            // Le son d'une explosion.
            explosionSound = new AudioClip(AudioClip.loadAudioClip(EXPLOSION_SOUND_PATH));
            explosionSound.setVolume(-5f);
            
            // La musique du menu du jeu.
            menuMusic = new AudioClip(AudioClip.loadAudioClip(MENU_MUSIC_PATH));
            menuMusic.mustLoop(true);
            
            // La musique du jeu.
            gameMusic = new AudioClip(AudioClip.loadAudioClip(GAME_MUSIC_PATH));
            gameMusic.mustLoop(true);

        } catch (IOException e) {
            System.err.println("Impossible de charger la ressource : " 
                               + e.getMessage());
        } catch (FontFormatException e) {
            System.err.println("La police est mauvaise.");
        } catch (NullPointerException e) {
            System.err.println("Mauvais fichier audio.");
        } catch (UnsupportedAudioFileException e) {
            System.err.println("Fichier audio non supporté.");
        } catch (LineUnavailableException e) {
            System.err.println("Fichier non lisable.");
        }
    }
    

    /**
     * Charge une image placée dans le dossier "res" au chemin spécifié
     * en argument.
     * @param path le chemin de l'image dans le dossier "res".
     * @return l'image chargée.
     * @throws IOException si un problème est survenu lors du chargement 
     *                     de l'image ou si elle n'existe pas.
     */
    public static BufferedImage loadImage(String path) throws IOException {
        return ImageIO.read(Resources.class.getResource(path));
    }
    
    
    /**
     * Charge une police de caractère placée dans le dossier "res" 
     * au chemin spécifié en argument.
     * @param path le chemin de la police dans le dossier "res".
     * @return la police chargée.
     * @throws FontFormatException si la police n'est pas une police conforme.
     * @throws IOException si un problème est survenu lors du chargement 
     *                     de la police ou si elle n'existe pas.
     */
    public static Font loadFont(String path) 
    throws FontFormatException, IOException {
        return Font.createFont(Font.TRUETYPE_FONT, 
                               Resources.class.getResourceAsStream(path));
    }
}
