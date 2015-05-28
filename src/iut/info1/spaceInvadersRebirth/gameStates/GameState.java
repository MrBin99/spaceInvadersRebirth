/*
 * GameState.java
 */
package iut.info1.spaceInvadersRebirth.gameStates;

import java.awt.Graphics2D;

/**
 * Représente l'état que peut prendre le jeu, 
 * c'est-à-dire les diférrents écrans que peut avoir le jeu 
 * (menu, pause, menu princupal, jeu en lui-même).
 * @author
 * @version 1.0
 */
public abstract class GameState {

    /** Le GameStateManager qui contrôle ce GameState. */
    protected GameStateManager gameStateManager;
    
    /**
     * Créé un nouveau GameState standard en ajoutant à 
     * celui-ci le GameStateManager qui le contrôle.
     * @param gameStateManager le GameStateManager qui contrôle ce GameState.
     * @throws NullPointerException si <code>gameStateManager == null</code>.
     */
    protected GameState(GameStateManager gameStateManager) 
    throws NullPointerException {
        
        // Precondition
        if (gameStateManager == null) {
            throw new NullPointerException("Le GameStateManager == null");
        }
        this.gameStateManager = gameStateManager;
    }
    
    /** 
     * Initialise le GameState 
     * (utilisé surtout pour initialiser les niveaux, placer les ennemis ...) 
     */
    public abstract void init();
    
    /** Met à jour le GameState (utilisé pour re-calculer les positions ...) */
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
     * @param keyCode le code de la touche appuyée.
     */
    public abstract void keyPressed(int keyCode);

    /**
     * Action à effectuer sur le GameState quand 
     * une touche du clavier est relachée.
     * @param keyCode le code de la touche relachée.
     */
    public abstract void keyReleased(int keyCode);
}
