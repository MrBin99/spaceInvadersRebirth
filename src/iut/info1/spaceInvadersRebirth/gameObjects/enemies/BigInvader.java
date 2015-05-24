/*
 * BigInvader.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects.enemies;

import iut.info1.spaceInvadersRebirth.gameStates.LevelState;
import iut.info1.spaceInvadersRebirth.res.Resources;

import java.awt.image.BufferedImage;

/**
 * Représente un ennemi de type "Big Invader".
 * @author
 * @version
 */
public class BigInvader extends Enemy {
    
    /**
     * Construit un nouvel ennemi de type "Big Invader".
     * @param levelState le LevelState où construire l'ennemi
     * @throws NullPointerException si levelState == null.
     */
    public BigInvader(LevelState levelState)
    throws NullPointerException {
        super(levelState, Resources.bigInvaderSprite);

        // Découpage du sprite
        sprite.slice(0, 0, 46, 33);
        sprite.slice(46, 0, 46, 33);
        sprite.slice(92, 0, 46, 33);
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
