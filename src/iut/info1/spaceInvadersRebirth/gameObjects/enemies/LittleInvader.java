/*
 * LittleInvader.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects.enemies;

import iut.info1.spaceInvadersRebirth.gameStates.LevelState;
import iut.info1.spaceInvadersRebirth.res.Resources;

import java.awt.image.BufferedImage;

/**
 * Représente un ennemi de type "Little Invader".
 * @author
 * @version
 */
public class LittleInvader extends Enemy {
    
    /**
     * Construit un nouvel ennemi de type "Little Invader".
     * @param levelState le LevelState où construire l'ennemi
     * @throws NullPointerException si levelState == null.
     */
    public LittleInvader(LevelState levelState)
    throws NullPointerException {
        super(levelState, Resources.littleInvaderSprite);
        
        // Découpage du sprite
        sprite.slice(0, 0, 32, 34);
        sprite.slice(32, 0, 33, 34);
        sprite.slice(65, 0, 33, 34);
    }


    

    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.GameObject#getFrame()
     */
    @Override
    public BufferedImage getFrame() {
        return sprite.getFrameAt(isDead ? 2 : 0);
    }
}
