/*
 * Player.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import iut.info1.spaceInvadersRebirth.gameObjects.abilities.ICanShoot;
import iut.info1.spaceInvadersRebirth.graphics.SpriteSheet;
import iut.info1.spaceInvadersRebirth.gui.GamePanel;
import iut.info1.spaceInvadersRebirth.res.Resources;

/**
 * Repr�sente le joueur du jeu pouvant se d�placer et tirer des projectiles.
 * @author
 * @version 1.0
 */
public class Player extends MovableGameObject implements ICanShoot {

    /** La liste des projectiles du joueur. */
    private List<Shot> shots;
    
    /** Construit un nouveau joueur. */
    public Player() {
        super();
        
        // D�finis la vitesse de d�placement du joueur
        speed = 10;
        
        // Attribution du sprite
        spriteSheet = new SpriteSheet(Resources.playerSprite);
        
        // Cr�� la liste des projectiles
        shots = new ArrayList<>();
        
        // Positionne le joueur en bas de l'�cran au milieu.
        translate(GamePanel.WIDTH / 2 - getWidth() / 2,
                  GamePanel.HEIGHT - getHeight());
    }
    
    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.MovableGameObject#update()
     */
    @Override
    public void update() {
        // D�place le joueur
        super.update();
        
        // Met � jour ses projectiles.
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
        // Ajout d'un nouveau projectile � la liste.
        shots.add(new Shot(this));
        
        // Joue le son de tir
        Resources.playerShoot.play();
    }

    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.abilities.ICanShoot#getShots()
     */
    @Override
    public List<Shot> getShots() {
        return shots;
    }

    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.GameObject#getFrame()
     */
    @Override
    public BufferedImage getFrame() {
        return spriteSheet.getSprite();
    }
}
