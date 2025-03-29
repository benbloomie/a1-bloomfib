package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DirectionManager extends MoveObserver {
    private static final Logger logger = LogManager.getLogger();
    private Direction currentDirection;

    public DirectionManager(char startingDirection, Subject subject) {
        setInitialDirection(startingDirection);
        this.subject = subject;
        subject.attach(this);
    }

    public void setInitialDirection(char startingDirection) {
        Direction[] directionsInEnum = Direction.values();
        
        for (Direction direction: directionsInEnum) {
            // if the first character of the direction matches the starting direction, intialize that as the starting direction
            if (direction.name().charAt(0) == startingDirection) {
                this.currentDirection = direction;
                return;
            }
        }
        throw new IllegalArgumentException("Cannot initialize starting direction to be " + startingDirection);
    }

    // method to move the explorer based on the provided move sequence
    public void turnExplorer(char move) {
        // uses the enum methods to update the facing direction when turning 
        if (move =='L') {
            currentDirection = currentDirection.turnLeft();  
            logger.trace("Moving L to new face: {}", getFacingDirection());
        }
        else if (move == 'R') {
            currentDirection = currentDirection.turnRight();
            logger.trace("Moving R to new face: {}", getFacingDirection());
        }
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    // getter method to retrieve the direction as its char representation
    public char getFacingDirection() {   
        return currentDirection.name().charAt(0);
    }

    // getter method to retrieve the value of the facing direction
    public int getFacingDirectionValue() {
        return currentDirection.ordinal();
    }

    @Override
    public void update() {
        char move = this.subject.getMove();
        if (move == 'L' || move == 'R') {
            turnExplorer(move);
            logger.trace("Moving L to new face: {}", getFacingDirection());
        }
    }
}
