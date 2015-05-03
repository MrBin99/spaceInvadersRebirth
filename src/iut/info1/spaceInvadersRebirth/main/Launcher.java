/*
 * Launcher.java
 */
package iut.info1.spaceInvadersRebirth.main;

import iut.info1.spaceInvadersRebirth.gui.GameWindow;

/**
 * Classe contenant la méthode "Main" permettant de lancer l'application.
 * @author MrBin99
 * @version dev
 */
public class Launcher {

    /**
     * Lance le jeu.
     * @param args non utilisés.
     */
    public static void main(String[] args) {
        new GameWindow().run();
    }
}
