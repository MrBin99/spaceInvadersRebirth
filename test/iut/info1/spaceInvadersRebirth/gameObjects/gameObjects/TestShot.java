/*
 * TestShot.java
 */
package iut.info1.spaceInvadersRebirth.gameObjects.gameObjects;

import static org.junit.Assert.*;

import java.awt.Rectangle;

import iut.info1.spaceInvadersRebirth.gameObjects.Player;
import iut.info1.spaceInvadersRebirth.gameObjects.Shot;
import iut.info1.spaceInvadersRebirth.gameObjects.enemies.LittleInvader;
import iut.info1.spaceInvadersRebirth.res.Resources;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test de la classe {@link Shot}.
 * @author
 * @version dev
 */
public class TestShot {

    private Player shooter;
    
    private LittleInvader enemy;
    
    private Shot shootPlay, shootEnemy;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        Resources.loadResources();
        enemy = new LittleInvader();
        shooter = new Player();
        shootPlay = new Shot(shooter);
        shootEnemy = new Shot(enemy);
       
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        shootPlay=null;
        shooter = null;
        shootEnemy = null;
        enemy = null;
    }

    /**
     * Test method for {@link iut.info1.spaceInvadersRebirth.gameObjects.Shot#Shot(iut.info1.spaceInvadersRebirth.gameObjects.GameObject)}.
     */
    @Test
    public void testShot() {
        assertEquals(10,shootPlay.getSpeed());
        //n'a pas trouvé le moyen de tester les translations
                
        
    }

    /**
     * Test method for {@link iut.info1.spaceInvadersRebirth.gameObjects.GameObject#translate(int, int)}.
     */
    @Test
    public void testTranslate() {
        int y = shooter.getPosY();
        shooter.translate(0,10);
        assertEquals(shooter.getPosY(),y+10);
        y = shooter.getPosY();
        shooter.translate(0,-5);
        assertEquals(shooter.getPosY(),y-5);
        
        int x = shooter.getPosX();
        shooter.translate(10,0);
        assertEquals(shooter.getPosX(),x+10);
        x = shooter.getPosX();
        shooter.translate(-5,0);
        assertEquals(shooter.getPosY(),x-5);
    }

    /**
     * Test method for {@link iut.info1.spaceInvadersRebirth.gameObjects.GameObject#kill()}.
     */
    @Test
    public void testKill() {
        shooter.kill();
        assertTrue(shooter.isDead());
    }

    /**
     * Test method for {@link iut.info1.spaceInvadersRebirth.gameObjects.GameObject#hit()}.
     */
    @Test
    public void testHit() {
        int health = shooter.getHealth();
        shooter.hit();
        assertEquals(health-1,shooter.getHealth());
    }

    /**
     * Test method for {@link iut.info1.spaceInvadersRebirth.gameObjects.GameObject#getHealth()}.
     */
    @Test
    public void testGetHealth() {
        assertEquals(shooter.getHealth(),1);
    }

    /**
     * Test method for {@link iut.info1.spaceInvadersRebirth.gameObjects.GameObject#isDead()}.
     */
    @Test
    public void testIsDead() {
        
        assertFalse(shooter.isDead());
        shooter.kill();
        assertTrue(shooter.isDead());
    }

    /**
     * Test method for {@link iut.info1.spaceInvadersRebirth.gameObjects.GameObject#getCollisionBox()}.
     */
    @Test
    public void testGetCollisionBox() {
        Rectangle rect = new Rectangle(shooter.getPosX(),shooter.getPosY(),shooter.getWidth(),shooter.getHeight());
        assertEquals(rect,shooter.getCollisionBox());
    }

    /**
     * Test method for {@link iut.info1.spaceInvadersRebirth.gameObjects.GameObject#getWidth()}.
     */
    @Test
    public void testGetWidth() {
        fail("Not yet implemented");
        //je sais pas comment faire ici 
    }

    /**
     * Test method for {@link iut.info1.spaceInvadersRebirth.gameObjects.GameObject#getHeight()}.
     */
    @Test
    public void testGetHeight() {
        fail("Not yet implemented");
        //ici non plus
    }

    /**
     * Test method for {@link iut.info1.spaceInvadersRebirth.gameObjects.GameObject#getPosX()}.
     */
    @Test
    public void testGetPosX() {
        int x = shooter.getPosX();
        shooter.translate(10, 0);
        assertEquals(x+10,shooter.getPosX());
    }

    /**
     * Test method for {@link iut.info1.spaceInvadersRebirth.gameObjects.GameObject#getPosY()}.
     */
    @Test
    public void testGetPosY() {
        int y = shooter.getPosY();
        shooter.translate(0, 10);
        assertEquals(y+10,shooter.getPosX());
    }

}
