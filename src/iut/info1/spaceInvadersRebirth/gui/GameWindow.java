/*
 * GameWindow.java
 */
package iut.info1.spaceInvadersRebirth.gui;

import iut.info1.spaceInvadersRebirth.res.Resources;

import javax.swing.JFrame;

/**
 * Repr�sente la fen�tre contenant le panneau de jeu.
 * @author
 * @version dev 0.3
 */
public class GameWindow extends JFrame {

    /** Num�ro de s�rialisation. */
    private static final long serialVersionUID = 2579360686780115378L;

    /** Le titre du jeu � afficher en tant que titre de la fen�tre. */
    public static final String TITLE = "Space Invaders - Rebirth";
    
    /** La largeur de la fen�tre. */
    public static final int WIDTH = 1024;
    
    /** La hauteur de la fen�tre. */
    public static final int HEIGHT = 768;
    
    /** 
     * Le panneau de jeu contenant tous les �l�ments du jeu 
     * et prenant tout l'espace sur le contenu de la fen�tre. 
     */
    private GamePanel gamePanel;
    
    /** Construit une nouvelle fen�tre de jeu. */
    public GameWindow() {
        // Titre
        setTitle(TITLE);
        
        // Ev�nnement � la fermeture
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Non redimensionnable
        setResizable(false);

        // Taille
        setSize(WIDTH, HEIGHT);
        
        // Appara�t au centre de l'�cran
        setLocationRelativeTo(null);
        
        // Charge les ressources du jeu
        Resources.loadResources();
        
        // Ajout du panneau de jeu et lancement du jeu
        getContentPane().add(getGamePanel());
        gamePanel.run();
    }
    
    /**
     * @return le panneau de jeu de la fen�tre.
     */
    public GamePanel getGamePanel() {
        if (gamePanel == null) {
            gamePanel = new GamePanel();
        }
        return gamePanel;
    }
}
