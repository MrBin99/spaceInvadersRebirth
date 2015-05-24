/*
 * EnemyWave.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects.enemies;

import iut.info1.spaceInvadersRebirth.gameObjects.abilities.IMovable;
import iut.info1.spaceInvadersRebirth.gameStates.LevelState;

/**
 * Représente une vague d'ennemis arriavnt à chaque début de niveau.
 * @author
 * @version
 */
public class EnemyWave implements IMovable {
    
    /** Les ennemies contenus dans la vague. */
    private Enemy[][] enemies;
    
    /** Le LevelState où créer la vague. */
    private LevelState levelState;
    
   
    /** 
     * Créé un nouvelle vague d'ennemis .
     * @param levelState le LevelState où créer la vague.
     * @throws NullPointerException si levelState == null.
     */
    public EnemyWave(LevelState levelState) throws NullPointerException {
        // Precondition
        if (levelState == null) {
            throw new NullPointerException();
        }
        
        this.levelState = levelState;
        
        // La vague comporte 5 lignes de 11 ennemis
        enemies = new Enemy[5][11];
    }
    
    /** Créé les ennemis et initialise leurs positions dans la vague. */
    public void init() {
        
        int posX = 10;
        int posY = 120;
        
        
        for (int i = 0 ; i < enemies.length ; i++) {
            for (int j = 0 ; j < enemies[i].length ; j++) {
                if (i == 0) {
                    enemies[i][j] = new LittleInvader(levelState);
                } else if (i == 1 || i == 2) {
                    enemies[i][j] = new MediumInvader(levelState);
                } else {
                    enemies[i][j] = new BigInvader(levelState);
                    // donne le droit a la première ligne d'alien de tirer 
                    if( i == 4  ) {
                        enemies[i][j].setCanShoot(true);
                    }
                }
                
                enemies[i][j].translate(posX, posY);
                posX += 50;
            }
            posX = 10;
            posY += 40;
        }
    }
    
    /** Met à jour la vague d'ennemis. */
    public void update() {
        for (int i = 0 ; i < enemies.length ; i++) {
            for (int j = 0 ; j < enemies[i].length ; j ++ ) {
                if (enemies[i][j] != null ) {
                    enemies[i][j].update();  
                }
            }
        }
        enemyShoot();
        move();
        shoot();
        
        
    }
    
    /**
     * Permet de modifier le droit de tire des aliens 
     */
    private void enemyShoot() {
        for (int i = enemies.length-2 ; i>= 0 ; i--) {
            for (int j = 0 ; j < enemies[i].length ; j ++ ) {
                if (enemies[i+1][j] !=null && enemies[i+1][j].isDead()) {
                    enemies[i][j].setCanShoot(true);
                    enemies[i+1][j].setCanShoot(false);
                } 
                if( enemies[0][j]!= null && enemies[0][j].isDead()) {
                    enemies[0][j].setCanShoot(false);
                }
                 
            }
        }
    }
    /**
     * Permet a un alien de tirer de facon aléatoire 
     */
    private void shoot() {
        int random; 
        for (int i = 0 ; i < enemies.length ; i++ ) {
            for ( int j = 0 ; j < enemies[i].length ; j++ ) {
                random =  (int) (Math.random() * 500);
                if (enemies[i][j]!= null && enemies[i][j].isCanShoot() && random > 498) {
                    enemies[i][j].shoot();
                }
            }
        }
    }

    /* 
     * (non-Javadoc)
     * @see iut.info1.spaceInvadersRebirth.gameObjects.abilities.IMovable#move()
     */
    @Override
    public void move() {
        // TODO Auto-generated method stub
    }
    
    /**
     * @return les ennemis contenus danc cette vague.
     */
    public Enemy[][] getEnemies() {
        return this.enemies;
    }

    
    
    
}
