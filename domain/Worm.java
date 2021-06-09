// Worm Game

// A class that creates a new worm composed of instances from the Piece class.
// It can also move and grow an existing worm.

package wormgame.domain;

import java.util.*;
import wormgame.Direction;

public class Worm extends Piece {
    private Direction wormDirection;
    private Piece originalPiece;
    private List<Piece> wormPieces;
    private boolean growCalled = false;
    
    public Worm(int originalX, int originalY, Direction wormDirection) {
        super(originalX, originalY);
        this.wormDirection = wormDirection;
        this.originalPiece =  new Piece(super.getX(), super.getY());

        this.wormPieces = new ArrayList<Piece>();
        this.wormPieces.add(this.originalPiece);
    }
    
    // Returns the worm's direction.    
    public Direction getDirection() {
        return this.wormDirection;
    }
    
    // Sets a new direction for our worm.
    public void setDirection(Direction direction) {
        this.wormDirection = direction;
    }
    
    // Returns the worm's length.    
    public int getLength() {
        return getPieces().size();
    }
    
    // Returns an ordered list of Piece objects the worm is composed of.     
    public List<Piece> getPieces() {
        return this.wormPieces;
    }
    
    // Moves the worm one piece forward.
    public void move() {
        if (this.wormPieces.size() < 3) {
            if (this.wormDirection == Direction.DOWN) {
                this.originalPiece = new Piece(super.getX(), super.addToY());
            } else if (this.wormDirection == Direction.UP) {
                this.originalPiece = new Piece(super.getX(), super.subtractFromY());
            } else if (this.wormDirection == Direction.LEFT) {
                this.originalPiece = new Piece(super.subtractFromX(), super.getY());
            } else if (this.wormDirection == Direction.RIGHT) {
                this.originalPiece = new Piece(super.addToX(), super.getY());
            }
            
            this.wormPieces.add(this.originalPiece);
            
        } else if ((this.wormPieces.size() == 3 && growCalled == false) || growCalled == false) {
            this.wormPieces.remove(0);
            
            if (this.wormDirection == Direction.DOWN) {
                this.originalPiece = new Piece(super.getX(), super.addToY());
            } else if (this.wormDirection == Direction.UP) {
                this.originalPiece = new Piece(super.getX(), super.subtractFromY());
            } else if (this.wormDirection == Direction.LEFT) {
                this.originalPiece = new Piece(super.subtractFromX(), super.getY());
            } else if (this.wormDirection == Direction.RIGHT) {
                this.originalPiece = new Piece(super.addToX(), super.getY());
            }
            
            this.wormPieces.add(this.originalPiece);
        }
        
        if (growCalled) {
            if (this.wormDirection == Direction.DOWN) {
                this.originalPiece = new Piece(super.getX(), super.addToY());
            } else if (this.wormDirection == Direction.UP) {
                this.originalPiece = new Piece(super.getX(), super.subtractFromY());
            } else if (this.wormDirection == Direction.LEFT) {
                this.originalPiece = new Piece(super.subtractFromX(), super.getY());
            } else if (this.wormDirection == Direction.RIGHT) {
                this.originalPiece = new Piece(super.addToX(), super.getY());
            }
            
            this.wormPieces.add(this.originalPiece);
            growCalled = false;
        }
    }
    
    // Grows the worm by one piece in conjunction with move().
    public void grow() {
        if (this.wormPieces.size() == 1 || this.wormPieces.size() == 2)
            growCalled = false;
        else
            growCalled = true;
    }
    
    // Checks whether the worm runs into the parameter piece.
    public boolean runsInto(Piece piece) {
        for (Piece p : this.wormPieces) {
            if (p.getX() == piece.getX() && p.getY() == piece.getY())
                return true;
        }
        
        return false;
    }
    
    // Checks whether the worm runs into itself.
    public boolean runsIntoItself() {
        Piece wormHead = this.originalPiece;
        
        for (int i = 0; i < this.wormPieces.size() - 1; i++) {
            if (wormHead.getX() == this.wormPieces.get(i).getX() && wormHead.getY() == this.wormPieces.get(i).getY())
                return true;
        }        
        
        return false;
    }
}
