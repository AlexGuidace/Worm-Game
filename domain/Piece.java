// Worm Game

// A class that returns the x and y coordinates of pieces that make up our worm.

package wormgame.domain;

public class Piece {
    private int x;
    private int y;
    
    public Piece(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    // Returns the x-coordinate of the piece assigned in the constructor.    
    public int getX() {
        return this.x;
    }
    
    // Returns the x-coordinate of the piece assigned in the constructor. 
    public int getY() {
        return this.y;
    }
    
    public int addToX() {
        return this.x = this.x + 1;
    }
    
    public int subtractFromX() {
        return this.x = this.x - 1;
    }
    
    public int addToY() {
        return this.y = this.y + 1;
    }
    
    public int subtractFromY() {
        return this.y = this.y - 1;
    }
    
    // Returns true if the object has the same coordinates as the Piece
    // received as the argument.
    public boolean runsInto(Piece piece) {
        return this.x == piece.getX() && this.y == piece.getY();
   
    }
    
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }   
}
