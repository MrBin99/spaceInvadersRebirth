/*
 * BigInvader.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects.enemies;

import iut.info1.spaceInvadersRebirth.graphics.SpriteSheet;
import iut.info1.spaceInvadersRebirth.res.Resources;

import java.awt.image.BufferedImage;

/**
 * Repr�sente un ennemi de type "Big Invader".
 * @author
 * @version 1.0
 */
public class BigInvader extends Enemy {

    /** Le nombre de points que rapporte le "Big Invader" en mourrant. */
    public static final int POINTS_ON_DEATH = 10;
    
    /** Construit un nouvel ennemi de type "Big Invader". */
    public BigInvader() {
        super();
        
        // Le nombre de points qu'il rapporte en mourrant
        pointsOnDeath = POINTS_ON_DEATH;
        
        // Attribution du sprite
        spriteSheet = new SpriteSheet(Resources.bigInvaderSprite);
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
