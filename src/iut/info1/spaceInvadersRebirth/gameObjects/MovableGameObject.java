/*
 * MovableGameObject.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects;

import iut.info1.spaceInvadersRebirth.gameObjects.abilities.IMovable;
import iut.info1.spaceInvadersRebirth.gameStates.LevelState;
import iut.info1.spaceInvadersRebirth.gui.GamePanel;

import java.awt.image.BufferedImage;

/**
 * Repr�sente un GameObject pouvant se d�placer sur le plateau de jeu.
 * @author
 * @version
 */
public abstract class MovableGameObject extends GameObject implements IMovable {

    /** Si le GameObject doit se d�placer vers la droite. */
    protected boolean movingRight;
    
    /** Si le GameObject doit se d�placer vers la gauche. */
    protected boolean movingLeft;
    
    /** La vitesse de d�placement (en pixels / frame) du GameObject. */
    protected int speed;
    
    /**
     * Cr�� un nouveau GameObject d�pla�able � l'�cran.
     * @param levelState le LevelState o� cr�er ce GameObject.
     * @param sprite l'image compl�te du sprite du GameObject.
     * @throws NullPointerException si levelState == null
     *                              ou si sprite == null
     */
    protected MovableGameObject(LevelState levelState, BufferedImage sprite)
    throws NullPointerException {
        super(levelState, sprite);

        // A sa cr�ation le GameObject ne doit pas se d�placer
        movingLeft = movingRight = false;
        
        // Il peut se d�placer pixel par pixel
        speed = 1;
    }
    
    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.IMovable#move()
     */
    @Override
    public void move() {
        // D�placement vers la gauche.
        if (movingLeft) {
            if (getPosX() >= 0) {
                translate(-speed, 0);
            }
            // Evite de se d�placer plusieurs fois.
            movingLeft = false;

            // D�placement vers la droite.
        } else if (movingRight) {
            if (getPosX() <= GamePanel.WIDTH - getWidth()) {
                translate(speed, 0);
            }
            // Evite de se d�placer plusieurs fois.
            movingRight = false;
        }
    }

    /**
     * @return si le GameObject doit se d�placer vers la droite.
     */
    public boolean isMovingRight() {
        return this.movingRight;
    }

    /**
     * Si le GameObject doit se d�placer vers la droite.
     * @param movingRight true s'il doit se d�placer vers la droite, 
     *                    false sinon.
     */
    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    /**
     * @return si le GameObject doit se d�placer vers la gauche.
     */
    public boolean isMovingLeft() {
        return this.movingLeft;
    }

    /**
     * Si le GameObject doit se d�placer vers la gauche.
     * @param movingLeft true s'il doit se d�placer vers la gauche, 
     *                    false sinon.
     */
    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }
    
    /**
     * @return retourne la vitesse de d�placement 
     *         (pixels / frame) du GameObject.
     */
    public int getSpeed() {
        return this.speed;
    }
    
    /**
     * D�finit la vitesse de d�placement du GameObject.
     * @param speed la nouvelle vitesse du GameObject.
     * @throws IllegalArgumentException si speed < 0.
     */
    public void setSpeed(int speed) throws IllegalArgumentException {
        // Precondition
        if (speed < 0 ) {
            throw new IllegalArgumentException("La vitesse d'un GameObject "
                                               + "ne peut �tre n�gative.");
        }
        this.speed = speed;
    }
}
