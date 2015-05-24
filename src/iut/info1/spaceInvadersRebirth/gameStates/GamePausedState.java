/*
 * GamePausedState.java
 */
package iut.info1.spaceInvadersRebirth.gameStates;

import iut.info1.spaceInvadersRebirth.gui.GamePanel;
import iut.info1.spaceInvadersRebirth.res.Resources;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 * Représente le menu pause du jeu.
 * @author
 * @version
 */
public class GamePausedState extends MenuState {

    /** Les différentes options du menu pause. */
    public static final String[] GAME_PAUSED_MENU_OPTIONS = {
        "Resume", "Retry", "Main Menu"
    };
    
    /**
     * Construit un nouveau menu pause.
     * @param gameStateManager le manager qui doit gérer ce GameState.
     * @throws NullPointerException si gameStateManager == null.
     */
    public GamePausedState(GameStateManager gameStateManager)
    throws NullPointerException {
        super(gameStateManager, GAME_PAUSED_MENU_OPTIONS);
    }
    
    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameStates.MenuState#doAction()
     */
    @Override
    public void doAction() {
        switch (currentOptionSelected) {
            case 0:
                // Reprendre le jeu
                gameStateManager.resumeGame();
                Resources.gameMusic.play();
                break;
            case 1:
                // Recommencer le jeu
                gameStateManager.retryGame();
                break;
            case 2:
                // Revenir au menu principal
                gameStateManager.switchState(GameStateManager.ID_MENU_STATE);
                break;
        }
    }

    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameStates.GameState#init()
     */
    @Override
    public void init() {
        // UNUSED
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
        
        graphics.drawString("Game Paused", 
                            (int) (GamePanel.WIDTH / 2.8), 
                            150);
        
        // Dessin du menu
        for (int i = 0 ; i < menuOptions.length ; i++) {
            if (i == currentOptionSelected) {
                graphics.setColor(Resources.COLOR_TEXT_SELECTED);
            } else {
                graphics.setColor(Resources.COLOR_TEXT);
            }
            graphics.drawString(menuOptions[i], 
                                (int) (GamePanel.WIDTH / 2.8),
                                GamePanel.HEIGHT / 2 + i * 75);
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
            
        // Si on re-appuie sur "Echap" alors reprend le jeu.
        } else if (keyCode == KeyEvent.VK_ESCAPE) {
            currentOptionSelected = 0;
            doAction();
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
