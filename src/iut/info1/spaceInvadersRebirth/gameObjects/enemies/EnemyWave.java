/*
 * EnemyWave.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects.enemies;

import iut.info1.spaceInvadersRebirth.gameObjects.MovableGameObject;
import iut.info1.spaceInvadersRebirth.gui.GamePanel;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author
 * @version
 */
public class EnemyWave extends MovableGameObject implements Iterable<Enemy> {
    
    /** Si l'ennemi va à gauche. */
    private boolean toLeft;
    
    /** Si l'ennemi va à droite. */
    private boolean toRight;
    
    /** La liste des ennemis présents dans la vague. */
    private List<Enemy> enemies;

    /** 
     * Probabilité que un ennemi pouvant tirer de la vague, tire.<br>
     * Cette valeur augmente au fil que le joueur passe de niveau en niveau.
     */
    private float probShoot;
    
    /**
     * Créé une nouvelle vague d'ennemis avec leur vitesse de déplacement 
     * et leur probabilité de tir.
     * @param probShoot la probabilité de tir des ennemis de la vague 
     *                  (valeur entre 0 et 1 non compris).
     * @param speed la vitesse de déplacement des ennemis dans cette vague.
     * @throws IllegalArgumentException si <code>probShoot</code> 
     *                                  n'est pas comrpis entre 0 et 1 ou si 
     *                                  <code>speed</code> est négatif ou nul.
     */
    public EnemyWave(float probShoot, int speed) 
    throws IllegalArgumentException {
        super();
        
        // Precondition
        if (probShoot <= 0 || probShoot >= 1) {
            throw new IllegalArgumentException("La probabilité de tir d'un ennemi "
                                               + "doit être comrpise entre 0 et 1.");
        } else if (speed <= 0) {
            throw new IllegalArgumentException("La vitesse d'un ennemi ne peut "
                                               + "être négative ou nulle.");
        }
        
        // Initialise la liste des ennemis.
        enemies = new ArrayList<>();
        
        // Initialise leur fréquence de tir.
        this.probShoot = probShoot;
        
        // Initialise la vitesse des ennemis
        this.speed = speed;
        
        // Initialise la postion des ennemis
        initPositions();
    }
    
    /**
     * Initialise la position de chaques ennemis de la liste 
     * pour former 5 lignes de 11 ennemis :
     * <ul><li>Une ligne de "Little Invader".</li>
     *     <li>Deux ligne de "Medium Invader".</li>
     *     <li>Deux ligne de "Big Invader".</li></ul>
     */
    private void initPositions() {
        // Position x et y du premier ennemi à placer (en haut à gauche)
        int posX = 0;
        int posY = 120;
        
        // Place la 1ere ligne d'ennemis
        for (int i = 0 ; i < 11 ; i++) {
            LittleInvader littleInvader = new LittleInvader();
            littleInvader.translate(posX, posY);
            littleInvader.setSpeed(speed);
            enemies.add(littleInvader);
            posX += 55;
        }
        
        // Réinitialise la position x et descend la position y
        posX = 0;
        posY += 40;
        
        // Place les 2 prochaines lignes
        for (int i = 0 ; i < 22 ; i++) {
            
            /*
             * Dès qu'on a placé un ligne réinitialise 
             * la position x et descend la position y
             */
            if (i == 11) {
                posX = 0;
                posY += 40;
            }
            
            MediumInvader mediumInvader = new MediumInvader();
            mediumInvader.translate(posX, posY);
            mediumInvader.setSpeed(speed);
            enemies.add(mediumInvader);
            posX += 55;
        }
        
        // Réinitialise la position x et descend la position y
        posX = 0;
        posY += 40;
        
        // Place les 2 dernières lignes
        for (int i = 0 ; i < 22 ; i++) {
            
            /*
             * Dès qu'on a placé un ligne réinitialise 
             * la position x et descend la position y
             */
            if (i == 11) {
                posX = 0;
                posY += 40;
            }
            
            // Créé les ennemies de la ligne
            BigInvader bigInvader = new BigInvader();
            
            // Les place sur l'écran
            bigInvader.translate(posX, posY);
            
            // Ajuste leur vitesse
            bigInvader.setSpeed(speed);
            
            if (i > 11) {
                // C'est la dernière ligne donc ils peuvent tirer
                bigInvader.setCanShoot(true);
            }
            
            enemies.add(bigInvader);
            posX += 55;
        }
    }
    
    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.GameObject#update()
     */
    @Override
    public void update() {
        // Met à jour les ennemies
        for (Enemy enemy : enemies) {
            if (enemy != null && !enemy.isDead()) {
                enemy.update();
            }
        }
        
        // Détermine qui peut tirer maintenant
        whoCanShoot();
        
        // Bouge la vague
        move();
        
        // Fait tirer les ennemis de la vague
        doEnemiesShoot();
    }
    
    /**
     * Détermine les ennemis de la vague qui n'ont aucuns 
     * ennemis devant eux donc ils peuvent tirer.
     */
    private void whoCanShoot() {
        for (int i = 1 ; i < enemies.size() ; i++) {
            if (enemies.get(enemies.size() - i).isDead() && i < 45) {
                enemies.get(enemies.size() - i - 11).setCanShoot(true);
            }
        }
    }
    
    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.MovableGameObject#move()
     */
    @Override
    public void move() {
        /*
         * Les ennemies qui ont la position x la plus à droite 
         * et la plus à gauche dans la vague 
         * pour déterminer par la suite la collision 
         * de la vague sur les bords de l'écran.
         */
        Enemy onRight = enemies.get(0);         // Ennemi en haut à gauche
        
        // Ennemi en bas à droite
        Enemy onLeft = enemies.get(enemies.size() - 1);
        
        // Pour tous les ennemis
        for (Enemy enemy : enemies) {
            
            /*
             * Si l'ennemi courant est plus à droite que onRight, 
             * met sa référence dans onRight.
             */
            if (enemy != null && !enemy.isDead() && enemy.getPosX() 
                + enemy.getWidth() > onRight.getPosX() + onRight.getWidth()) {
                onRight = enemy;
            }
            
            /*
             * Si l'ennemi courant est plus à gauche que onLeft, 
             * met sa référence dans onLeft.
             */
            if (enemy != null && !enemy.isDead() 
                && enemy.getPosX() < onLeft.getPosX()) {
                onLeft = enemy;
            }
        }
        
        // Pour faire bouger toute la vague.
        // On parcours la vague
        for (Enemy enemy : enemies) {
            
            /*
             * Si l'ennemi le plus à droite n'a pas touché encore le bord droite,
             * on déplace tout le monde vers la droite.
             */
            if (toRight && onRight.getPosX() + onRight.getWidth() < GamePanel.WIDTH) {
                toRight = true;
                enemy.setMovingRight(true);
                
            /*
             * Si l'ennemi le plus à gauche n'a pas touché encore le bord gauche,
             * on déplace tout le monde vers la gauche.
             */
            } else if (toLeft && onLeft.getPosX() > 0) {
                toLeft = true;
                enemy.setMovingLeft(true);
                
            /*
             * Dès que l'ennemi droit touche à droite ou l'ennemi gauche 
             * touche à gauche, on déplace d'un cran tout le monde vers le bas.
             */
            } else if ((onRight.getPosX() + onRight.getWidth() < GamePanel.WIDTH)
                       || (onLeft.getPosX() > 0)) {
               
                for (Enemy enemyDown : enemies) {
                    enemyDown.setMovingDown(true);
                    enemyDown.move();
                }
                if (toLeft) {
                    toRight = true;
                    toLeft = false;
                } else {
                    toLeft = true;
                    toRight = false;
                }
            }
            
            // On bouge l'ennemi.
            enemy.move();
        }
    }
    
    /**
     * Parcours tous les ennemis présents dans la vague, 
     * s'ils ne sont pas mort et qu'ils peuvent tirer, 
     * calcule leur probabilité de tir et les fait tirer.
     */
    private void doEnemiesShoot() {
        // Pour chaque ennemi
        for (Enemy enemy : enemies) {
            
            // Si il existe, n'est pas mort et peut tirer
            if (enemy != null && !enemy.isDead() && enemy.canShoot()) {
                
                // Calcul aléatoire pour savoir si il tire
                if (Math.random() > 1 - probShoot) {
                    enemy.shoot();
                }
            }
        }
    }

    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.GameObject#getFrame()
     */
    @Override
    public BufferedImage getFrame() {
        // GameObject composite
        return null;
    }
    
    /**
     * Setter sur probShoot
     * @param probShoot le probShoot à modifier
     */
    public void setProbShoot(float probShoot) {
        this.probShoot = probShoot;
    }
    
    /* 
     * (non-Javadoc)
     * @see java.lang.Iterable#iterator()
     */
    @Override
    public Iterator<Enemy> iterator() {
        return enemies.iterator();
    }
}
