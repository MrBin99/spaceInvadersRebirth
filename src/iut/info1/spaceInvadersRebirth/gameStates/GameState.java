/*
 * GameState.java
 */
package iut.info1.spaceInvadersRebirth.gameStates;

import java.awt.Graphics2D;

/**
 * Repr�sente l'�tat que peut prendre le jeu, 
 * c'est-�-dire les dif�rrents �crans que peut avoir le jeu 
 * (menu, pause, menu princupal, jeu en lui-m�me).
 * @author
 * @version 1.0
 */
public abstract class GameState {

    /** Le GameStateManager qui contr�le ce GameState. */
    protected GameStateManager gameStateManager;
    
    /**
     * Cr�� un nouveau GameState standard en ajoutant � 
     * celui-ci le GameStateManager qui le contr�le.
     * @param gameStateManager le GameStateManager qui contr�le ce GameState.
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
     * (utilis� surtout pour initialiser les niveaux, placer les ennemis ...) 
     */
    public abstract void init();
    
    /** Met � jour le GameState (utilis� pour re-calculer les positions ...) */
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
     * @param keyCode le code de la touche appuy�e.
     */
    public abstract void keyPressed(int keyCode);

    /**
     * Action � effectuer sur le GameState quand 
     * une touche du clavier est relach�e.
     * @param keyCode le code de la touche relach�e.
     */
    public abstract void keyReleased(int keyCode);
}
