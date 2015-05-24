/*
 * Enemy.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects.enemies;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import iut.info1.spaceInvadersRebirth.gameObjects.MovableGameObject;
import iut.info1.spaceInvadersRebirth.gameObjects.Shot;
import iut.info1.spaceInvadersRebirth.gameObjects.abilities.ICanShoot;
import iut.info1.spaceInvadersRebirth.gameStates.LevelState;

/**
 * Représente un ennemi du joueur pouvant se déplacer et tirer.
 * @author
 * @version
 */
public abstract class Enemy extends MovableGameObject implements ICanShoot {

    
    /** Les projectiles des enemies. */
    private List<Shot> shots;
    
    /** Accorde le droit a un énemie de tirer */
    private boolean canShoot;
    
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
        shots = new ArrayList<>();
    }
    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.abilities.ICanShoot#shoot()
     */
    @Override
    public void shoot() {
        // Le projectile que le joueur tire
        Shot toShoot = new Shot(levelState, false);
        
        // Déplace le projectile au niveau de l'enemy
        toShoot.translate(getPosX() + getWidth()/ 2 - toShoot.getWidth()/2, 
                          getPosY() + getHeight());
        
        // Ajoute le tir à la liste des tirs
        shots.add(toShoot);
    }
    
    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.GameObject#update()
     */
    @Override
    public void update() {

        for (int i = 0 ; i < shots.size() ; i++) {
            if (shots.get(i).isDead() || shots.get(i).getPosY() <= 50) {
                shots.remove(i);
            } else {
                shots.get(i).update();
            }
            
            shots.removeAll(Collections.singleton(null));
        }
        
    }
    
    /**
     * Getter sur canShoot
     * @return canShoot
     */
    public boolean isCanShoot() {
        return canShoot;
    }

    /**
     * Setter sur canShoot
     * @param canShoot le canShoot à modifier
     */
    public void setCanShoot(boolean canShoot) {
        this.canShoot = canShoot;
    }
    
    /**
     * Getter sur shots
     * @return shots
     */
    public List<Shot> getShots() {
        return shots;
    }
}
