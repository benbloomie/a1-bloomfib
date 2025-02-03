package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RightHandAlgorithm implements MoveAlgorithm {
    private static final Logger logger = LogManager.getLogger();
    private StringBuffer pathSequence = new StringBuffer();
    private String rightHandPath;
    private int[][] positions = {{-1,0}, {0,1}, {1,0}, {0,-1}};  // move positions to represent each direction
    private int[] mazeExit;

    @Override
    public void findPath(Maze maze, DirectionAnalyzer directionAnalyzer) {
        mazeExit = maze.getExit();

        // loops until the explorer has reached the exit
        while (!isExplorerAtExit(directionAnalyzer.getPosition())) {
            boolean moved = false;

            // Case I: if an empty space is present in the direction that the explorer is facing, move the explorer forward
            if (isForwardFree(directionAnalyzer, maze)) {
                directionAnalyzer.moveExplorer('F');
                logger.trace("Moving F to new position: [{}, {}]", directionAnalyzer.getPosition()[0], directionAnalyzer.getPosition()[1]);
                pathSequence.append('F');
                moved = true;
            } 

            // Case II: if the space to the right of the explorer is empty, move the explorer right
            if (isRightFree(directionAnalyzer, maze)) {
                directionAnalyzer.moveExplorer('R');
                pathSequence.append("R");
                logger.trace("Moving R to new face: {}", directionAnalyzer.getFacingDirection());       
                moved = true; 
            }

            // Case III: the explorer is currently facing the wall
            if (!moved) {
                directionAnalyzer.moveExplorer('L');
                logger.trace("Moving L to new face: {}", directionAnalyzer.getFacingDirection());
                pathSequence.append('L');
            }
        }
        this.rightHandPath = pathSequence.toString();
    }

    private boolean isExplorerAtExit(int[] currentPosition) {
        // initializes arrays for the exit position and the position that the explorer ends at
        logger.trace("Exit position: {} ", this.mazeExit);
        logger.trace("Current position: {} ", currentPosition);
        // compares row and column positions after all path moves have been made --> boolean results determines if explorer escaped
        return (currentPosition[0] == this.mazeExit[0] && currentPosition[1] == this.mazeExit[1]);
    }

    private boolean isForwardFree(DirectionAnalyzer directionAnalyzer, Maze maze) {
        int currentRow = directionAnalyzer.getPosition()[0];
        int currentColumn = directionAnalyzer.getPosition()[1];

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
                if (i == directionAnalyzer.getFacingDirectionValue()) {
                    return true;
                }
            }
        } 
        return false;
    }

    private boolean isRightFree(DirectionAnalyzer directionAnalyzer, Maze maze) {
        int facingDirection = directionAnalyzer.getFacingDirectionValue();
        logger.trace("Facing direction " + facingDirection);
        int currentRow = directionAnalyzer.getPosition()[0];
        int currentColumn = directionAnalyzer.getPosition()[1];
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