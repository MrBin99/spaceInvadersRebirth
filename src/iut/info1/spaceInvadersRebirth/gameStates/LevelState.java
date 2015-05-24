/*
 * LevelState.java
 */
package iut.info1.spaceInvadersRebirth.gameStates;

import iut.info1.spaceInvadersRebirth.gameObjects.GameObject;
import iut.info1.spaceInvadersRebirth.gameObjects.MovableGameObject;
import iut.info1.spaceInvadersRebirth.gameObjects.Player;
import iut.info1.spaceInvadersRebirth.gameObjects.Shelter;
import iut.info1.spaceInvadersRebirth.gameObjects.Shot;
import iut.info1.spaceInvadersRebirth.gameObjects.enemies.EnemyWave;
import iut.info1.spaceInvadersRebirth.gameObjects.enemies.MysteryShip;
import iut.info1.spaceInvadersRebirth.gui.GamePanel;
import iut.info1.spaceInvadersRebirth.res.Resources;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 * Repr�sente le plateau de jeu en lui-m�me compos� du joueur, 
 * des ennemis et des barricades.
 * @author
 * @version
 */
public class LevelState extends GameState {
    
    /** Le nombre de vies restant au joueur. */
    private int playerLifes;
    
    /** Le nombre de points que poss�de le joueur. */
    private int playerPoints;
    
    /** Le joueur sur le plateau de jeu. */
    private GameObject player;
    
    /** Les barricades permettant d'aider le joueur � se prot�ger. */
    private GameObject[] shelters;
    
    /** La vague d'ennemis pr�sente sur le jeu. */
    private EnemyWave enemyWave;
    
    /** Le vaisseau myst�re qui appara�t al�atoirement. */
    private GameObject mysteryShip;

    /**
     * Construit le plateau de jeu.
     * @param gameStateManager le GameStateManager qui doit g�rer ce GameState.
     * @throws NullPointerException si gameStateManager == null.
     */
    public LevelState(GameStateManager gameStateManager)
    throws NullPointerException {
        super(gameStateManager);

        // Par d�faut le joueur � 3 vies au d�but du jeu
        playerLifes = 3;
        
        // Le joueur n'a pas de points au commencement
        playerPoints = 0;
        
        // Cr�� un nouveau joueur
        player = new Player(this);
        
        // Cr�� le tableau des barricades
        shelters = new GameObject[4];
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
        initShelters();
        initEnemies();
    }
    
    /** Initialise une nouvelle vague d'ennemis. */
    private void initEnemies() {
        enemyWave = new EnemyWave(this);
        enemyWave.init();
    }

    /** 
     * Initialise la position des barricades au d�but du jeu.
     */
    private void initShelters() {
        for (int i = 0 ; i < shelters.length ; i++) {
            shelters[i] = new Shelter(this);
            shelters[i].translate(75 + 250 * i, (int) (GamePanel.HEIGHT * 0.75));
        }
    }

    /** 
     * Initialise la position du joueur au d�but du jeu 
     * et � chaque retour � la vie du joueur. 
     */
    private void initPlayer() {
        player.translate(GamePanel.WIDTH / 2 - player.getWidth() / 2,
                         GamePanel.HEIGHT - player.getHeight());
    }
    
    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameStates.GameState#update()
     */
    @Override
    public void update() {
        super.update();
        
        int random = (int) (Math.random() * 100);
        
        if (mysteryShip == null && frameCounter % 60 == 0 && random > 50) {
            mysteryShip = new MysteryShip(this);
            mysteryShip.translate(GamePanel.WIDTH + mysteryShip.getWidth(), 70);
        }
        
        if (mysteryShip != null) { 
            mysteryShip.update();
        }
        
        // Bouge le joueur si l'utilisateur a press� sur une touche
        player.update();
        
        if (enemyWave != null) {
            updateEnemyWave();
        }
    }
    
    /** Met � jour l'�tat de la vague d'ennemis. */
    private void updateEnemyWave() {
        // R�cup�re les ennemis des la vague
        GameObject[][] enemies = enemyWave.getEnemies();
        
        for (int i = 0 ; i < enemies.length ; i++) {
            for (int j = 0 ; j < enemies[i].length ; j++) {
                if (enemies[i][j] != null && enemies[i][j].isDead()) {
                    enemies[i][j] = null;
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
        drawPlayer(graphics);
        drawShelters(graphics);
        drawPlayerShots(graphics);
        drawEnemyWave(graphics);
        drawMysteryShip(graphics);
    }

    /**
     * @param graphics
     */
    private void drawMysteryShip(Graphics2D graphics) {
        if (mysteryShip != null) {
            graphics.drawImage(mysteryShip.getFrame(), mysteryShip.getPosX(), mysteryShip.getPosY(), null);
        }
    }

    /**
     * Dessine la vague d'ennemis � l'�cran.
     * @param graphics le contexte graphique o� dessiner 
     *                 la vague.
     */
    private void drawEnemyWave(Graphics2D graphics) {
        // R�cup�re les ennemis des la vague
        GameObject[][] enemies = enemyWave.getEnemies();
        
        // On parcours les ennemis et on les dessine
        for (int i = 0 ; i < enemies.length ; i++) {
            for (int j = 0 ; j < enemies[i].length ; j++) {
                if (enemies[i][j] != null) {
                    graphics.drawImage(enemies[i][j].getFrame(),
                                       enemies[i][j].getPosX(), 
                                       enemies[i][j].getPosY(), 
                                       null);
                }
            }
        }
    }

    /**
     * Dessine les barricades du joueur � l'�cran.
     * @param graphics le contexte graphique o� dessiner 
     *                 les barricades.
     */
    private void drawShelters(Graphics2D graphics) {
        for (int i = 0 ; i < shelters.length ; i++) {
            graphics.drawImage(shelters[i].getFrame(), 
                               shelters[i].getPosX(), 
                               shelters[i].getPosY(), 
                               null);
        }
    }

    /**
     * Dessine les projectiles du joueur � l'�cran.
     * @param graphics le contexte graphique o� dessiner 
     *                 les projectiles du joueur.
     */
    private void drawPlayerShots(Graphics2D graphics) {
        for (Shot shot : ((Player) player).getShots()) {
            graphics.drawImage(shot.getFrame(), 
                               shot.getPosX(), 
                               shot.getPosY(), 
                               null);
        }
    }

    /**
     * Dessine le joueur � l'�cran.
     * @param graphics le contexte graphique o� dessiner le joueur.
     */
    private void drawPlayer(Graphics2D graphics) {
        graphics.drawImage(player.getFrame(), 
                           player.getPosX(),
                           player.getPosY(), 
                           null);
    }

    /**
     * Dessine l'interface utilisateur � l'�cran.
     * @param graphics le contexte graphique o� dessiner 
     *                 l'interface utilisateur.
     */
    private void drawHUD(Graphics2D graphics) {
        // Mets la couleur en blanc.
        graphics.setColor(Resources.COLOR_TEXT);
        
        // Dessine le fond
        graphics.drawImage(Resources.background, 0, 0, null);

        // Elargit la line (4 pixels de large).
        graphics.setStroke(new BasicStroke(4));
        graphics.drawLine(0, 50, GamePanel.WIDTH, 50);

        // D�finit la taille de la police et la police
        graphics.setFont(Resources.font.deriveFont(20f));
        graphics.drawString(playerPoints + "   Points", 10, 45);
        //graphics.drawString("Level    " + numLevel, GamePanel.WIDTH / 2 - 60, 45);
        graphics.drawString(playerLifes + "   Lifes", GamePanel.WIDTH - 110, 45);
    }

    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameStates.GameState#keyPressed(int)
     */
    @Override
    public void keyPressed(int keyCode) {
        // Bouge le joueur selon les touches press�es
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
        if (keyCode == KeyEvent.VK_SPACE) {
            ((Player) player).shoot();
        }
    }
    
    /**
     * @return le joueur de ce plateau de jeu.
     */
    public GameObject getPlayer() {
        return this.player;
    }
    
    /**
     * @return la vague courante d'ennemis pr�sente sur plateau de jeu.
     */
    public EnemyWave getEnemyWave() {
        return this.enemyWave;
    }
    
    /**
     * @return le vaisseau myst�re pr�sente sur le plateau.
     */
    public GameObject getMysteryShip() {
        return this.mysteryShip;
    }
    
    /**
     * @return les barricades de ce plateau de jeu.
     */
    public GameObject[] getShelters() {
        return this.shelters;
    }
    
    /**
     * Ajoute un nombre de points aux points du joueur.
     * @param points les points � ajouter.
     */
    public void addPoints(int points) {
        playerPoints += points;
    }
}
