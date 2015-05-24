/*
 * Projectile.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects;

import iut.info1.spaceInvadersRebirth.gameStates.LevelState;
import iut.info1.spaceInvadersRebirth.res.Resources;

import java.awt.image.BufferedImage;

/**
 * Classe permettant la création d'un tir du joueur et des enemies.
 * @author
 * @version dev
 */
public class Shot extends MovableGameObject {

    /** True si un projectile du joueur, false si c'est celui d'un enemies. */
    private boolean isPlayerShot;

    /**
     * Construit un tir basique 
     * @param levelState le levelState où on doit dessiner le game object.
     * @param isPlayerShot True si un projectile du joueur, false si c'est celui d'un enemies
     * @param sprite Sprite du game object à afficher.
     * @throws NullPointerException  si levelState == null || sprite == null
     */
    public Shot(LevelState levelState, boolean isPlayerShot)
    throws NullPointerException {
        super(levelState, Resources.playerShotSprite);
        speed = 10;
        this.isPlayerShot = isPlayerShot;
        Resources.playerShoot.stop();
        Resources.playerShoot.play();
    }

    /*
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.MovableGameObject#move()
     */
    @Override
    public void move() {
        // si tir du joueur
        if (isPlayerShot) {
            translate(0, -speed);
        } else {
            // si tir d'un enemies
            translate(0, speed);
        }
    }

    /*
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.GameObject#getFrame()
     */
    @Override
    public BufferedImage getFrame() {
        // retourne le sprite du projectile
        return sprite.getSprite();
    }
}
