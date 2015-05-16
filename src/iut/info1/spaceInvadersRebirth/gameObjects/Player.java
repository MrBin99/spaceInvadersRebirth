/*
 * Player.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects;

import iut.info1.spaceInvadersRebirth.gameStates.LevelState;
import iut.info1.spaceInvadersRebirth.gui.GamePanel;
import iut.info1.spaceInvadersRebirth.res.Resources;

import java.awt.image.BufferedImage;

/**
 * Classe permettant de gérer le joueur et ses déplacements
 * @author
 * @version dev
 */
public class Player extends GameObject {

    /** Représente un déplacement vers la droite. */
    private boolean movingRight;
    
    /** Représente un déplacement vers la gauche. */
    private boolean movingLeft;
    
    /**
     * @param levelState 
     * @param sprite
     * @throws NullPointerException
     */
    public Player(LevelState levelState) throws NullPointerException {
        super(levelState, Resources.playerSprite);
        movingRight = movingLeft = false;
        speed = 10;
    }

    /**
     * Permet de déplacer le joueur.
     */
    public void move() { 
       // Déplacement vers la gauche. 
       if (movingLeft) {
           if(getPosX() >= 0) {
               translate(- speed, 0);
           }
           // Evite de se déplacer plusieurs fois.
           movingLeft = false;
           
       // Déplacement vers la droite.    
       } else if (movingRight) {
           if( getPosX() <= GamePanel.WIDTH - getWidth() ) {
               translate(speed, 0);
           }
        // Evite de se déplacer plusieurs fois.
           movingRight = false;
       }
    }
    
    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.GameObject#getFrame()
     */
    @Override
    public BufferedImage getFrame() {
        // Permet d'obtenir le sprite du joueur.
        return sprite.getSprite();
    }

    /**
     * Getter sur movingRight
     * @return movingRight
     */
    public boolean isMovingRight() {
        return movingRight;
    }

    /**
     * Setter sur movingRight
     * @param movingRight le movingRight à modifier
     */
    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    /**
     * Getter sur movingLeft
     * @return movingLeft
     */
    public boolean isMovingLeft() {
        return movingLeft;
    }

    /**
     * Setter sur movingLeft
     * @param movingLeft le movingLeft à modifier
     */
    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }
    
}
