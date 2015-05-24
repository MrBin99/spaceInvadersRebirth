/*
 * EnemyWave.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects.enemies;

import iut.info1.spaceInvadersRebirth.gameObjects.abilities.IMovable;
import iut.info1.spaceInvadersRebirth.gameStates.LevelState;

/**
 * Repr�sente une vague d'ennemis arriavnt � chaque d�but de niveau.
 * @author
 * @version
 */
public class EnemyWave implements IMovable {
    
    /** Les ennemies contenus dans la vague. */
    private Enemy[][] enemies;
    
    /** Le LevelState o� cr�er la vague. */
    private LevelState levelState;
    
    /** 
     * Cr�� un nouvelle vague d'ennemis .
     * @param levelState le LevelState o� cr�er la vague.
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
    
    /** Cr�� les ennemis et initialise leurs positions dans la vague. */
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
                }
                
                enemies[i][j].translate(posX, posY);
                posX += 50;
            }
            posX = 10;
            posY += 40;
        }
    }
    
    /** Met � jour la vague d'ennemis. */
    public void update() {
        move();
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
