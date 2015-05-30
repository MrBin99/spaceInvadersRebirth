/*
 * TestPlayer.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects.gameObjects;

import static org.junit.Assert.*;
import iut.info1.spaceInvadersRebirth.gameObjects.Player;
import iut.info1.spaceInvadersRebirth.res.Resources;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test de la classe {@link Player}.
 * @author
 * @version dev
 */
public class TestPlayer {
    
    /** Un joueur pour les tests. */
    private Player toTest;
    
    /** Un joueur pour tester le constructeur. */
    private Player toTestConstructor;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        Resources.loadResources();
        toTest = new Player();
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
     * Test method for {@link iut.info1.spaceInvadersRebirth.gameObjects.Player#getFrame()}.
     */
    @Test
    public void testGetFrame() {
        assertEquals(toTest.getFrame(), Resources.playerSprite);
    }

    /**
     * Test method for {@link iut.info1.spaceInvadersRebirth.gameObjects.Player#Player()}.
     */
    @Test
    public void testPlayer() {
        assertNull(toTestConstructor);
        toTestConstructor = new Player();
        assertNotNull(toTestConstructor);
        assertEquals(toTestConstructor.getSpeed(), 10);
        assertNotNull(toTestConstructor.getShots());
        assertTrue(toTestConstructor.getShots().isEmpty());
    }
}
