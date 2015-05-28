/*
 * HelpMenuState.java
 */
package iut.info1.spaceInvadersRebirth.gameStates;

import iut.info1.spaceInvadersRebirth.gameObjects.enemies.BigInvader;
import iut.info1.spaceInvadersRebirth.gameObjects.enemies.LittleInvader;
import iut.info1.spaceInvadersRebirth.gameObjects.enemies.MediumInvader;
import iut.info1.spaceInvadersRebirth.gameObjects.enemies.MysteryShip;
import iut.info1.spaceInvadersRebirth.gui.GamePanel;
import iut.info1.spaceInvadersRebirth.res.Resources;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 * 
 * @author
 * @version dev
 */
public class HelpMenuState extends MenuState {
    
    /** Les différentes options du menu. */
    public static final String[] HELP_MENU_OPTION = {
        "Back"
    };

    /**
     * @param gameStateManager
     * @param menuOptions
     * @throws NullPointerException
     */
    public HelpMenuState(GameStateManager gameStateManager) 
    throws NullPointerException {
        super(gameStateManager, HELP_MENU_OPTION);
    }

    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameStates.MenuState#doAction()
     */
    @Override
    public void doAction() {
        // Une seule option
        gameStateManager.switchState(GameStateManager.ID_MENU_STATE);
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
        graphics.setFont(Resources.font.deriveFont(20f));
        graphics.setColor(Resources.COLOR_TEXT);
        
        // Dessine le fond
        graphics.drawImage(Resources.background, 0, 0, null);
        
        // Affichage du logo
        graphics.drawImage(Resources.logo, 
                           GamePanel.WIDTH / 2 - Resources.logo.getWidth() / 2, 
                           30, null);
        
        // Dessins de chaque ennemis avec les points qu'ils donne
        graphics.drawImage(Resources.littleInvaderSprite, 
                           (int) (GamePanel.WIDTH / 2.5f),
                           Resources.littleInvaderSprite.getHeight() 
                               + 20 + Resources.logo.getHeight(),
                           null);
        graphics.drawString("    =    " + LittleInvader.POINTS_ON_DEATH + " pts", 
                            (int) (GamePanel.WIDTH / 2.2f),
                            Resources.littleInvaderSprite.getHeight() 
                            + 55 + Resources.logo.getHeight());
        
        graphics.drawImage(Resources.mediumInvaderSprite, 
                (int) (GamePanel.WIDTH / 2.5f),
                Resources.mediumInvaderSprite.getHeight() 
                    + 70 + Resources.logo.getHeight(),
                null);
        graphics.drawString("    =    " + MediumInvader.POINTS_ON_DEATH + " pts", 
                 (int) (GamePanel.WIDTH / 2.2f),
                 Resources.mediumInvaderSprite.getHeight() 
                 + 105 + Resources.logo.getHeight());
        
        graphics.drawImage(Resources.bigInvaderSprite, 
                (int) (GamePanel.WIDTH / 2.5f),
                Resources.bigInvaderSprite.getHeight() 
                    + 130 + Resources.logo.getHeight(),
                null);
        graphics.drawString("    =    " + BigInvader.POINTS_ON_DEATH + " pts", 
                 (int) (GamePanel.WIDTH / 2.2f),
                 Resources.bigInvaderSprite.getHeight() 
                 + 165 + Resources.logo.getHeight());
        
        graphics.drawImage(Resources.mysteryShipSprite, 
                (int) (GamePanel.WIDTH / 2.6f),
                Resources.mysteryShipSprite.getHeight() 
                    + 180 + Resources.logo.getHeight(),
                null);
        
        // Les points du Mystery Ship
        String mysteryShipPoints = "";
        
        // Parcours les points
        for (int points : MysteryShip.POINTS_ON_DEATH) {
            mysteryShipPoints += points + ", ";
        }
        
        graphics.drawString("    =    " 
                    + mysteryShipPoints.substring(
                            0, mysteryShipPoints.length() - 1) + " pts", 
                (int) (GamePanel.WIDTH / 2.2f),
                Resources.mysteryShipSprite.getHeight() 
                + 215 + Resources.logo.getHeight());
        
        // Affiche du menu "Back"
        graphics.setColor(Resources.COLOR_TEXT_SELECTED);
        graphics.setFont(Resources.font.deriveFont(40f));
        graphics.drawString(HELP_MENU_OPTION[0],
                            (int) (GamePanel.WIDTH / 2.2f), 
                            Resources.logo.getHeight() + 350);
        
        // Affichage des crédits, copyrights
        graphics.setColor(Resources.COLOR_TEXT);
        graphics.setFont(Resources.font.deriveFont(20f));
        
        graphics.drawString("IUT de Rodez - Informatique 1e Année - Semestre 2",
                            GamePanel.WIDTH / 5, GamePanel.HEIGHT / 1.05f);
        
        graphics.drawString("DIGOT Julien, PASCAL Ludovic, BLANC Lucas "
                                + "et PAGES Paul",
                            GamePanel.WIDTH / 6, GamePanel.HEIGHT / 1.01f);
        
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
