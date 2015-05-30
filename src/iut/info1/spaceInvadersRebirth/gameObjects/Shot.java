/*
 * Shot.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects;

import java.awt.image.BufferedImage;

import iut.info1.spaceInvadersRebirth.graphics.SpriteSheet;
import iut.info1.spaceInvadersRebirth.gui.GamePanel;
import iut.info1.spaceInvadersRebirth.res.Resources;

/**
 * Représente un projectile tiré par un ennemi ou le joueur.
 * @author
 * @version 1.0
 */
public class Shot extends MovableGameObject {

    /**
     * True si c'est un projectile du joueur,
     * false si c'est un projectile des ennemis.
     */
    private boolean isPlayerShot;
    
    /** Le GameObject qui a tiré ce projectile. */
    private GameObject shooter;
    
    /**
     * Construit un nouveau projectile du joueur ou des ennemis.
     * @param shooter le GameObject qui a tiré ce projectile.
     * @throws NullPointerException si shooter == null.
     */
    public Shot(GameObject shooter) throws NullPointerException {
        // Precondition
        if (shooter == null) {
            throw new NullPointerException();
        }
        
        // Définis la vitesse
        speed = 10;
        
        // Si c'est un projectile du joueur
        isPlayerShot = shooter instanceof Player;
        
        // Attribution des sprites
        spriteSheet = new SpriteSheet(isPlayerShot ?
                                      Resources.playerShotSprite : 
                                      Resources.enemiesShotSprite);
        
        // Place le projectile devant le GameObject qui l'a tiré
        if (isPlayerShot) {
            translate((int) (shooter.getPosX() + shooter.getWidth() / 2.8f), 
                      shooter.getPosY() - getHeight());
        } else {
            translate(shooter.getPosX() + shooter.getWidth() / 2, 
                      shooter.getPosY() + getHeight());
        }
    }
    
    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.MovableGameObject#update()
     */
    @Override
    public void update() {
        
        // Selon le type du projectile, il monte ou descend.
        if (isPlayerShot) {
            
            // Tue le projectile du joueur s'il arrive en haut de l'écran.
            if (getPosY() <= 50) {
                kill();
            } else {
                setMovingUp(true);
                super.update();
            }
        } else {
            
            // Tue le projectile ennemi s'il arrive en bas de l'écran.
            if (getPosY() >= GamePanel.HEIGHT - getHeight()) {
                kill();
            } else {
                setMovingDown(true);
                super.update();
            }
        }
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
