// Worm Game

// A class that draws the worm and the apple for the game.

package wormgame.gui;

import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;

import wormgame.game.WormGame;
import wormgame.domain.Piece;

public class DrawingBoard extends JPanel implements Updatable {
    private WormGame wormGame;
    private int pieceLength;
    
    public DrawingBoard (WormGame wormGame, int pieceLength) {
        this.wormGame = wormGame;
        // Dimensions of the pieces our worm is composed of.        
        this.pieceLength = pieceLength;
    }
    
    // Draws our game components.
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        
        // Draws a worm.
        for (Piece piece : this.wormGame.getWorm().getPieces()) {
            graphics.fill3DRect(piece.getX() * this.pieceLength, piece.getY() * this.pieceLength , this.pieceLength, this.pieceLength, true);
            graphics.setColor(Color.BLACK);
        }
        
        // Draws an apple.
        graphics.fillOval(this.wormGame.getApple().getX() * this.pieceLength, this.wormGame.getApple().getY() * this.pieceLength, this.pieceLength, this.pieceLength);
        graphics.setColor(Color.RED);  
    }

    // Updates our board and components.
    @Override
    public void update() {
        super.repaint();
    }
}
