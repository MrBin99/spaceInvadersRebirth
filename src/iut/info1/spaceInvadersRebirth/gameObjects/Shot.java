/*
 * Shot.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects;

import iut.info1.spaceInvadersRebirth.gameStates.LevelState;
import iut.info1.spaceInvadersRebirth.res.Resources;

import java.awt.image.BufferedImage;

/**
 * Représente un projectile du joueur ou des ennemis.
 * @author
 * @version
 */
public class Shot extends MovableGameObject {
    
    /**
     * True si ce projectile est un tir du joueur,
     * false si c'est un tir de l'ennemi.
     */
    private boolean isPlayerShot;

    /**
     * Construit un nouveau projectile.
     * @param levelState le LevelState ou créer le projectile.
     * @param isPlayerShot True si ce projectile est un tir du joueur,
     *                     false si c'est un tir de l'ennemi.
     * @throws NullPointerException si levelState == null.
     */
    public Shot(LevelState levelState, boolean isPlayerShot)
    throws NullPointerException {
        super(levelState, isPlayerShot ? 
              Resources.playerShotSprite : 
              Resources.enemiesShotSprite);
        
        this.isPlayerShot = isPlayerShot;
        
        // Définis la vitesse des projectiles
        speed = 10;
    }

    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.GameObject#update()
     */
    @Override
    public void update() {
        checkCollision();
        move();
    }
    
    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.MovableGameObject#move()
     */
    @Override
    public void move() {
        translate(0, isPlayerShot ? -speed : speed);
    }
    
    /** 
     * Teste si le projectile entre en collision avec les ennemis 
     * (si c'est un projectile du joueur), avec le joueur 
     * (si c'est un projectile des ennemis) et avec les barricades.<br>
     * Si il y a collision, enlève un point de vie au GameObject concerné 
     * et détruit le projectile. 
     */
    private void checkCollision() {
        checkCollisionWithShelters();
        
        if (isPlayerShot) {
            checkCollisonWithEnemies();
        } else {
            checkCollisonWithPlayer();
        }
        
    }
    /**
     * 
     */
    private void checkCollisonWithPlayer() {
        GameObject player = levelState.getPlayer();
        if (player != null && !player.isDead() 
                && player.getCollisionBox().intersects(getCollisionBox())) {
                
                // Collision !
                // Enlève un point de vie au vaisseau mystère
                player.hit();
                
                Resources.explosionSound.play();

                // Détruit le projectile
                kill();
            }
    }
    
    /**
     * Teste si le projectile du joueur entre en collision avec un ennemi.<br>
     * Si oui, enlève 1 point de vie à l'ennemi et détruit le projectile.
     */
    private void checkCollisonWithEnemies() {
        // Récupère les ennemis
        GameObject[][] enemies = levelState.getEnemyWave().getEnemies();
        GameObject mysteryShip = levelState.getMysteryShip();
        
        // Parcours les barricades
        for (int i = 0 ; i < enemies.length ; i++) {
            for (int j = 0 ; j < enemies[i].length ; j++) {
                // Test de collision
                if (enemies[i][j] != null && !enemies[i][j].isDead()
                    && getCollisionBox().intersects(enemies[i][j].getCollisionBox())) {
                    
                    // Collision !
                    // Enlève un point de vie à l'ennemi concernée
                    enemies[i][j].hit();
                    
                    Resources.explosionSound.play();
    
                    // Détruit le projectile
                    kill();
                }
                
                if (mysteryShip != null && !mysteryShip.isDead() 
                    && mysteryShip.getCollisionBox().intersects(getCollisionBox())) {
                    
                    // Collision !
                    // Enlève un point de vie au vaisseau mystère
                    mysteryShip.hit();
                    
                    Resources.explosionSound.play();
    
                    // Détruit le projectile
                    kill();
                }
            }
        }
    }

    /** 
     * Teste si le projectile entre en collision avec une barricade.<br>
     * Si oui, enlève 1 point de vie à la barricade et détruit le projectile.
     */
    private void checkCollisionWithShelters() {
        // Récupère les barricades
        GameObject[] shelters = levelState.getShelters();
        
        // Parcours les barricades
        for (int i = 0 ; i < shelters.length ; i++) {
            
            // Test de collision
            if (shelters[i] != null && !shelters[i].isDead()
                && getCollisionBox().intersects(shelters[i].getCollisionBox())) {
                
                // Collision !
                // Enlève un point de vie à la barricade concernée
                shelters[i].hit();
                
                Resources.explosionSound.play();

                // Détruit le projectile
                kill();
            }
        }
    }

    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.GameObject#getFrame()
     */
    @Override
    public BufferedImage getFrame() {
        return sprite.getSprite();
    }
}
