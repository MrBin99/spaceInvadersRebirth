/*
 * Shelter.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects;

import iut.info1.spaceInvadersRebirth.graphics.SpriteSheet;
import iut.info1.spaceInvadersRebirth.res.Resources;

import java.awt.image.BufferedImage;

/**
 * Représente une barricade permettant de protéger le joueur des tirs ennemis.
 * @author
 * @version 1.0
 */
public class Shelter extends GameObject {

    /** Créé un nouvelle barricade pour protéger le joueur. */
    public Shelter() {
        super();
        
        // Les barricades ont 4 états d'endomagement
        health = 10;
        
        spriteSheet = new SpriteSheet(Resources.shelterSprite);
        
        // Découpage du sprite
        spriteSheet.slice(0, 0, 89, 65);
        spriteSheet.slice(89, 0, 89, 65);
        spriteSheet.slice(178, 0, 89, 65);
        spriteSheet.slice(267, 0, 89, 65);
        spriteSheet.slice(356, 0, 89, 65);
        spriteSheet.slice(445, 0, 89, 65);
        spriteSheet.slice(534, 0, 89, 65);
        spriteSheet.slice(623, 0, 89, 65);
        spriteSheet.slice(712, 0, 89, 65);
        spriteSheet.slice(801, 0, 89, 65);
    }
    
    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.GameObject#update()
     */
    @Override
    public void update() {
        // UNUSED
    }

    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.GameObject#getFrame()
     */
    @Override
    public BufferedImage getFrame() {
        return spriteSheet.getFrameAt(-health + 10);
    }
}
