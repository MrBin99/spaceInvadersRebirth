/*
 * MenuState.java
 */
package iut.info1.spaceInvadersRebirth.gameStates;

import iut.info1.spaceInvadersRebirth.gui.GamePanel;
import iut.info1.spaceInvadersRebirth.res.Resources;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 * Représente le menu principal du jeu permettant 
 * au joueur de jouer ou de quitter le jeu.
 * @author
 * @version dev 0.2
 */
public class MainMenuState extends MenuState {

    /** Les différentes options du menu principal. */
    public static final String[] MAIN_MENU_OPTIONS = {
        "Play", "Help", "Quit"
    };
    
    /** 
     * L'option courante selectionnée par l'utilisateur 
     * (indice dans le tableau des options). 
     */
    private int currentOptionSelected;
    
    /**
     * Créé un nouveau menu principal du jeu.
     * @param gameStateManager le GameStateManager qui contrôle ce MenuState.
     * @throws NullPointerException si gameStateManager == null
     */
    public MainMenuState(GameStateManager gameStateManager)
    throws NullPointerException {
        super(gameStateManager, MAIN_MENU_OPTIONS);

        // Au chargement l'option sélectionnée est "Play"
        currentOptionSelected = 0;
    }
    
    /*
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameStates.MenuState#doAction()
     */
    @Override
    public void doAction() {
        switch (this.currentOptionSelected) {
            case 0:
                // Charge le jeu
                this.gameStateManager.switchState(
                                          GameStateManager.ID_LEVEL_STATE);
                break;
            case 1:
                // TODO Help
                break;
            case 2:
                // Quitte le jeu
                System.exit(0);
                break;
        }
    }

    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameStates.GameState#init()
     */
    @Override
    public void init() {
        Resources.gameMusic.stop();
        Resources.menuMusic.play();
    }

    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameStates.GameState#update()
     */
    @Override
    public void update() {
        // UNUSED
    }

    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameStates.GameState#draw(java.awt.Graphics2D)
     */
    @Override
    public void draw(Graphics2D graphics) {
        // Attribution de la police est la couleur du texte
        graphics.setFont(Resources.font.deriveFont(40f));
        graphics.setColor(Resources.COLOR_TEXT);
        
        // Dessine le fond
        graphics.drawImage(Resources.background, 0, 0, null);
        
        // Affichage du logo
        graphics.drawImage(Resources.logo, 
                           GamePanel.WIDTH / 2 - Resources.logo.getWidth() / 2, 
                           50, null);
        
        for (int i = 0 ; i < menuOptions.length ; i++) {
            if (i == currentOptionSelected) {
                graphics.setColor(Resources.COLOR_TEXT_SELECTED);
            } else {
                graphics.setColor(Resources.COLOR_TEXT);
            }
            graphics.drawString(menuOptions[i], 
                                (int) (GamePanel.WIDTH / 2.2f), 
                                Resources.logo.getHeight() + 200 + i * 75);
        }
    }

    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameStates.GameState#keyPressed(int)
     */
    @Override
    public void keyPressed(int keyCode) {
        // Le joueur à appuyé sur "Entrée"
        if (keyCode == KeyEvent.VK_ENTER) {
            doAction();
        
        // Le joueur veut monter dans le menu
        } else if (keyCode == KeyEvent.VK_UP) {
            this.currentOptionSelected--;
            
            // On passe à la fin du menu s'il veut monter plus haut
            if (this.currentOptionSelected == -1) {
                this.currentOptionSelected = menuOptions.length - 1;
            }
            
        // Le joueur veut descendre dans le menu
        } else if (keyCode == KeyEvent.VK_DOWN) {
            this.currentOptionSelected++;
            
            // On passe à la début du menu s'il veut descendre plus bas
            if (this.currentOptionSelected == menuOptions.length) {
                this.currentOptionSelected = 0;
            }
        }
    }
    
    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameStates.GameState#keyReleased(int)
     */
    @Override
    public void keyReleased(int keyCode) {
        // UNUSED
    }
}
