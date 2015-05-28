/*
 * LevelState.java
 */
package iut.info1.spaceInvadersRebirth.gameStates;

import iut.info1.spaceInvadersRebirth.gameObjects.Player;
import iut.info1.spaceInvadersRebirth.gameObjects.Shelter;
import iut.info1.spaceInvadersRebirth.gameObjects.Shot;
import iut.info1.spaceInvadersRebirth.gameObjects.enemies.Enemy;
import iut.info1.spaceInvadersRebirth.gameObjects.enemies.EnemyWave;
import iut.info1.spaceInvadersRebirth.gameObjects.enemies.MysteryShip;
import iut.info1.spaceInvadersRebirth.gui.GamePanel;
import iut.info1.spaceInvadersRebirth.res.Resources;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Représente le plateau de jeu en lui-même composé du joueur, 
 * des ennemis et des barricades.
 * @author
 * @version 1.0
 */
public class LevelState extends GameState {
    
    /** Le nombre de vies restant au joueur. */
    private int playerLifes;
    
    /** Le nombre de points que possède le joueur. */
    private int playerPoints;
    
    /** Les derniers points gagnés par le joueur. */
    private String lastPoints;
    
    /** Le joueur du jeu. */
    private Player player;
    
    /** La liste des barricades présente sur le plateau de jeu. */
    private List<Shelter> shelters;
    
    /** La vague d'ennemi présente sur le plateau. */
    private EnemyWave enemyWave;
    
    /** Le vaisseau de type "Mystery Ship" présent sur le plateau. */
    private MysteryShip mysteryShip;
    
    /** 
     * Permet de calculer les vitesses de déplcement des ennemis 
     * et leur probabilités de tir pour gérer la difficulté 
     * du jeu selon le niveau. 
     */
    private Difficulty difficulty;

    /**
     * Construit le plateau de jeu.
     * @param gameStateManager le GameStateManager qui doit gérer ce GameState.
     * @throws NullPointerException si <code>gameStateManager == null</code>.
     */
    public LevelState(GameStateManager gameStateManager)
    throws NullPointerException {
        super(gameStateManager);

        // Le joueur à par défaut 3 vies
        playerLifes = 3;
        
        // Il n'a pas de points au début du jeu
        playerPoints = 0;
        
        // Au debut le joueur n'a pas eu de points
        lastPoints = "";
        
        // Initialise la difficulté
        difficulty = new Difficulty();
    }
    
    /** 
     * Passe au niveau suivant : initialise une nouvelle vague d'ennemis pour le
     * niveau suivant avec un probabilité de tir, et 
     */
    private void nextLevel() {
        
        // Initialise le joueur et les ennemis
        initPlayer();
        initEnnemies();
        
        // Passe au niveau suivant
        difficulty.nextLevel();
    }

    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameStates.GameState#init()
     */
    @Override
    public void init() {
        initPlayer();
        initEnnemies();
        initShelters();
    }
    
    /** Créé un nouveau joueur sur le plateau de jeu. */
    private void initPlayer() {
        // Créé le joueur
        player = new Player();
    }
    
    /** Créé un nouvelle vague d'ennemis sur le plateau de jeu. */
    private void initEnnemies() {
        // Créé les ennemis
        enemyWave = new EnemyWave(difficulty.getProbShotsEnemies(), 
                                  difficulty.getSpeedEnemies());
    }
    
    /** Initialise les barricades sur le plateau de jeu. */
    private void initShelters() {
        shelters = new ArrayList<>();
        
        for (int i = 0 ; i < 4 ; i++) {
            Shelter toAdd = new Shelter();
            toAdd.translate(115 + i * 230, GamePanel.HEIGHT - 200);
            shelters.add(toAdd);
        }
    }
    
    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameStates.GameState#update()
     */
    @Override
    public void update() {
        
        // Si tous les ennemis sont mort, on passe au niveau suivant
        if (testAllEnemiesDead()) {
            nextLevel();
        }
        
        /*
         * Fait le calcul pour savoit s'il faut faire 
         * apparaître un Mystery Ship, s'il n'existe pas.
         */
        if (mysteryShip == null && Math.random() > 1 - difficulty.getProbSpawnMysteryShip()) {
            mysteryShip = new MysteryShip();
        }
        
        // Teste les collisions avec le joueur et met à jour le joueur.
        if (player != null && !player.isDead()) {
            checkCollisionWithPlayer();
            player.update();
        }
        
        // Détermine les collisions avec les barricades
        checkCollisionsWithShelters();
        
        /*
         * Teste si le joueur est mort.
         * Si oui lui enlève une vie, et recharge un nouveau joueur.
         * Si le joueur n'a plus de vie affiche l'écran de Game Over.
         */
        if (player != null && player.isDead()) {
            playerLifes--;
            if (playerLifes == 0) {
                gameStateManager.switchState(
                        GameStateManager.ID_GAME_OVER_STATE);
            } else {
                player = new Player();
            }
        }
        
        // Met à jour le Mystery Ship
        updateMysteryShip();
        
        // Met à jour les ennemis
        updateEnemyWave();
        
        /*
         * Si le joueur à 10 000 points, lui donne une vie, 
         * et remet à 0 ses points.
         */
        if (playerPoints >= 10000) {
            playerPoints = 0;
            playerLifes++;
        }
    }
    
    /** Met à jour le vaisseau de type "Mystery Ship". */
    private void updateMysteryShip() {
        // Ne pas mettre à jour si le Mystery Ship n'existe pas ou est mort
        if (mysteryShip != null && !mysteryShip.isDead()) {
            
            // Si il va à gauche et atteint le bord
            if (mysteryShip.isGoingToLeft() 
                && mysteryShip.getPosX() + mysteryShip.getWidth() <= 0) {
                
                // Le supprime
                mysteryShip = null;
            
            // Si il va à droite et atteint le bord
            } else if (mysteryShip.isGoingToRight() 
                       && mysteryShip.getPosX() + mysteryShip.getWidth() 
                           >= GamePanel.WIDTH) {
                
                // Le supprime
                mysteryShip = null;
            
            /*
             * Sinon il est dans l'écran donc on le met à jour 
             * et on vérifie les collisions.
             */
            } else {
                checkCollisionWithMysteryShip();
                mysteryShip.update();
            }
        } else if (mysteryShip != null && mysteryShip.isDead()) {
            mysteryShip = null;
        }
    }
    
    /** 
     * Vérifie les collisions des ennemis avec les projectiles 
     * du joueur et met à jour la vague d'ennemis
     */
    private void updateEnemyWave() {
        // Si la vague existe
        if (enemyWave != null) {
                        
            // Vérifie les collisions et la met à joueur
            checkCollisionsWithEnemies();
            enemyWave.update();
            
            // Vérifie que les ennemis ne sont pas arrivé sur terre.
            for (Enemy enemy : enemyWave) {
                if (enemy != null && !enemy.isDead()) {
                    if (enemy.getPosY() + enemy.getHeight() 
                            >= GamePanel.HEIGHT - 15) {
                        gameStateManager.switchState(
                                GameStateManager.ID_GAME_OVER_STATE);
                    }
                }
            }
        }
    }
    
    /**
     * Teste si tous les ennemis sont morts.
     * @return True si tous les ennemis sont morts, false sinon.
     */
    private boolean testAllEnemiesDead() {
        // Si tous les ennemis sont mort
        boolean allDead = true;
        
        // Si la vague existe on teste si tous les ennemis sont morts
        if (enemyWave != null) {
            for (Enemy enemy : enemyWave) {
                if (enemy != null && !enemy.isDead()) {
                    allDead = false;
                }
            }
        }
        
        return allDead;
    }
    
    /**
     * Teste si le joueur est en collision avec un projectile ennemi,
     * si oui lui inflige des dommages, et détruit le projectile. 
     */
    private void checkCollisionWithPlayer() {
        
        // Si le joueur existe, n'est pas mort et que la vague existe
        if (player != null && !player.isDead() && enemyWave != null) {
            
            // Parcours les ennemis
            for (Enemy enemy : enemyWave) {
                
                // Si l'ennemi existe
                if (enemy != null && !enemy.isDead()) {
                    
                    // Pour tous les projectiles du ennemi
                    for (Shot shot : enemy.getShots()) {
                        
                        // Si le projectile existe et qu'il est en collision
                        if (shot != null && !shot.isDead()
                            && shot.getCollisionBox()
                                       .intersects(player.getCollisionBox())) {
                            
                            // Détruit le projectile
                            shot.kill();
                            
                            // Joue le son d'explosion
                            Resources.playerDamageSound.play();
                            
                            // Tue le joueur
                            player.hit();
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Teste si les barricades sont en collision avec un projectile 
     * ennemi ou du joueur, si oui lui inflige des dommages, 
     * et détruit le projectile. 
     */
    private void checkCollisionsWithShelters() {
        
        // Si elles existent
        if (shelters != null) {
            
            // On les parcours
            for (Shelter shelter : shelters) {
                
                // Si la barricade existe
                if (shelter != null && !shelter.isDead()) {
                    
                    // Pour tous les ennemis, s'ils existent
                    for (Enemy enemy : enemyWave) {
                        if (enemy != null && !enemy.isDead()) {
                            
                            // Pour tout leur projectiles
                            for (Shot shot : enemy.getShots()) {
                                
                                // S'ils existent et qu'ils sont en collision
                                if (shot != null && !shot.isDead()
                                    && shot.getCollisionBox()
                                        .intersects(shelter.getCollisionBox())) {
                                    
                                    // Inflige des dommages à la barricade
                                    shelter.hit();
                                    
                                    // Joue le son
                                    Resources.shelterDamageSound.play();
                                    
                                    // Détruit le projectile
                                    shot.kill();
                                }
                            }
                        }
                    }
                    
                    // Si le joueur existe
                    if (player != null && !player.isDead()) {
                        
                        // Pour tout ses projectiles
                        for (Shot shot : player.getShots()) {
                            
                            // S'il existe et qu'il est en collision
                            if (shot != null && !shot.isDead()
                                && shot.getCollisionBox()
                                    .intersects(shelter.getCollisionBox())) {
                                
                                // Inflige des dommages à la barricade
                                shelter.hit();
                                
                                // Joue le son
                                Resources.shelterDamageSound.play();
                                
                                // Détruit le projectile
                                shot.kill();
                            }
                        }
                    }
                }
            }
        }
    }
    
    /** 
     * Teste si le "Mystery Ship" est en collision avec un projectile du joueur,
     * si oui lui inflige des dommages, donne les points correspondant
     * et détruit le projectile. 
     */
    private void checkCollisionWithMysteryShip() {
        // Si le joueur et le Mystery Ship existe
        if (player != null && !player.isDead() 
            && mysteryShip != null && !mysteryShip.isDead()) {
            
            // Pour tous les projectiles du joueur
            for (Shot shot : player.getShots()) {
                
                // Si le projectile existe et est en collision
                if (shot != null && !shot.isDead() 
                    && shot.getCollisionBox()
                               .intersects(mysteryShip.getCollisionBox())) {
                    
                    // Les points gagnés en tuant le Mystery Ship
                    int points = mysteryShip.getPointsOnDeath();
                    
                    // Ajout des points
                    playerPoints += points;
                    
                    // Affichage dans l'interface
                    lastPoints = "+ " + points;
                    
                    Resources.enemyDamageSound.play();
                    
                    // Tue le Mystery Ship et le projectile
                    mysteryShip.hit();
                    shot.kill();
                }
            }
        }
    }
    
    /**
     * Teste si les ennemis sont en collision avec un projectile du joueur,
     * si oui lui inflige des dommages, donne les points correspondant
     * et détruit le projectile. 
     */
    private void checkCollisionsWithEnemies() {
        // Pour tous les ennemis
        for (Enemy enemy : enemyWave) {
            
            // S'ils existent et que le joueur aussi
            if (enemy != null && !enemy.isDead() 
                && player != null && !player.isDead()) {
                
                // Pour tout ses projectiles
                for (Shot shot : player.getShots()) {
                    
                    // S'il existe et qu'il est en collision
                    if (shot != null && !shot.isDead()
                        && shot.getCollisionBox()
                            .intersects(enemy.getCollisionBox())) {
                        
                        // Récupère le nombre de points gagnés
                        int points = enemy.getPointsOnDeath();
                        
                        // On l'affiche sur l'interface
                        lastPoints = "+ " + points;
                        
                        // On l'ajoute aux points
                        playerPoints += points;
                        
                        // On joue le son
                        Resources.enemyDamageSound.play();
                        
                        // Inflige un de dommage à l'ennemi
                        enemy.hit();
                        
                        // Détruit le projectile
                        shot.kill();
                    }
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
        // Dessine tous les éléments du jeu
        drawHUD(graphics);
        drawPlayer(graphics);
        drawPlayerShots(graphics);
        drawShelters(graphics);
        drawEnemyWave(graphics);
        drawEnemiesShots(graphics);
        drawMysteryShip(graphics);
    }
    
    /**
     * Dessine l'interface utilisateur à l'écran.
     * @param graphics le contexte graphique où dessiner 
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

        // Définit la taille de la police et la police
        graphics.setFont(Resources.font.deriveFont(20f));
        graphics.drawString(playerPoints + "   Points   " + lastPoints, 10, 45);
        graphics.drawString("Level    " + difficulty.getLevel(), GamePanel.WIDTH / 2 - 60, 45);
                
        //graphics.drawString("Level    " + numLevel, GamePanel.WIDTH / 2 - 60, 45);
        graphics.drawString(playerLifes + "   Lifes", GamePanel.WIDTH - 110, 45);
    }
    
    /**
     * Dessine le joueur à l'écran.
     * @param graphics le contexte graphique où dessiner le joueur.
     */
    private void drawPlayer(Graphics2D graphics) {
        
        // Si il existe le dessine
        if (player != null) {
            graphics.drawImage(player.getFrame(), 
                    player.getPosX(), 
                    player.getPosY(), 
                    null);
        }
    }
    
    /**
     * Dessine les projectiles du joueur à l'écran.
     * @param graphics le contexte graphique 
     *                 où dessiner les projectiles du joueur.
     */
    private void drawPlayerShots(Graphics2D graphics) {
        // Test pour être sur qu'un projectile peut exister
        if (player != null && !player.isDead() && player.getShots() != null) {
            
            // Pour tous les projectiles
            for (Shot shot : player.getShots()) {
                
                /*
                 * A 50 sur l'axe y, il est en haut de l'écran 
                 * et ne doit plus être dessiné
                 */
                if (shot != null && !shot.isDead() && shot.getPosY() > 50) {
                    graphics.drawImage(shot.getFrame(), 
                            shot.getPosX(), 
                            shot.getPosY(), 
                            null);
                }
            }
        }
    }
    
    /**
     * Dessine les barricades à l'écran.
     * @param graphics le contexte graphique 
     *                 où dessiner les barricades.
     */
    private void drawShelters(Graphics2D graphics) {
        // S'il y a des barricades
        if (shelters != null) {
            
            // Pour toutes les barricades
            for (Shelter shelter : shelters) {
                
                // Si elle existe on la dessine
                if (shelter != null && !shelter.isDead()) {
                    graphics.drawImage(shelter.getFrame(), 
                                       shelter.getPosX(), 
                                       shelter.getPosY(), 
                                       null);
                }
            }
        }
    }
    
    /**
     * Dessine la vague d'ennemis à l'écran.
     * @param graphics le contexte gra^phique où dessiner la vague.
     */
    private void drawEnemyWave(Graphics2D graphics) {
        // Pour tous les ennemis si ils existent on les dessine
        if (enemyWave != null) {
            for (Enemy enemy : enemyWave) {
                if (enemy != null && !enemy.isDead()) {
                    graphics.drawImage(enemy.getFrame(), 
                                       enemy.getPosX(), 
                                       enemy.getPosY(), 
                                       null);
                }
            }
        }
    }
    
    /**
     * Dessine les projectiles des ennemis à l'écran.
     * @param graphics le contexte graphique où dessiner les projectiles.
     */
    private void drawEnemiesShots(Graphics2D graphics) {
        // Si la vague existe
        if (enemyWave != null) {
            
            // Pour tous les ennemis
            for (Enemy enemy : enemyWave) {
                
                // Pour tous les projectiles
                for (Shot shot : enemy.getShots()) {
                    
                    // Si le projectile existe on le dessine
                    if (shot != null && !shot.isDead() 
                            && enemy != null && !enemy.isDead()) {
                        graphics.drawImage(shot.getFrame(), 
                                shot.getPosX(),
                                shot.getPosY(), 
                                null);
                    }
                }
            }
        }
    }
    
    /**
     * Dessine le "Mystery Ship" à l'écran.
     * @param graphics le contexte graphique où dessiner le "Mystery Ship".
     */
    private void drawMysteryShip(Graphics2D graphics) {
        // Le dessine s'il existe
        if (mysteryShip != null && !mysteryShip.isDead()) {
            graphics.drawImage(mysteryShip.getFrame(),
                               mysteryShip.getPosX(), 
                               mysteryShip.getPosY(), 
                               null);
        }
    }

    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameStates.GameState#keyPressed(int)
     */
    @Override
    public void keyPressed(int keyCode) {
        // Bouge le joueur selon les touches pressées
        if (keyCode == KeyEvent.VK_LEFT) {
            player.setMovingLeft(true);
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            player.setMovingRight(true);
            
        // Met le jeu en pause
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
        // Le joueur tire
        if (keyCode == KeyEvent.VK_SPACE) {
            player.shoot();
        }
    }
}
