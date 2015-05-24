/*
 * LittleInvader.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects.enemies;

import iut.info1.spaceInvadersRebirth.gameStates.LevelState;
import iut.info1.spaceInvadersRebirth.gui.GamePanel;
import iut.info1.spaceInvadersRebirth.res.Resources;

import java.awt.image.BufferedImage;

/**
 * 
 * @author
 * @version dev
 */
public class LittleInvader extends Enemy {

    /**
     * @param levelState
     * @param sprite
     * @throws NullPointerException
     */
    public LittleInvader(LevelState levelState)
    throws NullPointerException {
        super(levelState, Resources.littleInvaderSprite);
        speed = 1;
        
        // Découpage du sprite
        sprite.slice(0, 0, 32, 34);
        sprite.slice(32, 0, 33, 34);
        sprite.slice(65, 0, 33, 34);
    }

    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.ICanShoot#shoot()
     */
    @Override
    public void shoot() {
        
    }

    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.enemies.Enemy#getFrame()
     */
    @Override
    public BufferedImage getFrame() {
        return sprite.getFrameAt(isDead ? 2 : 0);
    }

    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.MovableGameObject#move()
     */
    @Override
    public void move() {
        if (getPosX() <= GamePanel.WIDTH - getWidth() && !movingLeft) {
            movingRight = true;
            movingLeft = false;
            translate(speed, 0);
        } else if (getPosX() >= 0 && !movingRight) {
            movingLeft = true;
            movingRight = false;
            translate(-speed, 0);
        } else {
            movingLeft = movingRight = false;
            translate(0, getWidth() / 5);
        }
    }
}
