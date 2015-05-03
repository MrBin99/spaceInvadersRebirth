/*
 * GameState.java
 */
package iut.info1.spaceInvadersRebirth.gameStates;

import java.awt.Graphics2D;

/**
 * Cette classe représente un état que peut avoir le jeu 
 * (ex : un menu, un mode de jeu, un niveau ...)
 * @author
 * @version dev 0.1
 */
public abstract class GameState {
    
    /** Le GameStateManager qui gère ce GameState. */
    protected GameStateManager gameStateManager;

    /** 
     * Initialise le GameState 
     * (utilisé surtout pour initialiser les niveaux, placer les ennemis ...) 
     */
    public abstract void init();
    
    /**
     * Met à jour le GameState (utilisé pour re-calculer les positions ...)
     */
    public abstract void update();
    
    /**
     * Dessine les mises à jour calculées sur le contexte graphique 
     * passé en argument.
     * @param graphics le contexte graphique sur lequel déssiner.
     */
    public abstract void draw(Graphics2D graphics);
    
    /**
     * Action à effectuer sur le GameState quand 
     * une touche du clavier est appuyée.
     * @param key le code de la touche appuyée.
     */
    public abstract void keyPressed(int key);
    
    /**
     * Action à effectuer sur le GameState quand 
     * une touche du clavier est relachée.
     * @param key le code de la touche relachée.
     */
    public abstract void keyReleased(int key);
}
