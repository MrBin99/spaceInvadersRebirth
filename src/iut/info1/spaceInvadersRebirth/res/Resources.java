/*
 * Resources.java
 */
package iut.info1.spaceInvadersRebirth.res;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

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
    
    /** Le chemin dans les ressources vers l'image du logo du jeu. */
    public static final String LOGO_PATH = "/HUD/logo.png";
    
    /** Le chemin dans les ressources vers la police utilisée dans le jeu. */
    public static final String FONT_PATH = "/HUD/minecraftia.ttf";
    
    /** Le chemin dans les ressources vers le sprite du joueur. */
    public static final String PLAYER_SPRITE_PATH = "/player/player.png";
    
    /** Le chemin dans les ressources vers le sprite des barricades. */
    public static final String SHELTER_SPRITE_PATH = "/shelter/shelter.png";
    
    /** Le chemin dans les ressources vers le sprite de l'invader. */
    public static final String LITTLE_INVADER_SPRITE_PATH 
                                = "/ennemies/littleInvader.png";
    
    /** Le chemin dans les ressources vers le sprite du projectile du joueur.*/
    public static final String PLAYER_SHOT_SPRITE_PATH 
                               = "/player/playerShot.png";
    
    /** La couleur du texte affiché dans le jeu. */
    public static final Color COLOR_TEXT = Color.WHITE;
    
    /** La couleur du texte affiché et sélectionné dans le jeu. */
    public static final Color COLOR_TEXT_SELECTED = Color.RED;
    
    /** L'arrière-plan du jeu. */
    public static BufferedImage background;
    
    /** Le logo du jeu. */
    public static BufferedImage logo;
    
    /** La police de caractère utilisée dans le jeu. */
    public static Font font;
    
    /** Le sprite du joueur. */
    public static BufferedImage playerSprite;
    
    /** Le sprite des barricades. */
    public static BufferedImage shelterSprite;

    /** Le sprite de l'invader de type 1 */
    public static BufferedImage littleInvaderSprite;
    
    /** Le sprite du projectile */
    public static BufferedImage playerShotSprite; 
    
    /** 
     * Charge les ressources du jeu au chemin spécifiés 
     * dans les champs de classes (chaines de caractères) 
     * dans les champs de classes. 
     */
    public static void loadResources() {
        try {
            // Le background
            background = loadImage(BACKGROUND_PATH);
            
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
            
            // Le projectile du joueur
            playerShotSprite = loadImage(PLAYER_SHOT_SPRITE_PATH);

        } catch (IOException e) {
            System.err.println("Impossible de charger la ressource : " 
                               + e.getMessage());
        } catch (FontFormatException e) {
            System.err.println("La police est mauvaise.");
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
