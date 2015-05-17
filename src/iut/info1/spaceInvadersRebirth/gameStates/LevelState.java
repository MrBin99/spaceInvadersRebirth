/*
 * LevelState.java
 */
package iut.info1.spaceInvadersRebirth.gameStates;

import iut.info1.spaceInvadersRebirth.gameObjects.LittleInvader;
import iut.info1.spaceInvadersRebirth.gameObjects.Player;
import iut.info1.spaceInvadersRebirth.gameObjects.Shot;
import iut.info1.spaceInvadersRebirth.gui.GamePanel;
import iut.info1.spaceInvadersRebirth.res.Resources;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 * Classe permettant de g�rer le niveau de jeu comme le joueur, les points, la
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
    private Player player;

    /** Le little invader */
    private LittleInvader littleInvader;

    /**
     * @param gameStateManager
     *            manager qui doit g�rer cet �tat.
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
        init();
    }

    /*
     * (non-Javadoc)
     * 
     * @see iut.info1.spaceInvadersRebirth.gameStates.GameState#init()
     */
    @Override
    public void init() {
        initPlayer();
        initLittleInvader();
    }

    /**
     * Initialise la position du joueur au d�but de chaque niveau.
     */
    private void initLittleInvader() {
        // Permet de positionner le little invader sur le panneau de jeu
        littleInvader.translate(5, 10 + littleInvader.getHeight());

    }

    /**
     * Initialise la position du joueur au d�but de chaque niveau.
     */
    public void initPlayer() {
        // Permet de positionner le joueur sur la panneau de jeu.
        player.translate(GamePanel.WIDTH / 2 - player.getWidth() / 2,
                GamePanel.HEIGHT - (int) (player.getHeight() * 1.5));
    }

    /*
     * (non-Javadoc)
     * 
     * @see iut.info1.spaceInvadersRebirth.gameStates.GameState#update()
     */
    @Override
    public void update() {
        player.move();
        littleInvader.move();
        for (Shot shot : player.getShots()) {
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
     * 
     * @see
     * iut.info1.spaceInvadersRebirth.gameStates.GameState#draw(java.awt.Graphics2D
     * )
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
        for (Shot shot : player.getShots()) {
            if (shot != null && shot.getPosY() > 50) {

                // Initialise le projectile du player
                graphics.drawImage(shot.getFrame(), shot.getPosX(),
                        shot.getPosY(), null);
            }
        }
    }

    /**
     * Affiche le contexte de jeu (vie, points, etc.).
     * 
     * @param graphics
     *            contexte graphique o� la m�thode doit dessiner.
     */
    private void drawHUD(Graphics2D graphics) {
        // Mets la couleur en blanc.
        graphics.setColor(Resources.COLOR_TEXT);

        // Elargit la line (4 pixels de large).
        graphics.setStroke(new BasicStroke(4));
        graphics.drawLine(0, 50, GamePanel.WIDTH, 50);

        // D�finit la taille de la police et la police
        graphics.setFont(Resources.font.deriveFont(20f));
        graphics.drawString(playerPoints + "   Points", 10, 45);
        graphics.drawString("Level    " + level, GamePanel.WIDTH / 2 - 60, 45);
        graphics.drawString(playerLifes + "   Lifes", GamePanel.WIDTH - 110, 45);
    }

    /*
     * (non-Javadoc)
     * 
     * @see iut.info1.spaceInvadersRebirth.gameStates.GameState#keyPressed(int)
     */
    @Override
    public void keyPressed(int keyCode) {
        // Permet de capter une pression sur les fl�ches directionnelles.
        if (keyCode == KeyEvent.VK_LEFT) {
            player.setMovingLeft(true);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            player.setMovingRight(true);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see iut.info1.spaceInvadersRebirth.gameStates.GameState#keyReleased(int)
     */
    @Override
    public void keyReleased(int keyCode) {
        // Permet de capter si on relache le bouton
        if (keyCode == KeyEvent.VK_SPACE) {
            player.shoot();
        }

    }

}
