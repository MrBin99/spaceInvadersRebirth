/*
 * MenuState.java
 */
package iut.info1.spaceInvadersRebirth.gameStates;

/**
 * Représente un état de menu de jeu qui peut être affiché à l'écran.
 * @author
 * @version dev
 */
public abstract class MenuState extends GameState {
    
    /** Les options du menu affiché. */
    protected String[] menuOptions;
    
    /** 
     * L'option courante du menu selectionnée 
     * (indice dans le tableau des options). 
     */
    protected int currentOptionSelected;

    /**
     * Construit un nouvel état de menu.
     * @param gameStateManager le manager qui gère cet état.
     * @param menuOptions les options du menu.
     * @throws NullPointerException si gameStateManager == null 
     *                              ou si  menuOptions == null
     */
    protected MenuState(GameStateManager gameStateManager, String[] menuOptions)
    throws NullPointerException {
        super(gameStateManager);
        
        if (menuOptions == null) {
            throw new NullPointerException();
        }
        
        // Initialise les champs
        this.currentOptionSelected = 0;
        this.menuOptions = menuOptions;
    }
    
    /** Exécute l'action sélectionnée dans le menu. */
    public abstract void doAction();
}
