/*
 * GameWindow.java
 */
package iut.info1.spaceInvadersRebirth.gui;

import iut.info1.spaceInvadersRebirth.res.Resources;

import javax.swing.JFrame;

/**
 * Représente la fenêtre contenant le panneau de jeu.
 * @author
 * @version dev 0.3
 */
public class GameWindow extends JFrame {

    /** Numéro de sérialisation. */
    private static final long serialVersionUID = 2579360686780115378L;

    /** Le titre du jeu à afficher en tant que titre de la fenêtre. */
    public static final String TITLE = "Space Invaders - Rebirth";
    
    /** La largeur de la fenêtre. */
    public static final int WIDTH = 1024;
    
    /** La hauteur de la fenêtre. */
    public static final int HEIGHT = 768;
    
    /** 
     * Le panneau de jeu contenant tous les éléments du jeu 
     * et prenant tout l'espace sur le contenu de la fenêtre. 
     */
    private GamePanel gamePanel;
    
    /** Construit une nouvelle fenêtre de jeu. */
    public GameWindow() {
        // Titre
        setTitle(TITLE);
        
        // Evènnement à la fermeture
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Non redimensionnable
        setResizable(false);

        // Taille
        setSize(WIDTH, HEIGHT);
        
        // Apparaît au centre de l'écran
        setLocationRelativeTo(null);
        
        // Charge les ressources du jeu
        Resources.loadResources();
        
        // Ajout du panneau de jeu et lancement du jeu
        getContentPane().add(getGamePanel());
        gamePanel.run();
    }
    
    /**
     * @return le panneau de jeu de la fenêtre.
     */
    public GamePanel getGamePanel() {
        if (gamePanel == null) {
            gamePanel = new GamePanel();
        }
        return gamePanel;
    }
}
