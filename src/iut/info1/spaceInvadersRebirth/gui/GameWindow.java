/*
 * GameWindow.java
 */
package iut.info1.spaceInvadersRebirth.gui;

import javax.swing.JFrame;

/**
 * Représente la fenêtre qui contiendra le jeu.
 * @author
 * @version dev 0.1
 */
public class GameWindow extends JFrame {

    /** Numéro de sérialisation. */
    private static final long serialVersionUID = 2579360686780115378L;
    
    /** La largeur de la fenêtre. */
    public static final int FRAME_WIDTH = 800;
    
    /** La hauteur de la fenêtre. */
    public static final int FRAME_HEIGHT = 800;
    
    /** Le titre du jeu. */
    public static final String GAME_TITLE = "Space Invaders : Rebirth";
    
    /** Le panneau où sera dessiné le jeu. */
    private GamePanel panel;

    /** Constructeur de la fenêtre. */
    public GameWindow() {
        // Taille de la fenêtre
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        
        // Action à effectuer lors de la fermeture de la fenêtre
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Fenêtre non redimensionnable
        this.setResizable(false);
        
        // Titre
        this.setTitle(GAME_TITLE);
        
        // Place la fenêtre au centre de l'écran au démarrage
        this.setLocationRelativeTo(null);
        
        // Ajout du panneau
        this.panel = new GamePanel();
        this.getContentPane().add(panel);
        
        // Met la fenêtre visible
        this.setVisible(true);
    }
    
    /** Lance le jeu. */
    public void run() {
        this.panel.run();
    }
}
