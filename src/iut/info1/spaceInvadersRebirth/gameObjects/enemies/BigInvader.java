/*
 * BigInvader.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects.enemies;

import iut.info1.spaceInvadersRebirth.gameStates.LevelState;
import iut.info1.spaceInvadersRebirth.res.Resources;

import java.awt.image.BufferedImage;

/**
 * Repr�sente un ennemi de type "Big Invader".
 * @author
 * @version
 */
public class BigInvader extends Enemy {
    
    /**
     * Construit un nouvel ennemi de type "Big Invader".
     * @param levelState le LevelState o� construire l'ennemi
     * @throws NullPointerException si levelState == null.
     */
    public BigInvader(LevelState levelState)
    throws NullPointerException {
        super(levelState, Resources.bigInvaderSprite);

        // D�coupage du sprite
        sprite.slice(0, 0, 46, 33);
        sprite.slice(46, 0, 46, 33);
        sprite.slice(92, 0, 46, 33);
    }

    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.abilities.ICanShoot#shoot()
     */
    @Override
    public void shoot() {
        // TODO Auto-generated method stub
        
    }

    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.GameObject#update()
     */
    @Override
    public void update() {
        // TODO Auto-generated method stub
        
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
