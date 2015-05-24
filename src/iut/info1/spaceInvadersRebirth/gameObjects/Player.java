/*
 * Player.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import iut.info1.spaceInvadersRebirth.gameObjects.abilities.ICanShoot;
import iut.info1.spaceInvadersRebirth.gameStates.LevelState;
import iut.info1.spaceInvadersRebirth.res.Resources;

/**
 * Représente le joueur du jeu pouvant se déplacer et tirer des projectiles.
 * @author
 * @version
 */
public class Player extends MovableGameObject implements ICanShoot {
    
    /** Les projectiles du joueur. */
    private List<Shot> shots;

    /**
     * Construit un nouveau joueur.
     * @param levelState le LevelState ou créer le joueur.
     * @throws NullPointerException si levelState == null.
     */
    public Player(LevelState levelState)
    throws NullPointerException {
        super(levelState, Resources.playerSprite);
        
        // Créé la liste des projectiles
        shots = new ArrayList<>();
        
        // Définis la vitesse de déplacement du joueur
        speed = 10;
        
        // Découpe le sprite
        sprite.slice(0, 0, 46, 63);
        sprite.slice(46, 0, 46, 63);
    }

    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.abilities.ICanShoot#shoot()
     */
    @Override
    public void shoot() {
        // Le projectile que le joueur tire
        Shot toShoot = new Shot(levelState, true);
        
        // Déplace le projectile au niveau du joueur
        toShoot.translate(getPosX() + getWidth() / 3, 
                          getPosY() - toShoot.getHeight());
        
        // Ajoute le tir à la liste des tirs
        shots.add(toShoot);
    }

    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.GameObject#update()
     */
    @Override
    public void update() {
        move();
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
     * @return les projectiles du joueur.
     */
    public List<Shot> getShots() {
        return this.shots;
    }

    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.GameObject#getFrame()
     */
    @Override
    public BufferedImage getFrame() {
        return sprite.getFrameAt(isDead ? 1 : 0);
    }
}
