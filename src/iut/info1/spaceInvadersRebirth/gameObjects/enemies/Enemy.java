/*
 * Enemy.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects.enemies;

import iut.info1.spaceInvadersRebirth.gameObjects.MovableGameObject;
import iut.info1.spaceInvadersRebirth.gameObjects.Shot;
import iut.info1.spaceInvadersRebirth.gameObjects.abilities.ICanShoot;
import iut.info1.spaceInvadersRebirth.res.Resources;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente un ennemi du joueur pouvant se déplacer et tirer.
 * @author
 * @version 1.0
 */
public abstract class Enemy extends MovableGameObject implements ICanShoot {
    
    /** Les projectiles des enemies. */
    protected List<Shot> shots;
    
    /** Accorde le droit a un énemie de tirer */
    protected boolean canShoot;
    
    /** Le nombre de points que rapprote l'ennemi en mourrant. */
    protected int pointsOnDeath;
    
    /** 
     * Construit un ennemi basique ne pouvant pas tirer pour l'instant 
     * et rapportant 1 point en mourrant. 
     */
    protected Enemy() {
        super();
        
        // Initialise la liste des projectiles
        shots = new ArrayList<>();
        
        // Rapporte 1 point en mourrant
        pointsOnDeath = 1;
        
        // Un ennemi a une vitesse initiale de 1
        speed = 1;
        
        // Par défaut il ne peut pas tirer
        canShoot = false;
    }

    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.MovableGameObject#update()
     */
    @Override
    public void update() {
        // Bouge l'ennemi
        super.update();
        
        // Met à jour ses projectiles
        for (Shot shot : shots) {
            if (shot != null && !shot.isDead()) {
                shot.update();
            }
        }
    }
    
    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.abilities.ICanShoot#shoot()
     */
    @Override
    public void shoot() {
        // Si il peut tirer ajoute un nouveau projectile à la liste
        if (canShoot) {
            shots.add(new Shot(this));
            
            // Joue le son de tir
            Resources.enemyShoot.play();
        }
    }

    /**
     * @return True si l'ennemi peut tirer, false sinon.
     */
    public boolean canShoot() {
        return this.canShoot;
    }
    
    /**
     * @return le nombre de points que rapprote l'ennemi en mourrant.
     */
    public int getPointsOnDeath() {
        return this.pointsOnDeath;
    }

    /**
     * True si l'ennemi peut tirer, false sinon.
     * @param canShoot True si l'ennemi peut tirer, false sinon.
     */
    public void setCanShoot(boolean canShoot) {
        this.canShoot = canShoot;
    }
    
    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.abilities.ICanShoot#getShots()
     */
    @Override
    public List<Shot> getShots() {
        return shots;
    }
}
