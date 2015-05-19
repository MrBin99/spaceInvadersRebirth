/*
 * MoveableObject.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects;

import iut.info1.spaceInvadersRebirth.gameStates.LevelState;

import java.awt.image.BufferedImage;

/**
 * Repr�sente un GameObject pouvant se d�placer.
 * @author
 * @version dev
 */
public abstract class MovableGameObject extends GameObject implements IMovable {

    /** Si le GameObject doit se d�placer vers la droite. */
    protected boolean movingRight;
    
    /** Si le GameObject doit se d�placer vers la gauche. */
    protected boolean movingLeft;
    
    /**
     * Cr�� un nouveau GameObject d�pla�able.
     * @param levelState le levelState ou cr�er le GameObject.
     * @param sprite le sprite du GameObject.
     * @throws NullPointerException si levelState == null
     *                              ou si sprite == null.
     */
    protected MovableGameObject(LevelState levelState, BufferedImage sprite)
    throws NullPointerException {
        super(levelState, sprite);

        // Au d�but le GameObject ne bouge pas
        movingLeft = movingRight = false;
    }
    
    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.IMoveable#move()
     */
    @Override
    public abstract void move();

    /**
     * @return true si le GameObject doit bouger vers la droite, false sinon.
     */
    public boolean isMovingRight() {
        return this.movingRight;
    }

    /**
     * Si le GameObject doit bouger vers la droite.
     * @param movingRight true si il doit bouger vers la droite, false sinon.
     */
    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    /**
     * @return true si le GameObject doit bouger vers la gauche, false sinon.
     */
    public boolean isMovingLeft() {
        return this.movingLeft;
    }

    /**
     * Si le GameObject doit bouger vers la gauche.
     * @param movingLeft true si il doit bouger vers la gauche, false sinon.
     */
    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }
}
