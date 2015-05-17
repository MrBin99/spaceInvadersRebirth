/*
 * Player.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects;

import iut.info1.spaceInvadersRebirth.gameStates.LevelState;
import iut.info1.spaceInvadersRebirth.gui.GamePanel;
import iut.info1.spaceInvadersRebirth.res.Resources;

import java.awt.image.BufferedImage;

/**
 * Classe permettant de g�rer le joueur et ses d�placements
 * 
 * @author
 * @version dev
 */
public class Player extends GameObject {

    /** Repr�sente un d�placement vers la droite. */
    private boolean movingRight;

    /** Repr�sente un d�placement vers la gauche. */
    private boolean movingLeft;

    /** Represente le projectile du joueur. */
    private Shot[] shots;

    /** Tir courant du tableau des tirs � instancier pour un nouveau tir. */
    private int currentProjectile;

    /**
     * @param levelState le levelState o� on doit dessiner le game object.
     * @param sprite Sprite du game object � afficher.
     * @throws NullPointerException si levelState == null || sprite == null
     */
    public Player(LevelState levelState) throws NullPointerException {
        super(levelState, Resources.playerSprite);
        movingRight = movingLeft = false;
        speed = 10;
        shots = new Shot[10];
        currentProjectile = 0;
    }

    /**
     * Permet de d�placer le joueur.
     */
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
     * Permet au joueur de tir�
     */
    public void shoot() {
        if (currentProjectile == 9) {
            currentProjectile = 0;
        }
        
        shots[currentProjectile] = new Shot(levelState, true);
        int posX = getPosX() + getWidth() / 2
                - shots[currentProjectile].getWidth() / 2;
        int posY = getPosY()
                - shots[currentProjectile].getHeight();
        shots[currentProjectile].translate(posX, posY);
        currentProjectile++;

    }

    /*
     * (non-Javadoc)
     * 
     * @see iut.info1.spaceInvadersRebirth.gameObjects.GameObject#getFrame()
     */
    @Override
    public BufferedImage getFrame() {
        // Permet d'obtenir le sprite du joueur.
        return sprite.getSprite();
    }

    /**
     * Getter sur movingRight
     * 
     * @return movingRight
     */
    public boolean isMovingRight() {
        return movingRight;
    }

    /**
     * Setter sur movingRight
     * 
     * @param movingRight
     *            le movingRight � modifier
     */
    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    /**
     * Getter sur movingLeft
     * 
     * @return movingLeft
     */
    public boolean isMovingLeft() {
        return movingLeft;
    }

    /**
     * Setter sur movingLeft
     * 
     * @param movingLeft
     *            le movingLeft � modifier
     */
    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    /**
     * Getter sur projectile
     * 
     * @return projectile
     */
    public Shot[] getShots() {
        return shots;
    }

}
