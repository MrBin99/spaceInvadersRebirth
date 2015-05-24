/*
 * GamePanel.java
 */
package iut.info1.spaceInvadersRebirth.gui;

import iut.info1.spaceInvadersRebirth.gameStates.GameStateManager;
import iut.info1.spaceInvadersRebirth.res.Resources;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

/**
 * Représente le panneau de jeu où sera dessiné tous les éléments du jeu.
 * @author
 * @version dev 0.2
 */
public class GamePanel extends JPanel implements KeyListener {

    /** Numéro de sérialisation. */
    private static final long serialVersionUID = -7952436435937556877L;
    
    /** 
     * "Frames Per Seconds", le nombre de fois 
     * que le jeu fait une mise à jour par secondes. 
     */
    public static final int FPS = 60;
    
    /** La largeur du panneau dans la fenêtre. */
    public static final int WIDTH = 1018;
    
    /** La hauteur du panneau dans la fenêtre. */
    public static final int HEIGHT = 763;
    
    /** 
     * Permet de mettre à jour régulièrement le jeu 
     * à chaque "tick" du Timer.<br>
     * C'est à dire toutes les (1000 / FPS) ms = 16 ms.
     */
    private Timer ticker;
    
    /**
     * Permet de gérer l'enchainement des différents écrans du jeu 
     * (ex : menu principal, le jeu en lui-même, le menu pause ...)
     */
    private GameStateManager gameStateManager;

    /** Construit le panneau de jeu. */
    public GamePanel() {
        // Permet d'avoir toujours le focus
        this.setFocusable(true);
        this.requestFocus();
        
        // Ajoute l'écouteur clavier au panneau
        this.addKeyListener(this);
        
        // Met le fond noir
        this.setBackground(Color.BLACK);
        
        // Permet un repaint() du panneau plus vite en utilisant un "buffer"
        this.setDoubleBuffered(true);
        
        // Création du GameStateManager
        gameStateManager = GameStateManager.getInstance();
    }
    
    /** Créé le Timer permettant de lancer le jeu. */
    public void run() {
        // Créé l'horloge qui "tick" toute les 16ms environ
        ticker = new Timer();
        ticker.schedule(new TimerTask() {
            
            // A chaque tick du jeu
            @Override
            public void run() {
                gameStateManager.update();
                repaint();
            }
            
        }, 0, 1000 / FPS);
    }
    
    /* 
     * (non-Javadoc)
     * @see javax.swing.JComponent#paint(java.awt.Graphics)
     */
    @Override
    public void paint(Graphics graphics) {
        
        // Repaint du jeu
        super.paint(graphics);
        gameStateManager.draw((Graphics2D) graphics);
    }

    /* 
     * (non-Javadoc)
     * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
     */
    @Override
    public void keyTyped(KeyEvent event) {
        // UNUSED
    }

    /* 
     * (non-Javadoc)
     * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
     */
    @Override
    public void keyPressed(KeyEvent event) {
        gameStateManager.keyPressed(event.getKeyCode());
    }

    /* 
     * (non-Javadoc)
     * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
     */
    @Override
    public void keyReleased(KeyEvent event) {
        gameStateManager.keyReleased(event.getKeyCode());
    }
}
