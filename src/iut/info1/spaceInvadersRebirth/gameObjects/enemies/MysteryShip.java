/*
 * MysteriShip.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects.enemies;

import iut.info1.spaceInvadersRebirth.gameStates.LevelState;
import iut.info1.spaceInvadersRebirth.res.Resources;

import java.awt.image.BufferedImage;

/**
 * Repr�sente un ennemi de type "Mystery Ship".
 * @author
 * @version
 */
public class MysteryShip extends Enemy {

    /**
     * Construit un nouvel ennemi de type "Mystery Ship".
     * @param levelState le LevelState o� construire l'ennemi
     * @throws NullPointerException si levelState == null.
     */
    public MysteryShip(LevelState levelState)
    throws NullPointerException {
        super(levelState, Resources.mysteryShipSprite);
        
        // D�coupage du sprite
        sprite.slice(0, 0, 77, 37);
        sprite.slice(78, 0, 77, 37);
    }
    
    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.MovableGameObject#move()
     */
    @Override
    public void move() {
        // D�placement vers la gauche.
        if (movingLeft) {
            translate(-speed, 0);
            
            // Evite de se d�placer plusieurs fois.
            movingLeft = false;

            // D�placement vers la droite.
        } else if (movingRight) {
            translate(speed, 0);

            // Evite de se d�placer plusieurs fois.
            movingRight = false;
        }
    }

    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.abilities.ICanShoot#shoot()
     */
    @Override
    public void shoot() {
        // TODO Auto-generated method stub

    }

    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.GameObject#update()
     */
    @Override
    public void update() {
        if (getPosX() <= 0 - getWidth()) {
            kill();
        } else {
            setMovingLeft(true);
            move();
        }
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
