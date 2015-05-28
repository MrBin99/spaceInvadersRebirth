/*
 * LittleInvader.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects.enemies;

import iut.info1.spaceInvadersRebirth.graphics.SpriteSheet;
import iut.info1.spaceInvadersRebirth.res.Resources;

import java.awt.image.BufferedImage;

/**
 * Représente un ennemi de type "Little Invader".
 * @author
 * @version 1.0
 */
public class LittleInvader extends Enemy {

    /** Le nombre de points que rapporte le "Little Invader" en mourrant. */
    public static final int POINTS_ON_DEATH = 30;
    
    /** Construit un nouvel ennemi de type "Little Invader". */
    public LittleInvader() {
        super();
        
        // Le nombre de points qu'il rapporte en mourrant
        pointsOnDeath = POINTS_ON_DEATH;
        
        // Attribution du sprite
        spriteSheet = new SpriteSheet(Resources.littleInvaderSprite);
    }
    
    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.GameObject#getFrame()
     */
    @Override
    public BufferedImage getFrame() {
        return spriteSheet.getSprite();
    }
}
