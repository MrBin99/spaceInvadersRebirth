/*
 * TestShelter.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects.gameObjects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import iut.info1.spaceInvadersRebirth.gameObjects.Shelter;
import iut.info1.spaceInvadersRebirth.res.Resources;

import org.junit.Test;

/**
 * Test de la classe {@link Shelter}.
 * @author
 * @version dev
 */
public class TestShelter {
    
    /** La barricade pour les tests. */
    private Shelter toTest;

    /**
     * Test method for {@link iut.info1.spaceInvadersRebirth.gameObjects.Shelter#Shelter()}.
     */
    @Test
    public void testShelter() {
        Resources.loadResources();
        assertNull(toTest);
        toTest = new Shelter();
        assertEquals(toTest.getHealth(), 10);
    }
}
