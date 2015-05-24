/*
 * Shelter.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects;

import java.awt.image.BufferedImage;

import iut.info1.spaceInvadersRebirth.gameStates.LevelState;
import iut.info1.spaceInvadersRebirth.res.Resources;

/**
 * 
 * @author
 * @version
 */
public class Shelter extends GameObject {

    /**
     * @param levelState
     * @param sprite
     * @throws NullPointerException
     */
    public Shelter(LevelState levelState)
    throws NullPointerException {
        super(levelState, Resources.shelterSprite);

        // Les barricades ont 4 états d'endomagement
        health = 5;
        
        // Découpage du sprite
        sprite.slice(0, 0, 107, 77);
        sprite.slice(108, 0, 106, 77);
        sprite.slice(215, 0, 107, 77);
        sprite.slice(322, 0, 107, 77);
        sprite.slice(430, 0, 106, 77);
        sprite.slice(537, 0, 107, 77);
    }

    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.GameObject#update()
     */
    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.GameObject#getFrame()
     */
    @Override
    public BufferedImage getFrame() {
        return sprite.getFrameAt(-health + 5);
    }
}
