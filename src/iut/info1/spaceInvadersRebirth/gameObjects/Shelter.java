/*
 * Shelter.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects;

import iut.info1.spaceInvadersRebirth.gameStates.LevelState;
import iut.info1.spaceInvadersRebirth.res.Resources;

import java.awt.image.BufferedImage;

/**
 * Représente les barricades permettant d'aider le joueur se défendre.
 * @author
 * @version dev
 */
public class Shelter extends GameObject {

    /**
     * Construit un nouvelle barricade.
     * @param levelState le levelState ou construire la barricade.
     * @throws NullPointerException si levelState == null.
     */
    public Shelter(LevelState levelState) throws NullPointerException {
        super(levelState, Resources.shelterSprite);

        // Découpage du sprite
        sprite.slice(0, 0, 107, 77);
        //sprite.slice(179, 0, 178, 129);
        //sprite.slice(358, 0, 178, 129);
        //sprite.slice(716, 0, 177, 129);
    }

    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.GameObject#getFrame()
     */
    @Override
    public BufferedImage getFrame() {
        return sprite.getFrameAt(0);
    }
}
