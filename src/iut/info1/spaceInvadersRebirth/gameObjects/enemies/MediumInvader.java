/*
 * MediumInvader.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects.enemies;

import iut.info1.spaceInvadersRebirth.graphics.SpriteSheet;
import iut.info1.spaceInvadersRebirth.res.Resources;

import java.awt.image.BufferedImage;

/**
 * Représente un ennemi de type "Medium Invader".
 * @author
 * @version 1.0
 */
public class MediumInvader extends Enemy {

    /** Le nombre de points que rapporte le "Medium Invader" en mourrant. */
    public static final int POINTS_ON_DEATH = 20;

    /** Construit un nouvel ennemi de type "Medium Invader". */
    public MediumInvader() {
        super();
        
        // Le nombre de points qu'il rapporte en mourrant
        pointsOnDeath = POINTS_ON_DEATH;
        
        // Attribution du sprite
        spriteSheet = new SpriteSheet(Resources.mediumInvaderSprite);
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
