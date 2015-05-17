/*
 * Enemy.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects;

import iut.info1.spaceInvadersRebirth.gameStates.LevelState;

import java.awt.image.BufferedImage;

/**
 * Permet de g�rer le comportement des ennemies. 
 * @author
 * @version dev
 */
public abstract class Enemy extends GameObject {
    
    /** Repr�sente un d�placement vers la droite. */
    protected boolean movingRight;
    
    /** Repr�sente un d�placement vers la gauche. */
    protected boolean movingLeft;

    /**
     * @param levelState
     * @param sprite
     * @throws NullPointerException
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
