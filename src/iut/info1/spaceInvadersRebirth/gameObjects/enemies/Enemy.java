/*
 * Enemy.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects.enemies;

import iut.info1.spaceInvadersRebirth.gameObjects.GameObject;
import iut.info1.spaceInvadersRebirth.gameObjects.ICanShoot;
import iut.info1.spaceInvadersRebirth.gameObjects.IMoveable;
import iut.info1.spaceInvadersRebirth.gameStates.LevelState;

import java.awt.image.BufferedImage;

/**
 * Permet de gérer le comportement des ennemies. 
 * @author
 * @version dev
 */
public abstract class Enemy extends GameObject implements IMoveable, ICanShoot {
    
    /** Représente un déplacement vers la droite. */
    protected boolean movingRight;
    
    /** Représente un déplacement vers la gauche. */
    protected boolean movingLeft;

    /**
     * Créé un nouvel ennemi basique.
     * @param levelState le levelState ou créer l'ennemi.
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
