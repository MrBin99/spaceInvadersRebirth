/*
 * Launcher.java
 */
package iut.info1.spaceInvadersRebirth.launcher;

import iut.info1.spaceInvadersRebirth.gui.GameWindow;

/**
 * Classe contenant la m�thode "main" pour lancer le jeu.
 * @author
 * @version 1.0
 */
public class Launcher {

    /**
     * Lance le jeu.
     * @param args non utilis�s.
     */
    public static void main(String[] args) {
        new GameWindow().setVisible(true);
    }
}
