/*
 * SpriteSheet.java
 */
package iut.info1.spaceInvadersRebirth.graphic;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author
 * @version
 */
public class SpriteSheet {

    /** L'image complète du sprite. */
    private BufferedImage sprite;
    
    /** La liste des sous-images du sprite. */
    private List<BufferedImage> frames;

    /**
     * Construit un SpriteSheet à l'aide d'une image complète à découper
     * et initialise une nouvelle liste de "frames" vide.
     * @param sprite l'image complète du SpriteSheet. 
     * @throws NullPointerException si sprite == null.
     */
    public SpriteSheet(BufferedImage sprite) throws NullPointerException {
        // Precondition
        if (sprite == null) {
            throw new NullPointerException();
        }
        this.sprite = sprite;
        
        // Créé la liste de frames
        frames = new ArrayList<>();
    }
    
    /**
     * Découpe une sous-image de l'image principale du sprite 
     * et la place dans la prochaine case libre de la liste des "frames".
     * @param x coordonnée x où commencer la découpe.
     * @param y coordonnée y où commencer la découpe.
     * @param width la largeur de la découpe.
     * @param height la hauteur de la découpe.
     * @throws IllegalArgumentException si les arguments ne correspondent 
     *                                  pas à de bonnes coordonnées de découpes,
     *                                  c'est-à-dire que les coordonnées 
     *                                  soit sont négatives soit elles 
     *                                  dépassent la taille de l'image.
     */
    public void slice(int x, int y, int width, int height) 
    throws IllegalArgumentException {
        // Precondition
        if (x < 0 || y < 0 || x >= sprite.getWidth() || y >= sprite.getHeight()
            || width <= 0 || height <= 0 || width > sprite.getWidth() 
            || height > sprite.getHeight()) {
            throw new IllegalArgumentException("Coordonnées incorrecte de la "
                                               + "frame à découper");
        }
        
        /*
         * Découpe l'image et la range à la prochaine position 
         * dans la liste des frames.
         */
        frames.add(sprite.getSubimage(x, y, width, height));
    }
    
    /**
     * Retourne la "frame" à la position passée en argument.
     * @param id la position de la frame.
     * @return la frame à la position passée en argument.
     */
    public BufferedImage getFrameAt(int id) {
        if (id < 0 || id >= frames.size()) {
            throw new IllegalArgumentException("Aucune frame à cet indice.");
        }
        return frames.get(id);
    }
    
    /**
     * @return l'image complète du sprite.
     */
    public BufferedImage getSprite() {
        return this.sprite;
    }
}
