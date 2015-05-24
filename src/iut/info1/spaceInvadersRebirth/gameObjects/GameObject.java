/*
 * GameObject.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects;

import iut.info1.spaceInvadersRebirth.gameStates.LevelState;
import iut.info1.spaceInvadersRebirth.graphics.SpriteSheet;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Repr�sente une entit� du jeu (joueur, ennemi, un tir, etc.) 
 * @author
 * @version
 */
public abstract class GameObject {

    /** Position du game object. */
    protected Point position;
    
    /** Point de vie du game object. */
    protected int health;
    
    /** D�finit si le game object est en vie ou est mort. */
    protected boolean isDead;
    
    /** Le levelState o� on doit dessiner le game object. */
    protected LevelState levelState;
    
    /** Sprite du game object � afficher. */
    protected SpriteSheet sprite;
    
    /**
     * Cr�� un nouveau GameObject basique.
     * @param levelState Le levelState o� on doit dessiner le game object.
     * @param sprite Sprite du game object � afficher.
     * @throws NullPointerException  si levelState == null || sprite == null
     */
    protected GameObject(LevelState levelState, BufferedImage sprite)
    throws NullPointerException {
        // Pr�condition
        if (levelState == null || sprite == null) {
            throw new NullPointerException();
        }
        this.levelState = levelState;
        this.sprite = new SpriteSheet(sprite);
        this.position = new Point(0,0);
        
        // A �a cr�ation le GameObject n'est pas mort
        this.isDead = false;
        
        // Il meurt avec 1 coups
        this.health = 1;
    }
    
    /**
     * D�termine le rectangle autour du GameObject permettant 
     * de d�tecter les collisions entre GameObject.
     * @return le rectangle autour du GameObject 
     *         permettant de d�terminer sa "collision box".
     */
    public Rectangle getCollisionBox() {
        return new Rectangle(getPosX(), getPosY(), getWidth(), getHeight());
    }
    
    /**
     * Translate le game object � la position (x + dx, y + dy).
     * @param dx la distance � translater sur l'axe x.
     * @param dy la distance � translater sur l'axe y.
     */
    public void translate(int dx, int dy) {
        position.translate(dx, dy);
    }
    
    /** Tue ou d�truit le GameObject. */
    public void kill() {
        isDead = true;
    }
    
    /** Met � jour le GameObject toute les "frames" de jeu. */
    public abstract void update();
    
    /**
     * @return true si le GameObject est mort ou d�truit, false sinon.
     */
    public boolean isDead() {
        return this.isDead;
    }
    
    /**
     * @return le nombre de points de vie qu'il reste au joueur.
     */
    public int getHealth() {
        return this.health;
    }
    
    /** 
     * Inflige un point de d�g�t au GameObject 
     * et tue le celui-ci s'il n'a plus de points de vie. *
     */
    public void hit() {
        // 1 point de d�g�t
        health--;
        
        // Si la vie est �gale � 0, tue le GameObject
        if (health == 0) {
            kill();
        }
    }

    /**
     * @return la frame du game object selon son �tat.
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
