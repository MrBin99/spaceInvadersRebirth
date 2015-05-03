/*
 * GameState.java
 */
package iut.info1.spaceInvadersRebirth.gameStates;

import java.awt.Graphics2D;

/**
 * Cette classe repr�sente un �tat que peut avoir le jeu 
 * (ex : un menu, un mode de jeu, un niveau ...)
 * @author
 * @version dev 0.1
 */
public abstract class GameState {
    
    /** Le GameStateManager qui g�re ce GameState. */
    protected GameStateManager gameStateManager;

    /** 
     * Initialise le GameState 
     * (utilis� surtout pour initialiser les niveaux, placer les ennemis ...) 
     */
    public abstract void init();
    
    /**
     * Met � jour le GameState (utilis� pour re-calculer les positions ...)
     */
    public abstract void update();
    
    /**
     * Dessine les mises � jour calcul�es sur le contexte graphique 
     * pass� en argument.
     * @param graphics le contexte graphique sur lequel d�ssiner.
     */
    public abstract void draw(Graphics2D graphics);
    
    /**
     * Action � effectuer sur le GameState quand 
     * une touche du clavier est appuy�e.
     * @param key le code de la touche appuy�e.
     */
    public abstract void keyPressed(int key);
    
    /**
     * Action � effectuer sur le GameState quand 
     * une touche du clavier est relach�e.
     * @param key le code de la touche relach�e.
     */
    public abstract void keyReleased(int key);
}
