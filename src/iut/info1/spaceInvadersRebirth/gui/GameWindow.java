/*
 * GameWindow.java
 */
package iut.info1.spaceInvadersRebirth.gui;

import javax.swing.JFrame;

/**
 * Repr�sente la fen�tre qui contiendra le jeu.
 * @author
 * @version dev 0.1
 */
public class GameWindow extends JFrame {

    /** Num�ro de s�rialisation. */
    private static final long serialVersionUID = 2579360686780115378L;
    
    /** La largeur de la fen�tre. */
    public static final int FRAME_WIDTH = 800;
    
    /** La hauteur de la fen�tre. */
    public static final int FRAME_HEIGHT = 800;
    
    /** Le titre du jeu. */
    public static final String GAME_TITLE = "Space Invaders : Rebirth";
    
    /** Le panneau o� sera dessin� le jeu. */
    private GamePanel panel;

    /** Constructeur de la fen�tre. */
    public GameWindow() {
        // Taille de la fen�tre
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        
        // Action � effectuer lors de la fermeture de la fen�tre
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Fen�tre non redimensionnable
        this.setResizable(false);
        
        // Titre
        this.setTitle(GAME_TITLE);
        
        // Place la fen�tre au centre de l'�cran au d�marrage
        this.setLocationRelativeTo(null);
        
        // Ajout du panneau
        this.panel = new GamePanel();
        this.getContentPane().add(panel);
        
        // Met la fen�tre visible
        this.setVisible(true);
    }
    
    /** Lance le jeu. */
    public void run() {
        this.panel.run();
    }
}
