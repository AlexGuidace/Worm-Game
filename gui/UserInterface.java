// Worm Game

// Creates the window and its components for the game to run in.

package wormgame.gui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import wormgame.game.WormGame;

public class UserInterface implements Runnable 
{
    private JFrame frame;
    private WormGame game;
    private int sideLength;
    private DrawingBoard drawingBoard;

    public UserInterface(WormGame game, int sideLength) {
        this.game = game;
        this.sideLength = sideLength;
    }
    
    // Creates the window for the game to run in.
    @Override
    public void run() {
        frame = new JFrame("Worm Game");
        int width = (game.getWidth() + 1) * sideLength + 10;
        int height = (game.getHeight() + 2) * sideLength + 10;

        frame.setPreferredSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    // Adds the worm and apple components to the window. Makes the worm moveable.
    public void createComponents(Container container) {
        drawingBoard = new DrawingBoard(this.game, sideLength);
        container.add(drawingBoard);
        
        KeyboardListener keyboardListener = new KeyboardListener(this.game.getWorm());
        frame.addKeyListener(keyboardListener); 
    }
    
    // Gets the updated drawingBoard.
    public Updatable getUpdatable() {
        return drawingBoard;
    }

    public JFrame getFrame() {
        return frame;
    }
}
