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

    /** L'image compl�te du sprite. */
    private BufferedImage sprite;
    
    /** La liste des sous-images du sprite. */
    private List<BufferedImage> frames;

    /**
     * Construit un SpriteSheet � l'aide d'une image compl�te � d�couper
     * et initialise une nouvelle liste de "frames" vide.
     * @param sprite l'image compl�te du SpriteSheet. 
     * @throws NullPointerException si sprite == null.
     */
    public SpriteSheet(BufferedImage sprite) throws NullPointerException {
        // Precondition
        if (sprite == null) {
            throw new NullPointerException();
        }
        this.sprite = sprite;
        
        // Cr�� la liste de frames
        frames = new ArrayList<>();
    }
    
    /**
     * D�coupe une sous-image de l'image principale du sprite 
     * et la place dans la prochaine case libre de la liste des "frames".
     * @param x coordonn�e x o� commencer la d�coupe.
     * @param y coordonn�e y o� commencer la d�coupe.
     * @param width la largeur de la d�coupe.
     * @param height la hauteur de la d�coupe.
     * @throws IllegalArgumentException si les arguments ne correspondent 
     *                                  pas � de bonnes coordonn�es de d�coupes,
     *                                  c'est-�-dire que les coordonn�es 
     *                                  soit sont n�gatives soit elles 
     *                                  d�passent la taille de l'image.
     */
    public void slice(int x, int y, int width, int height) 
    throws IllegalArgumentException {
        // Precondition
        if (x < 0 || y < 0 || x >= sprite.getWidth() || y >= sprite.getHeight()
            || width <= 0 || height <= 0 || width > sprite.getWidth() 
            || height > sprite.getHeight()) {
            throw new IllegalArgumentException("Coordonn�es incorrecte de la "
                                               + "frame � d�couper");
        }
        
        /*
         * D�coupe l'image et la range � la prochaine position 
         * dans la liste des frames.
         */
        frames.add(sprite.getSubimage(x, y, width, height));
    }
    
    /**
     * Retourne la "frame" � la position pass�e en argument.
     * @param id la position de la frame.
     * @return la frame � la position pass�e en argument.
     */
    public BufferedImage getFrameAt(int id) {
        if (id < 0 || id >= frames.size()) {
            throw new IllegalArgumentException("Aucune frame � cet indice.");
        }
        return frames.get(id);
    }
    
    /**
     * @return l'image compl�te du sprite.
     */
    public BufferedImage getSprite() {
        return this.sprite;
    }
}
