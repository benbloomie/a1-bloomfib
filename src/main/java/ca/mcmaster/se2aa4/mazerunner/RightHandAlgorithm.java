package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RightHandAlgorithm implements MoveAlgorithm {
    private static final Logger logger = LogManager.getLogger();
    private Maze maze;
    private String rightHandPath;
    private int[][] positions = {{-1,0}, {0,1}, {1,0}, {0,-1}};  // move positions to represent each direction
    private PositionManager positionManager;
    private DirectionManager directionManager;

    public RightHandAlgorithm(Maze maze) {
        this.maze = maze;
    }

    @Override
    public void setManagers(PositionManager positionManager, DirectionManager directionManager) {
        this.positionManager = positionManager;
        this.directionManager = directionManager;
    }

    @Override
    public void findPath() {
        StringBuffer pathSequence = new StringBuffer();

        // loops until the explorer has reached the exit
        while (!isExplorerAtExit()) {
            boolean moved = false;

            // Case I: if an empty space is present in the direction that the explorer is facing, move the explorer forward
            if (isForwardFree()) {
                positionManager.moveExplorer(directionManager.getCurrentDirection());
                logger.trace("Moving F to new position: [{}, {}]", positionManager.getPosition()[0], positionManager.getPosition()[1]);
                pathSequence.append('F');
                moved = true;
            } 

            // Case II: if the space to the right of the explorer is empty, move the explorer right
            if (isRightFree()) {
                directionManager.turnExplorer('R');
                pathSequence.append("R");
                logger.trace("Moving R to new face: {}", directionManager.getFacingDirection());       
                moved = true; 
            }

            // Case III: the explorer is currently facing the wall
            if (!moved) {
                directionManager.turnExplorer('L');
                logger.trace("Moving L to new face: {}", directionManager.getFacingDirection());
                pathSequence.append('L');
            }
        }
        this.rightHandPath = pathSequence.toString();
    }

    private boolean isExplorerAtExit() {
        int[] currentPosition = positionManager.getPosition();
        // initializes arrays for the exit position and the position that the explorer ends at
        logger.trace("Exit position: {} ", maze.getExit());
        logger.trace("Current position: {} ", currentPosition);
        // compares row and column positions after all path moves have been made --> boolean results determines if explorer escaped
        return (currentPosition[0] == maze.getExit()[0] && currentPosition[1] == maze.getExit()[1]);
    }

    private boolean isForwardFree() {
        int currentRow = positionManager.getPosition()[0];
        int currentColumn = positionManager.getPosition()[1];

        for (int i = 0; i <= 3; i++) {
            // updates row and column position for each possible movement position
            int newRow = currentRow + this.positions[i][0];
            int newColumn = currentColumn + this.positions[i][1];

            // checks if the new row and new column are valid before proceeding
            if ((newColumn < 0 || newColumn >= maze.getLength()) || (newRow < 0 || newRow >= maze.getHeight())) {
                logger.error("Position out of bounds.");
            }
            // if column and row values are valid, proceed forward 
            else if (maze.getTile(newRow, newColumn) == ' ') {
                if (i == directionManager.getFacingDirectionValue()) {
                    return true;
                }
            }
        } 
        return false;
    }

    private boolean isRightFree() {
        int facingDirection = directionManager.getFacingDirectionValue();
        logger.trace("Facing direction " + facingDirection);
        int currentRow = positionManager.getPosition()[0];
        int currentColumn = positionManager.getPosition()[1];
        // calculates which way the explorer can move based on the direction it faces
        int rowMovement = (facingDirection + 2) % 2;  // facing north and south means its row can move
        int columnMovement = (facingDirection + 1) % 2;  // facing east and west means its column can move

        // checks the right tile --> if the explorer is facing north or east, add the values OR if the explorer is facing west or south, subtract the values
        if ((facingDirection < 2 && maze.getTile(currentRow + rowMovement, currentColumn + columnMovement) == ' ') 
            || (facingDirection >= 2 && maze.getTile(currentRow - rowMovement, currentColumn - columnMovement) == ' ')) {
            return true; 
        }
        return false;  // if the right sqaure is not open, return false
    }
    
    @Override
    public String getAlgorithmPath() {
        return this.rightHandPath;
    }
}