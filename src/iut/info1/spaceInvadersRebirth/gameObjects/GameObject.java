/*
 * GameObject.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects;

import iut.info1.spaceInvadersRebirth.graphics.SpriteSheet;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Repr�sente une entit� statique du jeu (joueur, ennemi, un tir, etc.)
 * @author
 * @version
 */
public abstract class GameObject {

    /** 
     * Les coordonn�es (x, y) du coin sup�rieur gauche 
     * du GameObject � l'�cran. 
     */
    protected Point position;
    
    /** 
     * Les points de vie courant du GameObject.<br> 
     * Si ils sont � 0 le GameObject meurt. 
     */
    protected int health;
    
    /**
     * True si le GameObject est consid�r� comme mort,
     * false si il est encore en vie.
     */
    protected boolean isDead;
    
    /** 
     * Le SpriteSheet du GameObject contenant toutes les images 
     * de celui-ci � afficher sur l'�cran pour 
     * le repr�senter selon son �tat. 
     */
    protected SpriteSheet spriteSheet;
    
    /**
     * Construit un nouveau GameObject � une position de (0, 0)
     * et poss�dant 1 point de vie.
     */
    protected GameObject() {
        position = new Point(0, 0);
        health = 1;
    }
    
    /**
     * Translate le GameObject � la position (x + dx, y + dy).
     * @param dx la distance � translater sur l'axe x.
     * @param dy la distance � translater sur l'axe y.
     */
    public void translate(int dx, int dy) {
        position.translate(dx, dy);
    }
    
    /** Tue le GameObject. */
    public void kill() {
        isDead = true;
    }
    
    /**
     * Inflige 1 point de dommage au GameObject.<br>
     * Si le GameObject poss�de 0 points de vie, alors tue le GameObject.
     */
    public void hit() {
        // Inflige 1 point de dommage
        health--;
        
        // Si ses points de vie sont � 0, le tue.
        if (health == 0) {
            kill();
        }
    }
    
    /** Met � jour le GameObject. */
    public abstract void update();

    /**
     * @return retourne le nombre de points de vie courant du GameObject.
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * @return True si le GameObject est mort, false sinon.
     */
    public boolean isDead() {
        return this.isDead;
    }
    
    /**
     * @return la frame du game object selon son �tat.
     */
    public abstract BufferedImage getFrame();
    
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
