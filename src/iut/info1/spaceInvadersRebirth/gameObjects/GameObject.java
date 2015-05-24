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
 * Représente une entité du jeu (joueur, ennemi, un tir, etc.) 
 * @author
 * @version
 */
public abstract class GameObject {

    /** Position du game object. */
    protected Point position;
    
    /** Point de vie du game object. */
    protected int health;
    
    /** Définit si le game object est en vie ou est mort. */
    protected boolean isDead;
    
    /** Le levelState où on doit dessiner le game object. */
    protected LevelState levelState;
    
    /** Sprite du game object à afficher. */
    protected SpriteSheet sprite;
    
    /**
     * Créé un nouveau GameObject basique.
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
        
        // A ça création le GameObject n'est pas mort
        this.isDead = false;
        
        // Il meurt avec 1 coups
        this.health = 1;
    }
    
    /**
     * Détermine le rectangle autour du GameObject permettant 
     * de détecter les collisions entre GameObject.
     * @return le rectangle autour du GameObject 
     *         permettant de déterminer sa "collision box".
     */
    public Rectangle getCollisionBox() {
        return new Rectangle(getPosX(), getPosY(), getWidth(), getHeight());
    }
    
    /**
     * Translate le game object à la position (x + dx, y + dy).
     * @param dx la distance à translater sur l'axe x.
     * @param dy la distance à translater sur l'axe y.
     */
    public void translate(int dx, int dy) {
        position.translate(dx, dy);
    }
    
    /** Tue ou détruit le GameObject. */
    public void kill() {
        isDead = true;
    }
    
    /** Met à jour le GameObject toute les "frames" de jeu. */
    public abstract void update();
    
    /**
     * @return true si le GameObject est mort ou détruit, false sinon.
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
     * Inflige un point de dégât au GameObject 
     * et tue le celui-ci s'il n'a plus de points de vie. *
     */
    public void hit() {
        // 1 point de dégât
        health--;
        
        // Si la vie est égale à 0, tue le GameObject
        if (health == 0) {
            kill();
        }
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
