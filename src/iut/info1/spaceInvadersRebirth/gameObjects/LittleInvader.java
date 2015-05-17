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
       if( getPosX() <= GamePanel.WIDTH - getWidth() ) {
               movingRight = true;
               translate(speed, 0);
       } else {
           movingRight = false;
           
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
