/*
 * GameStateManager.java
 */
package iut.info1.spaceInvadersRebirth.gameStates;

import java.awt.Graphics2D;

/**
 * Classe permettant de g�rer les �tats du jeu (menus, game over et le jeu).
 * Cette classe permet de d�terminer � quel moment 
 * afficher ces diff�rents �tats.
 * @author
 * @version 1.0
 */
public class GameStateManager {

    /** 
     * Seule instance du GameStateManager 
     * dans l'application (Pattern Singleton). 
     */
    private static GameStateManager instance = new GameStateManager();
    
    /** Le nombre de GameStates g�r�s par le manager. */
    public static final int NBR_GAME_STATES = 5;
    
    /** 
     * Identifiant dans le GameStateManager du menu principal .
     * (indice dans le tableau des GameStates). 
     */
    public static final int ID_MENU_STATE = 0;
    
    /** 
     * Identifiant dans le GameStateManager du jeu.
     * (indice dans le tableau des GameStates). 
     */
    public static final int ID_LEVEL_STATE = 1;
    
    /** 
     * Identifiant dans le GameStateManager de l'�cran de pause du jeu.
     * (indice dans le tableau des GameStates). 
     */
    public static final int ID_GAME_PAUSED_STATE = 2;
    
    /** 
     * Identifiant dans le GameStateManager de l'�cran "Game Over".
     * (indice dans le tableau des GameStates). 
     */
    public static final int ID_GAME_OVER_STATE = 3;
    
    /** 
     * Identifiant dans le GameStateManager de l'�cran d'aide.
     * (indice dans le tableau des GameStates). 
     */
    public static final int ID_GAME_HELP_STATE = 4;
    
    /** Les �tats possibles du jeu g�r�s par le manager. */
    private GameState[] gameStates;
    
    /** 
     * L'identifiant du GameState courant charg� 
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
     * Instancie le GameState r�f�renc� par 
     * son identifiant contenu dans le champ du GameState courant.
     * @see GameStateManager#switchState(int)
     */
    private void loadState() {
        switch (this.currentState) {
            case ID_MENU_STATE:
                // Lance le menu du jeu
                this.gameStates[this.currentState] = new MainMenuState(this);
                break;
            case ID_LEVEL_STATE:
                this.gameStates[this.currentState] = new LevelState(this);
                break;
            case ID_GAME_OVER_STATE:
                this.gameStates[this.currentState] = new GameOverState(this);
                break;
            case ID_GAME_HELP_STATE :
                this.gameStates[this.currentState] = new HelpMenuState(this);
                break;
        }
        
        this.gameStates[this.currentState].init();
    }
    
    /** D�charge le GameState courant. */
    private void unloadState() {
        this.gameStates[this.currentState] = null;
    }
    
    /**
     * Change le GameState courant par celui dont l'identifiant 
     * est pass� en argument et le charge.
     * @param id l'identifiant du GameState � charger.
     * @throws IllegalArgumentException si id ne correspond 
     *                                  � aucun GameState sauvegard�.
     */
    public void switchState(int id) {
        // Precondition
        if (id < 0 || id >= this.gameStates.length) {
            throw new IllegalArgumentException("Identifiant de "
                                               + "GameState Invalide.");
        }
        
        // On d�charge le GameState courant
        unloadState();
        
        // On charge le prochain
        this.currentState = id;
        loadState();
    }
    
    /** Met le jeu en pause. */
    public void pauseGame() {
        this.currentState = ID_GAME_PAUSED_STATE;
        this.gameStates[currentState] = new GamePausedState(this);
    }
    
    /** Reprend le jeu qui �tait en pause. */
    public void resumeGame() {
        this.currentState = ID_LEVEL_STATE;
    }
    
    /** Recommence le jeu. */
    public void retryGame() {
        this.currentState = ID_LEVEL_STATE;
        loadState();
    }
    
    /** Met � jour le GameState courant. */
    public void update() {
        if (gameStates[currentState] != null) {
            gameStates[currentState].update();
        }
    }
    
    /** 
     * Met � jour l'affichage du jeu pour le GameState courant. 
     * @param graphics le contexte graphique o� dessiner les mises � jour. 
     */
    public void draw(Graphics2D graphics) {
        if (gameStates[currentState] != null) {
            gameStates[currentState].draw(graphics);
        }
    }
    
    /**
     * Action � effectuer sur le GameState courant quand 
     * une touche du clavier est appuy�e.
     * @param keyCode le code de la touche appuy�e.
     */
    public void keyPressed(int keyCode) {
        if (gameStates[currentState] != null) {
            gameStates[currentState].keyPressed(keyCode);
        }
    }
    
    /**
     * Action � effectuer sur le GameState courant quand 
     * une touche du clavier est relach�e.
     * @param keyCode le code de la touche relach�e.
     */
    public void keyReleased(int keyCode) {
        if (gameStates[currentState] != null) {
            gameStates[currentState].keyReleased(keyCode);
        }
    }
    
    /**
     * @return l'instance du GameStateManager.
     */
    public static GameStateManager getInstance() {
        return instance;
    }
}
