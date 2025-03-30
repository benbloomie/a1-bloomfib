package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PositionManager extends MoveObserver {
    private static final Logger logger = LogManager.getLogger();
    private int[] position;
    private Maze maze;

    public PositionManager(Maze maze, int[] initialPosition, Subject subject) {
        this.position = initialPosition;
        this.maze = maze;
        this.subject = subject;
        subject.attach(this);
    }

    @Override
    public void update() {
        if (this.subject.getMove() =='F') {
            moveExplorer(this.subject.getDirection());;
            logger.trace("Moving F to new position: [{}, {}]", position[0], position[1]);
        }
    }

    public void moveExplorer(Direction direction) {
        direction.makeMove(position, maze);
    }  // keeping this method in for consistent unit testing

    // getter method to access the current position of the traveler
    public int[] getPosition() {
        return position;
    }


}