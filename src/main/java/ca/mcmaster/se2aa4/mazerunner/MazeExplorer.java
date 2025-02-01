package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeExplorer {
    private static final Logger logger = LogManager.getLogger();
    private Maze maze;
    private DirectionAnalyzer directionAnalyzer;
 
    public MazeExplorer(Maze maze) {
        this.maze = maze;
        directionAnalyzer = new DirectionAnalyzer('E', maze, maze.getEntrance('E'));    // using east facing direction as starting posiiton 
    }

    public void verifyInputPath(String moveSequence) {
        // continue looping until every path movement has been read
        for (int i = 0; i < moveSequence.length(); i++) {
            char currentMove = moveSequence.charAt(i);
            // checks if the direction is accepted before processing the move
            if (currentMove != 'F' && currentMove != 'R' && currentMove != 'L') {
                logger.error("Error: Cannot make the move {}", currentMove);
                throw new IllegalArgumentException("Invalid move in path: " + currentMove);
            }
            directionAnalyzer.moveExplorer(currentMove);  // passes through each move to update the position
            logger.trace("Move {}: {} in direction {}", i + 1, currentMove, directionAnalyzer.getFacingDirection()); // log each move
        }
        didExplorerEscape();  // checks if the explorer successfully completed the maze 
    }

    private boolean verifyPath() {
        // initializes arrays for the exit position and the position that the explorer ends at
        int[] exitPosition = maze.getExit();
        int[] currentPosition = directionAnalyzer.getPosition();
        logger.trace("Exit position: {} ", exitPosition);
        logger.info("Current position: {} ", currentPosition);
        // compares row and column positions after all path moves have been made
        if (currentPosition[0] == exitPosition[0] && currentPosition[1] == exitPosition[1]) {
            return true;  // if positions match, the path is valid
        }
        return false;
    }

    private void didExplorerEscape() {
        // verifies if the path entered from the command-line is valid
        if (verifyPath()) {
            System.out.println("correct path");
        }
        else {
            System.out.println("incorrect path");
        }
    }

    public String findPath() {
        StringBuffer pathSequence = new StringBuffer();
        int[][] positions = {{-1,0}, {0,1}, {1,0}, {0,-1}};  // move positions to represent each direction
        
        while (!verifyPath()) {
            int currentRow = directionAnalyzer.getPosition()[0];
            int currentColumn = directionAnalyzer.getPosition()[1];
            boolean moved = false;

            // Case I: if an empty space is present in the direction that the explorer is facing, move the explorer forward
            for (int i = 0; i <= 3; i++) {
                // updates row and column position for each possible movement position
                int newRow = currentRow + positions[i][0];
                int newColumn = currentColumn + positions[i][1];

                // checks if the new row and new column are valid before proceeding
                if ((newColumn < 0 && newColumn >= maze.getLength()) && (newRow < 0 && newRow >= maze.getHeight())) {
                    logger.error("Position out of bounds.");
                }
                // if column and row values are valid, proceed forward 
                else if (maze.getTile(newRow, newColumn) == ' ') {
                    if (i == directionAnalyzer.getFacingDirectionValue()) {
                        directionAnalyzer.moveExplorer('F');
                        logger.info("Moving F to new position: [{}, {}]", directionAnalyzer.getPosition()[0], directionAnalyzer.getPosition()[1]);
                        pathSequence.append('F');
                        moved = true;
                        break;
                    }
                }
            }

            // Case II: if the space to the right of the explorer is empty, move the explorer right
            int facingDirection = directionAnalyzer.getFacingDirectionValue();
            logger.info("facing direction " + facingDirection);
            if (rightIsEmpty(facingDirection, (facingDirection + 2) % 2, (facingDirection + 1) % 2)) {
                pathSequence.append('R');
                logger.info("Moving R to new face: {}", directionAnalyzer.getFacingDirection());       
                moved = true;          
            }
            // Case III: the explorer is currently facing the wall
            if (!moved) {
                directionAnalyzer.moveExplorer('L');
                logger.info("Moving L to new face: {}", directionAnalyzer.getFacingDirection());
                pathSequence.append('L');
            }
        }
        return pathSequence.toString();
    }

    private boolean rightIsEmpty(int facingDirection, int rowMovement, int columnMovement) {
        int currentRow = directionAnalyzer.getPosition()[0];
        int currentColumn = directionAnalyzer.getPosition()[1];

        // if the explorer is facing north or east, add the values
        if (facingDirection < 2 && maze.getTile(currentRow + rowMovement, currentColumn + columnMovement) == ' ') {
            directionAnalyzer.moveExplorer('R');
            return true; 
        }
        // if the explorer is facing west or south, subtract the values
        else if (facingDirection >= 2 && maze.getTile(currentRow - rowMovement, currentColumn - columnMovement) == ' ') {
            directionAnalyzer.moveExplorer('R');
            return true;
        }
        // if the right sqaure is not open, return false
        return false;
    }

}