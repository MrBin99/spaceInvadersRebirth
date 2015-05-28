/*
 * Launcher.java
 */
package iut.info1.spaceInvadersRebirth.launcher;

import iut.info1.spaceInvadersRebirth.gui.GameWindow;

/**
 * Classe contenant la méthode "main" pour lancer le jeu.
 * @author
 * @version 1.0
 */
public class Launcher {

    /**
     * Lance le jeu.
     * @param args non utilisés.
     */
    public static void main(String[] args) {
        new GameWindow().setVisible(true);
    }
}
