/*
 * GamePanel.java
 */
package iut.info1.spaceInvadersRebirth.gui;

import iut.info1.spaceInvadersRebirth.gameStates.GameStateManager;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Le panneau contenu dans la fenêtre du jeu où sera dessiné le jeu.<br>
 * Le panneau comporte un écouteur clavier qui permettra 
 * à l'utilisateur de faire les actions du jeu.
 * @author
 * @version dev 0.1
 */
public class GamePanel extends Canvas implements KeyListener {

    /** Numéro de sérialisation. */
    private static final long serialVersionUID = 649482702172806765L;
    
    /** Nombre de fois par secondes que le jeu se met à jour. */
    public static final int FPS = 100;
    
    /** TODO Commenter */
    public static final int TARGET_TIME = 20000 / FPS;
    
    /** Si le jeu est en cours d'exécution. */
    private boolean gameRunning;
    
    /** Permet de gérer les états du jeu à afficher sur le panneau. */
    private GameStateManager gameStateManager;
    
    /**
     * Constructeur du panneau de jeu.
     */
    public GamePanel() {
        // Pour que le panneau garde le focus
        this.setFocusable(true);
        this.requestFocus();
        
        // Ajout de l'écouteur clavier
        this.addKeyListener(this);
        
        // Met l'arrière-plan en noir
        this.setBackground(Color.BLACK);
        
        // Affiche le menu comme 1er état
        this.gameStateManager = GameStateManager.getInstance();
        
        // Le jeu n'a pas démarré
        this.gameRunning = false;
    }
    
    /** Démarre la Game Loop du jeu. */
    public void run() {
        // Le jeu démarre
        this.gameRunning = true;
        
        long timeStart;
        long timeElapsed;
        long timeWait;
        
        // Game Loop
        while (this.gameRunning) {
            timeStart = System.nanoTime();
            
            // Mise à jour du jeu et de l'affichage
            update();
            repaint();
            
            timeElapsed = System.nanoTime() - timeStart;
            timeWait = TARGET_TIME - timeElapsed / 1000000;
            timeWait = timeWait < 0 ? 5 : timeWait;
            
            try {
                Thread.sleep(timeWait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    /** Met à jour l'état courant du jeu. */
    private void update() {
        this.gameStateManager.update();
    }
    
    /* 
     * (non-Javadoc)
     * @see java.awt.Canvas#paint(java.awt.Graphics)
     */
    @Override
    public void paint(Graphics graphics) {
        this.gameStateManager.draw((Graphics2D) graphics);
    }

    /* 
     * (non-Javadoc)
     * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // UNUSED
    }

    /* 
     * (non-Javadoc)
     * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
     */
    @Override
    public void keyPressed(KeyEvent e) {
        this.gameStateManager.keyPressed(e.getKeyCode());
    }

    /* 
     * (non-Javadoc)
     * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
     */
    @Override
    public void keyReleased(KeyEvent e) {
        this.gameStateManager.keyReleased(e.getKeyCode());
    }
}
