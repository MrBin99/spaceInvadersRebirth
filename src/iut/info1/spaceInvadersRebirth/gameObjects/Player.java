/*
 * Player.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects;

import iut.info1.spaceInvadersRebirth.gameStates.LevelState;
import iut.info1.spaceInvadersRebirth.gui.GamePanel;
import iut.info1.spaceInvadersRebirth.res.Resources;

import java.awt.image.BufferedImage;

/**
 * Classe permettant de gérer le joueur et ses déplacements.
 * @author
 * @version dev
 */
public class Player extends MovableGameObject implements ICanShoot {

    /** Represente le projectile du joueur. */
    private Shot[] shots;

    /** Tir courant du tableau des tirs à instancier pour un nouveau tir. */
    private int currentProjectile;

    /**
     * Créé un nouveau joueur.
     * @param levelState le levelState où on doit dessiner le game object.
     * @param sprite Sprite du game object à afficher.
     * @throws NullPointerException si levelState == null || sprite == null
     */
    public Player(LevelState levelState) throws NullPointerException {
        super(levelState, Resources.playerSprite);
        movingRight = movingLeft = false;
        speed = 10;
        shots = new Shot[10];
        currentProjectile = 0;
        
        // Découpe le sprite
        sprite.slice(0, 0, 46, 63);
        sprite.slice(46, 0, 46, 63);
    }

    /*
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.MovableGameObject#move()
     */
    @Override
    public void move() {
        // Déplacement vers la gauche.
        if (movingLeft) {
            if (getPosX() >= 0) {
                translate(-speed, 0);
            }
            // Evite de se déplacer plusieurs fois.
            movingLeft = false;

            // Déplacement vers la droite.
        } else if (movingRight) {
            if (getPosX() <= GamePanel.WIDTH - getWidth()) {
                translate(speed, 0);
            }
            // Evite de se déplacer plusieurs fois.
            movingRight = false;
        }
    }

    /*
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.ICanShoot#shoot()
     */
    @Override
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
     * @see iut.info1.spaceInvadersRebirth.gameObjects.GameObject#getFrame()
     */
    @Override
    public BufferedImage getFrame() {
        // Permet d'obtenir le sprite du joueur.
        return sprite.getFrameAt(isDead ? 1 : 0);
    }

    /**
     * @return les projectiles du joueur.
     */
    public Shot[] getShots() {
        return shots;
    }
}
