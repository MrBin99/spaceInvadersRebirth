/*
 * TestMediumInvader.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects.enemies;

import static org.junit.Assert.*;
import iut.info1.spaceInvadersRebirth.res.Resources;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test de la classe {@link MediumInvader}.
 * @author
 * @version dev
 */
public class TestMediumInvader {
    
    /** Le Medium Invader utilisé pour les tests. */
    private MediumInvader toTest;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        // Chargement du sprite
        Resources.loadResources();
        
        // Construit le BigInvader pour les tests
        toTest = new MediumInvader();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        toTest = null;
    }

    /**
     * Test method for {@link iut.info1.spaceInvadersRebirth.gameObjects.MovableGameObject#move()}.
     */
    @Test
    public void testMove() {
        // Sauvegarde les coordonnées du début
        int x = toTest.getPosX();
        int y = toTest.getPosY();
        
        // Déplacement à droite
        toTest.setMovingRight(true);
        toTest.move();
        
        // Vérifie que les coordonnées x on changées mais pas y
        assertNotEquals(toTest.getPosX(), x);
        assertEquals(toTest.getPosY(), y);
        
        // Sauvegarde de la coordonnée x
        x = toTest.getPosX();
        
        // Déplacement vers le haut
        toTest.setMovingDown(true);
        toTest.move();
        
        // Vérifie que les coordonnées y on changées mais pas x
        assertEquals(toTest.getPosX(), x);
        assertNotEquals(toTest.getPosY(), y);
    }

    /**
     * Test method for {@link iut.info1.spaceInvadersRebirth.gameObjects.MovableGameObject#isMovingRight()}.
     */
    @Test
    public void testIsMovingRight() {
        toTest.setMovingRight(true);
        assertTrue(toTest.isMovingRight());
    }

    /**
     * Test method for {@link iut.info1.spaceInvadersRebirth.gameObjects.MovableGameObject#setMovingRight(boolean)}.
     */
    @Test
    public void testSetMovingRight() {
        toTest.setMovingRight(true);
        assertTrue(toTest.isMovingRight());
    }

    /**
     * Test method for {@link iut.info1.spaceInvadersRebirth.gameObjects.MovableGameObject#isMovingLeft()}.
     */
    @Test
    public void testIsMovingLeft() {
        toTest.setMovingLeft(true);
        assertTrue(toTest.isMovingLeft());
    }

    /**
     * Test method for {@link iut.info1.spaceInvadersRebirth.gameObjects.MovableGameObject#setMovingLeft(boolean)}.
     */
    @Test
    public void testSetMovingLeft() {
        toTest.setMovingLeft(true);
        assertTrue(toTest.isMovingLeft());
    }

    /**
     * Test method for {@link iut.info1.spaceInvadersRebirth.gameObjects.MovableGameObject#isMovingUp()}.
     */
    @Test
    public void testIsMovingUp() {
        toTest.setMovingUp(true);
        assertTrue(toTest.isMovingUp());
    }

    /**
     * Test method for {@link iut.info1.spaceInvadersRebirth.gameObjects.MovableGameObject#setMovingUp(boolean)}.
     */
    @Test
    public void testSetMovingUp() {
        toTest.setMovingUp(true);
        assertTrue(toTest.isMovingUp());
    }

    /**
     * Test method for {@link iut.info1.spaceInvadersRebirth.gameObjects.MovableGameObject#isMovingDown()}.
     */
    @Test
    public void testIsMovingDown() {
        toTest.setMovingDown(true);
        assertTrue(toTest.isMovingDown());
    }

    /**
     * Test method for {@link iut.info1.spaceInvadersRebirth.gameObjects.MovableGameObject#setMovingDown(boolean)}.
     */
    @Test
    public void testSetMovingDown() {
        toTest.setMovingDown(true);
        assertTrue(toTest.isMovingDown());
    }

    /**
     * Test method for {@link iut.info1.spaceInvadersRebirth.gameObjects.MovableGameObject#getSpeed()}.
     */
    @Test
    public void testGetSpeed() {
        assertEquals(toTest.getSpeed(), 1);
    }

    /**
     * Test method for {@link iut.info1.spaceInvadersRebirth.gameObjects.MovableGameObject#setSpeed(int)}.
     */
    @Test
    public void testSetSpeed() {
        toTest.setSpeed(5);
        assertEquals(toTest.getSpeed(), 5);
    }
}
