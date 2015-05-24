/*
 * Enemy.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects.enemies;

import java.awt.image.BufferedImage;

import iut.info1.spaceInvadersRebirth.gameObjects.MovableGameObject;
import iut.info1.spaceInvadersRebirth.gameObjects.abilities.ICanShoot;
import iut.info1.spaceInvadersRebirth.gameStates.LevelState;

/**
 * Représente un ennemi du joueur pouvant se déplacer et tirer.
 * @author
 * @version
 */
public abstract class Enemy extends MovableGameObject implements ICanShoot {

    /**
     * Construit un nouvel ennemi.
     * @param levelState le LevelState où construire l'ennemi
     * @param sprite l'image complète du sprite de l'ennemi.
     * @throws NullPointerException si levelState == null
     *                              ou si sprite == null.
     */
    public Enemy(LevelState levelState, BufferedImage sprite)
    throws NullPointerException {
        super(levelState, sprite);
    }
}
