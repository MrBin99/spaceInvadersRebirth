/*
 * LittleInvader.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects;

import iut.info1.spaceInvadersRebirth.gameStates.LevelState;
import iut.info1.spaceInvadersRebirth.gui.GamePanel;
import iut.info1.spaceInvadersRebirth.res.Resources;

import java.awt.image.BufferedImage;

/**
 * Permet de gérer les invaders de type 1 (les petits).
 * @author
 * @version dev
 */
public class LittleInvader extends Enemy {
    
    /**
     * @param levelState
     * @throws NullPointerException
     */
    public LittleInvader(LevelState levelState) throws NullPointerException {
        super(levelState, Resources.littleInvaderSprite);
        movingRight = movingLeft = false;
        speed = (int)1.5;
    }

    /**
     * Permet de déplacer le little invader.
     */
    public void move() {
        // Déplacement vers la droite.
        if (getPosX() <= GamePanel.WIDTH - getWidth() && movingLeft == false) {
            movingRight = true;
            movingLeft = false;
            translate(speed, 0);
        } else if (getPosX() >= 0 && movingRight == false) {
            movingRight = false;
            movingLeft = true;
            translate(-speed, 0);

        } else {
            movingRight = movingLeft = false;
            translate(0, 5);
        }
    }
    
    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.Enemy#getFrame()
     */
    @Override
    public BufferedImage getFrame() {
        // Permet d'obtenir le sprite de l'invader.
        return sprite.getSprite();
    }

}
