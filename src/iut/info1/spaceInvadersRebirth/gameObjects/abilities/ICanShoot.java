/*
 * ICanShoot.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects.abilities;

import iut.info1.spaceInvadersRebirth.gameObjects.Shot;

import java.util.List;

/**
 * Définit si un GameObject peut tirer des projectiles.
 * @author
 * @version 1.0
 */
public interface ICanShoot {

    /** Fait tirer un projectile au GameObject. */
    public void shoot();
    
    /**
     * Retourne la liste des projectiles du GameObject.
     * @return la liste des projectiles du GameObject.
     */
    public List<Shot> getShots();
}
