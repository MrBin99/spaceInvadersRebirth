/*
 * MediumInvader.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects.enemies;

import iut.info1.spaceInvadersRebirth.gameStates.LevelState;
import iut.info1.spaceInvadersRebirth.res.Resources;

import java.awt.image.BufferedImage;

/**
 * Représente un ennemi de type "Medium Invader".
 * @author
 * @version
 */
public class MediumInvader extends Enemy {
    
    /**
     * Construit un nouvel ennemi de type "Medium Invader".
     * @param levelState le LevelState où construire l'ennemi
     * @throws NullPointerException si levelState == null.
     */
    public MediumInvader(LevelState levelState)
            throws NullPointerException {
        super(levelState, Resources.mediumInvaderSprite);

        // Découpage du sprite
        sprite.slice(0, 0, 42, 32);
        sprite.slice(42, 0, 42, 32);
        sprite.slice(84, 0, 42, 32);
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
