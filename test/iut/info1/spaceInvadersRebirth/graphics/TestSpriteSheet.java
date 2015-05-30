/*
 * TestSpriteSheet.java
 */
package iut.info1.spaceInvadersRebirth.graphics;

import static org.junit.Assert.*;

import java.io.IOException;

import iut.info1.spaceInvadersRebirth.res.Resources;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test de la classe {@link SpriteSheet}.
 * @author
 * @version 1.0
 */
public class TestSpriteSheet {

    private String spritePathSlice = "/shelter/shelters.png";

    private String spritePathNoSlice = "/HUD/back.png";

    private SpriteSheet spriteException;

    private SpriteSheet spriteConstruct;

    private SpriteSheet spriteWithSlice;

    private SpriteSheet spriteWithoutSlice;

    /**
     * Met en place l'environnement de test. 
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        spriteWithSlice = new SpriteSheet(Resources.loadImage(spritePathSlice));
        spriteWithoutSlice = new SpriteSheet(Resources.loadImage(spritePathNoSlice));
    }

    /**
     * Test method for {@link iut.info1.spaceInvadersRebirth.graphics.SpriteSheet#SpriteSheet(java.awt.image.BufferedImage)}.
     */
    @Test
    public void testSpriteSheet() {
        boolean nullPointer = false;
        try {
            spriteException = new SpriteSheet(null);
        } catch (NullPointerException e) {
            nullPointer = true;
        }

        assertTrue(nullPointer);

        boolean error = false;

        try {
            spriteConstruct = new SpriteSheet(Resources.loadImage(spritePathSlice));
        } catch (NullPointerException e) {
            // Pas testé ici
        } catch (IOException e) {
            // Pas testé ici
        }

        assertNotNull(spriteConstruct);
    }

    /**
     * Test method for {@link iut.info1.spaceInvadersRebirth.graphics.SpriteSheet#slice(int, int, int, int)}.
     */
    @Test
    public void testSlice() {
        int x = 5;
        int y = 16;
        int width = 45;
        int height = 24;

        spriteWithSlice.slice(x, y, width, height);

        assertNotNull(spriteWithSlice.getFrameAt(0));
    }

    /**
     * Test method for {@link iut.info1.spaceInvadersRebirth.graphics.SpriteSheet#getFrameAt(int)}.
     */
    @Test
    public void testGetFrameAt() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link iut.info1.spaceInvadersRebirth.graphics.SpriteSheet#getSprite()}.
     */
    @Test
    public void testGetSprite() {
        fail("Not yet implemented");
    }
}