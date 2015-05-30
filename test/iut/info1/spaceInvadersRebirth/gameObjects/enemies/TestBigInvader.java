/*
 * TestBigInvader.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects.enemies;

import static org.junit.Assert.*;
import iut.info1.spaceInvadersRebirth.res.Resources;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author
 * @version dev
 */
public class TestBigInvader {
    
    /** Le Big Invader utilisé pour les tests. */
    private BigInvader toTest;
    
    /** Le Big Invader pour tester le constructeur. */
    private BigInvader toTestConstructor;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        // Chargement du sprite
        Resources.loadResources();
        
        // Construit le BigInvader pour les tests
        toTest = new BigInvader();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        toTest = null;
        toTestConstructor = null;
    }

    /**
     * Test method for {@link iut.info1.spaceInvadersRebirth.gameObjects.enemies.BigInvader#getFrame()}.
     */
    @Test
    public void testGetFrame() {
        // Vérifie que la frame retournée est bien celle du Big Invader 
        assertEquals(toTest.getFrame(), Resources.bigInvaderSprite);
    }

    /**
     * Test method for {@link iut.info1.spaceInvadersRebirth.gameObjects.enemies.BigInvader#BigInvader()}.
     */
    @Test
    public void testBigInvader() {
        toTestConstructor = new BigInvader();
        
        // Vérifie que après construction le Big Invader n'est pas null
        assertNotEquals(toTestConstructor, null);
        assertFalse(toTestConstructor.isDead());
        
        // Vérifie que il possède les bon points
        assertEquals(toTestConstructor.getPointsOnDeath(), 
                     BigInvader.POINTS_ON_DEATH);
    }

    /**
     * Test method for {@link iut.info1.spaceInvadersRebirth.gameObjects.enemies.Enemy#shoot()}.
     */
    @Test
    public void testShoot() {
        // Vérifie que avant le tir, il n'y aucuns tirs
        assertTrue(toTest.getShots().isEmpty());
        
        // Il eput tirer et il tire
        toTest.setCanShoot(true);
        toTest.shoot();
        
        // Vérifie qu'un nouveau projectile a été ajouté
        assertNotNull(toTest.getShots());
        assertFalse(toTest.getShots().isEmpty());
    }

    /**
     * Test method for {@link iut.info1.spaceInvadersRebirth.gameObjects.enemies.Enemy#canShoot()}.
     */
    @Test
    public void testCanShoot() {
        toTest.setCanShoot(true);
        assertTrue(toTest.canShoot());
        toTest.setCanShoot(false);
        assertFalse(toTest.canShoot());
    }

    /**
     * Test method for {@link iut.info1.spaceInvadersRebirth.gameObjects.enemies.Enemy#getPointsOnDeath()}.
     */
    @Test
    public void testGetPointsOnDeath() {
        assertEquals(toTest.getPointsOnDeath(), BigInvader.POINTS_ON_DEATH);
    }

    /**
     * Test method for {@link iut.info1.spaceInvadersRebirth.gameObjects.enemies.Enemy#setCanShoot(boolean)}.
     */
    @Test
    public void testSetCanShoot() {
        toTest.setCanShoot(true);
        assertTrue(toTest.canShoot());
        toTest.setCanShoot(false);
        assertFalse(toTest.canShoot());
    }

    /**
     * Test method for {@link iut.info1.spaceInvadersRebirth.gameObjects.enemies.Enemy#getShots()}.
     */
    @Test
    public void testGetShots() {
        assertNotNull(toTest.getShots());
        assertTrue(toTest.getShots().isEmpty());
        toTest.setCanShoot(true);
        toTest.shoot();
        assertNotNull(toTest.getShots());
        assertFalse(toTest.getShots().isEmpty());
    }
}
