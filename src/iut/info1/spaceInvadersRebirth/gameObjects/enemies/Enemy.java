/*
 * Enemy.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects.enemies;

import iut.info1.spaceInvadersRebirth.gameObjects.ICanShoot;
import iut.info1.spaceInvadersRebirth.gameObjects.MovableGameObject;
import iut.info1.spaceInvadersRebirth.gameStates.LevelState;

import java.awt.image.BufferedImage;

/**
 * Permet de g�rer le comportement des ennemies. 
 * @author
 * @version dev
 */
public abstract class Enemy extends MovableGameObject implements ICanShoot {
    
    /**
     * Cr�� un nouvel ennemi basique.
     * @param levelState le levelState ou cr�er l'ennemi.
     * @param sprite le sprite de l'ennemi.
     * @throws NullPointerException si levelState == null.
     */
    public Enemy(LevelState levelState, BufferedImage sprite)
    throws NullPointerException {
        super(levelState, sprite);
    }

    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.GameObject#getFrame()
     */
    @Override
    public abstract BufferedImage getFrame();
}
