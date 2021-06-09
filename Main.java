// Worm Game

// A program emulating the classic video game, Snake. 

// The game is started from here.

package wormgame;

import javax.swing.SwingUtilities;
import wormgame.gui.UserInterface;
import wormgame.game.WormGame;

public class Main {

    public static void main(String[] args) {
       
        // Create a new WormGame.
        WormGame game = new WormGame(20, 20);

        // Create the WormGame's user interface.
        UserInterface ui = new UserInterface(game, 20);

        // Make all Swing structures run on the same AWT thread for thread safety.
        SwingUtilities.invokeLater(ui);

        // If the drawingBoard cannot update in the window, put the thread to sleep
        // until it does update.
        while (ui.getUpdatable() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("The drawing board hasn't been created yet.");
            }
        }

        // Set Updateable so the game can progress.
        game.setUpdatable(ui.getUpdatable());

        // Start the game.
        game.start();
    }
}
