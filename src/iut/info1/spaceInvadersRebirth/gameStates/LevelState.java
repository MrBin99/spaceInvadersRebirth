/*
 * LevelState.java
 */
package iut.info1.spaceInvadersRebirth.gameStates;

import iut.info1.spaceInvadersRebirth.gameObjects.GameObject;
import iut.info1.spaceInvadersRebirth.gameObjects.MovableGameObject;
import iut.info1.spaceInvadersRebirth.gameObjects.Player;
import iut.info1.spaceInvadersRebirth.gameObjects.Shelter;
import iut.info1.spaceInvadersRebirth.gameObjects.Shot;
import iut.info1.spaceInvadersRebirth.gameObjects.enemies.LittleInvader;
import iut.info1.spaceInvadersRebirth.gui.GamePanel;
import iut.info1.spaceInvadersRebirth.res.Resources;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 * Classe permettant de gérer le niveau de jeu comme le joueur, les points, la
 * vie, etc.
 * @author
 * @version dev
 */
public class LevelState extends GameState {

    /** Le nombre de vies restantes du joueur. */
    private int playerLifes;

    /** Le nombre de points actuel du joueur. */
    private int playerPoints;

    /** Niveau actuel du joueur. */
    private int level;

    /** Le joueur */
    private GameObject player;
    
    /** Les barricades pour protéger le joueur. */
    private GameObject[] shelters;

    /** Le little invader */
    private GameObject littleInvader;

    /**
     * Créé un nouvel état de jeu.
     * @param gameStateManager manager qui doit gérer cet état.
     * @throws NullPointerException
     */
    public LevelState(GameStateManager gameStateManager)
    throws NullPointerException {
        super(gameStateManager);
        playerLifes = 3;
        playerPoints = 0;
        level = 1;
        player = new Player(this);
        littleInvader = new LittleInvader(this);
        shelters = new Shelter[4];
    }

    /*
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameStates.GameState#init()
     */
    @Override
    public void init() {
        Resources.menuMusic.stop();
        Resources.gameMusic.play();
        initPlayer();
        initLittleInvader();
        
        // Initialise les barricades
        for (int i = 0 ; i < shelters.length ; i++) {
            shelters[i] = new Shelter(this);
            shelters[i].translate(GamePanel.WIDTH / 13 + i * 250, 
                                  GamePanel.HEIGHT - 220);
        }
    }

    /**
     * Initialise la position du joueur au début de chaque niveau.
     */
    private void initLittleInvader() {
        // Permet de positionner le little invader sur le panneau de jeu
        littleInvader.translate(5, 10 + littleInvader.getHeight());

    }

    /**
     * Initialise la position du joueur au début de chaque niveau.
     */
    public void initPlayer() {
        // Permet de positionner le joueur sur la panneau de jeu.
        player.translate(GamePanel.WIDTH / 2 - player.getWidth() / 2,
                GamePanel.HEIGHT - (int) (player.getHeight() * 1.3));
    }

    /*
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameStates.GameState#update()
     */
    @Override
    public void update() {
        ((Player) player).move();
        ((LittleInvader) littleInvader).move();
        for (Shot shot : ((Player) player).getShots()) {
            if (shot != null) {
                if (shot.getPosY() <= 50) {
                    shot = null;
                } else {
                    shot.move();
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameStates.GameState#draw(java.awt.Graphics2D)
     */
    @Override
    public void draw(Graphics2D graphics) {
        drawHUD(graphics);
        // Initialise le joueur.
        graphics.drawImage(player.getFrame(), player.getPosX(),
                player.getPosY(), null);

        // Initialise le little invader
        graphics.drawImage(littleInvader.getFrame(), littleInvader.getPosX(),
                littleInvader.getPosY(), null);
        
        // Dessine les barricades
        for (int i = 0 ; i < shelters.length ; i++) {
            if (shelters[i] != null) {
                graphics.drawImage(shelters[i].getFrame(), shelters[i].getPosX(), shelters[i].getPosY(), null);
            }
        }
        
        // Dessine les projectiles du joueur
        for (Shot shot : ((Player) player).getShots()) {
            if (shot != null && shot.getPosY() > 50) {

                // Initialise le projectile du player
                graphics.drawImage(shot.getFrame(), shot.getPosX(),
                        shot.getPosY(), null);
            }
        }
    }

    /**
     * Affiche le contexte de jeu (vie, points, etc.).
     * @param graphics contexte graphique où la méthode doit dessiner.
     */
    private void drawHUD(Graphics2D graphics) {
        // Mets la couleur en blanc.
        graphics.setColor(Resources.COLOR_TEXT);
        
        // Dessine le fond
        graphics.drawImage(Resources.background, 0, 0, null);

        // Elargit la line (4 pixels de large).
        graphics.setStroke(new BasicStroke(4));
        graphics.drawLine(0, 50, GamePanel.WIDTH, 50);

        // Définit la taille de la police et la police
        graphics.setFont(Resources.font.deriveFont(20f));
        graphics.drawString(playerPoints + "   Points", 10, 45);
        graphics.drawString("Level    " + level, GamePanel.WIDTH / 2 - 60, 45);
        graphics.drawString(playerLifes + "   Lifes", GamePanel.WIDTH - 110, 45);
    }

    /*
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameStates.GameState#keyPressed(int)
     */
    @Override
    public void keyPressed(int keyCode) {
        // Permet de capter une pression sur les flêches directionnelles.
        if (keyCode == KeyEvent.VK_LEFT) {
            ((MovableGameObject) player).setMovingLeft(true);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            ((MovableGameObject) player).setMovingRight(true);
        } else if (keyCode == KeyEvent.VK_ESCAPE) {
            gameStateManager.pauseGame();
        }
    }

    /*
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameStates.GameState#keyReleased(int)
     */
    @Override
    public void keyReleased(int keyCode) {
        // Permet de capter si on relache le bouton
        if (keyCode == KeyEvent.VK_SPACE) {
            ((Player) player).shoot();
        }
    }
}
