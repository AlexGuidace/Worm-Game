// Worm Game

// A class that creates the worm game and gives it functionality. 

package wormgame.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;
import wormgame.Direction;
import wormgame.gui.Updatable;

import wormgame.domain.Worm;
import wormgame.domain.Apple;

public class WormGame extends Timer implements ActionListener {
    private int width;
    private int height;
    private boolean continues;
    private Updatable updatable;
    
    private Worm worm;
    private Apple apple;
    private Random random = new Random();
    private int xAppleCoordinate;
    private int yAppleCoordinate;

    // Creates the new game.
    public WormGame(int width, int height) {
        super(1000, null);
        
        int xWorm = width / 2;
        int yWorm = height / 2;
        
        worm = new Worm(xWorm, yWorm, Direction.DOWN);

        this.width = width;
        this.height = height;
        this.continues = true;
        
        xAppleCoordinate = random.nextInt(this.width);
        yAppleCoordinate = random.nextInt(this.height);
        
        while (xAppleCoordinate == xWorm && yAppleCoordinate == yWorm) {
            xAppleCoordinate = random.nextInt(this.width);
            yAppleCoordinate = random.nextInt(this.height);
        }
        
        this.apple = new Apple(xAppleCoordinate, yAppleCoordinate);

        addActionListener(this);
        setInitialDelay(2000);
    }
    
    // Returns the game's worm so it can be used in UserInterface.
    public Worm getWorm() {
        return this.worm;
    }
    
    // Sets the worm for the game.
    public void setWorm(Worm worm) {
        this.worm = worm;
    }
    
    // Returns the game's apple.
    public Apple getApple() {
        return this.apple;
    }
    
    // Sets the worm for the game.
    public void setApple(Apple apple) {
        this.apple = apple;
    }

    // If the worm runs into itself, we return false.    
    public boolean continues() {
        return continues;
    }
    
    // Updates the game so the worm can progress.
    public void setUpdatable(Updatable updatable) {
        this.updatable = updatable;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    // Logic for what happens when the worm is moved by the player.    
    @Override
    public void actionPerformed(ActionEvent ae) { 
        this.worm.move();
        
        if (this.worm.runsInto(this.apple)) {
            xAppleCoordinate = random.nextInt(this.width);
            yAppleCoordinate = random.nextInt(this.height);
        
            while (xAppleCoordinate == (this.width / 2) && yAppleCoordinate == (this.height / 2)) {
                xAppleCoordinate = random.nextInt(this.width);
                yAppleCoordinate = random.nextInt(this.height);
            }
            
            this.apple = new Apple(xAppleCoordinate, yAppleCoordinate);
        
            this.worm.grow();
        }
        
        if (this.worm.runsIntoItself()) {
            continues = false;
        }
        
        if (this.worm.getX() == this.width || this.worm.getY() == this.height || this.worm.getX() == 0 || this.worm.getY() == 0) {    
            continues = false;
        }

        this.updatable.update();
        
        // Our worm's velocity grows with respect to the worm's length.
        setDelay(1000 / this.worm.getLength());
        
        if (!continues) {
            return;
        }
    }
}
