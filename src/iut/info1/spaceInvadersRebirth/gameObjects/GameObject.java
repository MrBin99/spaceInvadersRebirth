/*
 * GameObject.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects;

import iut.info1.spaceInvadersRebirth.gameStates.LevelState;
import iut.info1.spaceInvadersRebirth.graphic.SpriteSheet;

import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * Représente une entité du jeu (joueur, ennemi, etc.) 
 * @author 
 * @version dev 
 */
public abstract class GameObject {
    
    /** Position du game object. */
    protected Point position;

    /** Vitesse de déplacement du game object. */
    protected int speed;
    
    /** Point de vie du game object. */
    protected int health;
    
    /** Définit si le game object est en vie ou est mort. */
    protected boolean isDead;
    
    /** Le levelState où on doit dessiner le game object. */
    protected LevelState levelState;
    
    /** Sprite du game object à afficher. */
    protected SpriteSheet sprite;
    
    /**
     * 
     * @param levelState Le levelState où on doit dessiner le game object.
     * @param sprite Sprite du game object à afficher.
     * @throws NullPointerException  si levelState == null || sprite == null
     */
    protected GameObject(LevelState levelState, BufferedImage sprite)
    throws NullPointerException {
        // Précondition
        if (levelState == null || sprite == null) {
            throw new NullPointerException();
        }
        this.levelState = levelState;
        this.sprite = new SpriteSheet(sprite);
        this.position = new Point(0,0);
        this.isDead = false;
        this.health = 1;
        this.speed = 0;
    }
    
    /**
     * Translate le game object à la position (x + dx, y + dy).
     * @param dx la distance à translater sur l'axe x.
     * @param dy la distance à translater sur l'axe y.
     */
    public void translate(int dx, int dy) {
        position.translate(dx, dy);
    }
    
    /**
     * @return la frame du game object selon son état.
     */
    public abstract BufferedImage getFrame();
    
    /**
     * @return largeur de la frame courante.
     */
    public int getWidth() {
        return getFrame().getWidth();
    }
    
    /**
     * @return hauteur de la frame courante.
     */
    public int getHeight() {
        return getFrame().getHeight();
    }
    
    /**
     * @return la position du game object sur l'axe x.
     */
    public int getPosX() {
        return position.x;
    }
    
    /**
     * @return la position du game object sur l'axe y.
     */
    public int getPosY() {
        return position.y;
    }
}
