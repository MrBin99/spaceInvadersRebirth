/*
 * MenuState.java
 */
package iut.info1.spaceInvadersRebirth.gameStates;

/**
 * Repr�sente un �tat de menu de jeu qui peut �tre affich� � l'�cran.
 * @author
 * @version dev
 */
public abstract class MenuState extends GameState {
    
    /** Les options du menu affich�. */
    protected String[] menuOptions;
    
    /** 
     * L'option courante du menu selectionn�e 
     * (indice dans le tableau des options). 
     */
    protected int currentOptionSelected;

    /**
     * Construit un nouvel �tat de menu.
     * @param gameStateManager le manager qui g�re cet �tat.
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
    
    /** Ex�cute l'action s�lectionn�e dans le menu. */
    public abstract void doAction();
}
