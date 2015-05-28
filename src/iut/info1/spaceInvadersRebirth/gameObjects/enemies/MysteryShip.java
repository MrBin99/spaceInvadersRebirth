/*
 * MysteryShip.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects.enemies;

import iut.info1.spaceInvadersRebirth.graphics.SpriteSheet;
import iut.info1.spaceInvadersRebirth.gui.GamePanel;
import iut.info1.spaceInvadersRebirth.res.Resources;

import java.awt.image.BufferedImage;

/**
 * Représente un ennemi de type "Mystery Ship".
 * @author
 * @version
 */
public class MysteryShip extends Enemy {

    /** Le nombre de points que rapporte le "Big Invader" en mourrant. */
    public static final int[] POINTS_ON_DEATH = {
        50, 100, 150, 300
    };
    
    /** Si le "Mystery Ship" doit se déplacer de la gauche vers la droite. */
    private boolean toRight;
    
    /** Si le "Mystery Ship" doit se déplacer de la droite vers la gauche. */
    private boolean toLeft;

    /** Construit un nouvel ennemi de type "Mystery Ship". */
    public MysteryShip() {
        super();
        
        // Le nombre de points qu'il rapporte en mourrant
        pointsOnDeath = POINTS_ON_DEATH[(int) (Math.random() * 4)];
        
        // Attribution du sprite
        spriteSheet = new SpriteSheet(Resources.mysteryShipSprite);
        
        speed = 2;
        
        /*
         * Défini aléatoirement si le Mystery Ship va de la droite 
         * vers la gauche ou l'inverse.
         */
        toRight = Math.random() > 0.5f;
        toLeft = !toRight;
        
        if (toRight) {
            translate(0 - getWidth(), 70);
            
        // else if (toLeft) {
        } else {
            translate(GamePanel.WIDTH + getWidth(), 70);
        }
    }
    
    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.enemies.Enemy#update()
     */
    @Override
    public void update() {
        
        setMovingLeft(toLeft);
        setMovingRight(toRight);
        
        move();
    }
    
    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.MovableGameObject#move()
     */
    @Override
    public void move() {
        // Si il se déplace vers la droite
        if (movingRight) {
            translate(speed, 0);
            
        // Si il se déplace vers la gauche
        } else if (movingLeft) {
            translate(-speed, 0);
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
    
    /**
     * @return si le "Mystery Ship" va de gauche à droite.
     */
    public boolean isGoingToRight() {
        return this.toRight;
    }

    /**
     * @return si le "Mystery Ship" va de droite à gauche.
     */
    public boolean isGoingToLeft() {
        return this.toLeft;
    }
}
