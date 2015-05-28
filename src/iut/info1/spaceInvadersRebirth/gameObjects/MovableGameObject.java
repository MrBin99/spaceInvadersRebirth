/*
 * MovableGameObject.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects;

import iut.info1.spaceInvadersRebirth.gameObjects.abilities.IMovable;
import iut.info1.spaceInvadersRebirth.gui.GamePanel;

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
    
    /** Si le GameObject doit se d�placer vers le haut. */
    protected boolean movingUp;
    
    /** Si le GameObject doit se d�placer vers le bas. */
    protected boolean movingDown;
    
    /** La vitesse de d�placement (en pixels / frame) du GameObject. */
    protected int speed;
    
    /** 
     * Cr�� un nouveau GameObject d�pla�able � l'�cran, 
     * avec une vitesse de d�placement de 1 pixels / frame. 
     */
    protected MovableGameObject() {
        super();
        
        // Au d�but l'objet ne se d�place pas
        movingDown = movingUp = movingLeft = movingRight = false;
        
        // Met la vitesse � 1
        speed = 1;
    }
    
    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.GameObject#update()
     */
    @Override
    public void update() {
        move();
    }
    
    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.abilities.IMovable#move()
     */
    @Override
    public void move() {
        // D�placement vers la gauche.
        if (movingLeft && getPosX() >= 0) {
            translate(-speed, 0);
            
            // Evite de se d�placer plusieurs fois.
            movingLeft = false;

        // D�placement vers la droite.
        } else if (movingRight && getPosX() <= GamePanel.WIDTH - getWidth()) {
            translate(speed, 0);
            
            // Evite de se d�placer plusieurs fois.
            movingRight = false;
            
        // D�placement vers le bas
        } else if (movingDown && getPosY() <= GamePanel.HEIGHT - getHeight()) {
            translate(0, speed);
            
            // Evite de se d�placer plusieurs fois.
            movingDown = false;
            
        // D�placement vers le haut
        } else if (movingUp && getPosY() >= 50) {
            translate(0, -speed);
            
            // Evite de se d�placer plusieurs fois.
            movingUp = false;
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
     * @return si le GameObject doit se d�placer vers le haut.
     */
    public boolean isMovingUp() {
        return this.movingUp;
    }

    /**
     * Si le GameObject doit se d�placer vers le haut.
     * @param movingUp true s'il doit se d�placer vers le haut, 
     *                    false sinon.
     */
    public void setMovingUp(boolean movingUp) {
        this.movingUp = movingUp;
    }

    /**
     * @return si le GameObject doit se d�placer vers le bas.
     */
    public boolean isMovingDown() {
        return this.movingDown;
    }

    /**
     * Si le GameObject doit se d�placer vers le bas.
     * @param movingDown true s'il doit se d�placer vers le bas, 
     *                    false sinon.
     */
    public void setMovingDown(boolean movingDown) {
        this.movingDown = movingDown;
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
     * @throws IllegalArgumentException si <code>speed < 0</code>.
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
