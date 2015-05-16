/*
 * GameStateManager.java
 */
package iut.info1.spaceInvadersRebirth.gameStates;

import java.awt.Graphics2D;

/**
 * Classe permettant de gérer les états du jeu (menus, game over et le jeu).
 * Cette classe permet de déterminer à quel moment 
 * afficher les différents états.
 * @author
 * @version dev 0.2
 */
public class GameStateManager {
    
    /** 
     * Seule instance du GameStateManager 
     * dans l'application (Pattern Singleton). 
     */
    private static GameStateManager instance = new GameStateManager();
    
    /** Le nombre de GameStates gérés par le manager. */
    public static final int NBR_GAME_STATES = 4;
    
    /** 
     * Identifiant dans le GameStateManager du menu principal 
     * (indice dans le tableau des GameStates). 
     */
    public static final int ID_MENU_STATE = 0;
    
    /** 
     * Identifiant dans le GameStateManager du jeu
     * (indice dans le tableau des GameStates). 
     */
    public static final int ID_LEVEL_STATE = 1;
    
    /** 
     * Identifiant dans le GameStateManager de l'écran de pause du jeu.
     * (indice dans le tableau des GameStates). 
     */
    public static final int ID_GAME_PAUSED_STATE = 2;
    
    /** 
     * Identifiant dans le GameStateManager de l'écran "Game Over"
     * (indice dans le tableau des GameStates). 
     */
    public static final int ID_GAME_OVER_STATE = 3;
    
    /** Les états possibles du jeu gérés par le manager. */
    private GameState[] gameStates;
    
    /** 
     * L'identifiant du GameState courant chargé 
     * (indice dans le tableau des GameStates). 
     */
    private int currentState;
    
    /**
     * Construit un GameStateManager.<br>
     * Initialise la liste des GameStates et charge le menu principal.
     */
    private GameStateManager() {
        // Initialisation du tableau des GameStates
        gameStates = new GameState[NBR_GAME_STATES];
        
        // Charge le menu principal
        currentState = 0;
        loadState();
    }
    
    /**
     * Instancie le GameState référencé par 
     * son identifiant contenu dans le champ du GameState courant.
     * @see GameStateManager#switchState(int)
     */
    private void loadState() {
        switch (this.currentState) {
            case ID_MENU_STATE:
                // Lance le menu du jeu
                this.gameStates[this.currentState] = new MenuState(this);
                break;
            case ID_LEVEL_STATE:
                //this.gameStates[this.currentState] = new LevelState(this);
                break;
            case ID_GAME_PAUSED_STATE:
                // Chargment du menu pause
                break;
            case ID_GAME_OVER_STATE:
                // Chargment du menu game over
                break;
        }
    }
    
    /** Décharge le GameState courant. */
    private void unloadState() {
        this.gameStates[this.currentState] = null;
    }
    
    /**
     * Change le GameState courant par celui dont l'identifiant 
     * est passé en argument et le charge.
     * @param id l'identifiant du GameState à charger.
     * @throws IllegalArgumentException si id ne correspond 
     *                                  à aucun GameState sauvegardé.
     */
    public void switchState(int id) {
        // Precondition
        if (id < 0 || id >= this.gameStates.length) {
            throw new IllegalArgumentException("Identifiant de "
                                               + "GameState Invalide.");
        }
        
        // On décharge le GameState courant
        unloadState();
        
        // On charge le prochain
        this.currentState = id;
        loadState();
    }
    
    /** Met à jour le GameState courant. */
    public void update() {
        gameStates[currentState].update();
    }
    
    /** 
     * Met à jour l'affichage du jeu pour le GameState courant. 
     * @param graphics le contexte graphique où dessiner les mises à jour. 
     */
    public void draw(Graphics2D graphics) {
        gameStates[currentState].draw(graphics);
    }
    
    /**
     * Action à effectuer sur le GameState courant quand 
     * une touche du clavier est appuyée.
     * @param keyCode le code de la touche appuyée.
     */
    public void keyPressed(int keyCode) {
        gameStates[currentState].keyPressed(keyCode);
    }
    
    /**
     * Action à effectuer sur le GameState courant quand 
     * une touche du clavier est relachée.
     * @param keyCode le code de la touche relachée.
     */
    public void keyReleased(int keyCode) {
        gameStates[currentState].keyReleased(keyCode);
    }
    
    /**
     * @return l'instance du GameStateManager.
     */
    public static GameStateManager getInstance() {
        return instance;
    }
}
