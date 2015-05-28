/*
 * Difficulty.java
 */
package iut.info1.spaceInvadersRebirth.gameStates;

/**
 * Classe gérant la difficulté du jeu selon le niveau courant.<br>
 * La difficulté augamente du niveau 1 au niveau 10, après cela, 
 * la difficulté reste constante.
 * @author
 * @version 1.0
 */
public class Difficulty {

    /** Le niveau de jeu courant pour calculer la difficulté. */
    private int level;
    
    /** 
     * La probabilité que les ennemis tirent en fonction du niveau de jeu,
     * augmente au fil des niveaux.
     */
    private float probShotsEnemies;
    
    /** 
     * La vitesse des ennemis selon le niveau de jeu, 
     * augmente au fil des niveaux. 
     */
    private int speedEnemies;
    
    /** 
     * Probabilité d'apparition d'un "Mystery Ship", 
     * diminue au fil des niveaux. 
     */
    private float probSpawnMysteryShip;
    
    /** Initialise les champs de difficultés pour le niveau 1. */
    public Difficulty() {
        // Niveau 1
        level = 0;
        nextLevel();
    }
    
    /**
     * Passe au niveau suivant et calcule les nouveaux 
     * critères de difficultés si le niveau suivant 
     * est inférieur ou égal au niveau 10.
     */
    public void nextLevel() {
        // Niveau suivant
        level++;
        
        // Difficulté croissante jusqu'au niveau 10
        if (level <= 10) {
            
            // Calcul des nouveaux critères de difficulté
            speedEnemies = (int) (0.4444f * level + 0.5556f);
            probShotsEnemies = 0.002f * level - 0.001f;
            probSpawnMysteryShip = 0.011f - 0.001f * level;
        }
    }
    
    /**
     * @return la vitesse des ennemis selon le niveau courant
     */
    public int getSpeedEnemies() {
        return this.speedEnemies;
    }
    
    /**
     * @return le numéro du niveau courant
     */
    public int getLevel() {
        return this.level;
    }
    
    /**
     * @return la probabilité de tir des ennemis selon le niveau courant.
     */
    public float getProbShotsEnemies() {
        return this.probShotsEnemies;
    }
    
    /**
     * @return la probabilité d'aparition d'un "Mystery Ship" 
     *         selon le niveau courant.
     */
    public float getProbSpawnMysteryShip() {
        return this.probSpawnMysteryShip;
    }
}
