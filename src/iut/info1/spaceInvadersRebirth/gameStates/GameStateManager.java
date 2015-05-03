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
 * @version dev 0.1
 */
public class GameStateManager {
    
    /** Seule instance du GameStateManager (pattern Singleton). */
    private static GameStateManager instance = new GameStateManager();
    
    /** Les états du jeu gérés par le manager. */
    private GameState[] gameStates;
    
    /** L'identifiant du GameState courant chargé. */
    private int currentState;
    
    /** Le nombre de GameStates gérés par le manager. */
    public static final int NBR_GAME_STATES = 3;
    
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
     * Identifiant dans le GameStateManager de l'écran "Game Over"
     * (indice dans le tableau des GameStates). 
     */
    public static final int ID_GAME_OVER_STATE = 2;
    
    /**
     * Constructeur du GameStateManager.<br>
     * Initialise la liste des GameStates et charge le menu principal.
     */
    private GameStateManager() {
        // Initialisation de la liste des GameStates
        this.gameStates = new GameState[NBR_GAME_STATES];
        
        // On charge le menu
        this.currentState = 0;
        loadState();
    }
    
    /**
     * Instancie le GameState référencé par 
     * son identifiant contenu dans le champ du GameState courant.
     * @see GameStateManager#switchState(int)
     */
    private void loadState() {
        switch (this.currentState) {
            case 0:
                // Chargement du menu
                break;
            case 1:
                // Chargement du level
                break;
            case 2:
                break;
        }
    }
    
    /**
     * Décharge le GameState courant.
     */
    private void unloadState() {
        this.gameStates[this.currentState] = null;
    }
    
    /**
     * Change le GameState courant par celui dont l'identifiant 
     * est passé en argument et le charge.
     * @param id l'identifiant du GameState à charger.
     */
    public void switchState(int id) {
        // Precondition
        if (id < 0 || id >= this.gameStates.length) {
            throw new IllegalArgumentException("Identifiant de "
                                               + "GameState Invalide");
        }
        
        // On décharge le GameState courant
        unloadState();
        
        // On charge le prochain
        this.currentState = id;
        loadState();
    }
    
    /**
     * Met à jour le GameState courant.
     */
    public void update() {
        this.gameStates[this.currentState].update();
    }
    
    /**
     * Dessine les mises à jour calculées sur le contexte graphique 
     * passé en argument.
     * @param graphics le contexte graphique sur lequel déssiner.
     */
    public void draw(Graphics2D graphics) {
        this.gameStates[this.currentState].draw(graphics);
    }
    
    /**
     * Action à effectuer sur le GameState courant quand 
     * une touche du clavier est appuyée.
     * @param key le code de la touche appuyée.
     */
    public void keyPressed(int key) {
        this.gameStates[this.currentState].keyPressed(key);
    }
    
    /**
     * Action à effectuer sur le GameState courant quand 
     * une touche du clavier est relachée.
     * @param key le code de la touche relachée.
     */
    public void keyReleased(int key) {
        this.gameStates[this.currentState].keyReleased(key);
    }
    
    /**
     * Getter sur instance
     * @return instance
     */
    public static GameStateManager getInstance() {
        return instance;
    }
}
