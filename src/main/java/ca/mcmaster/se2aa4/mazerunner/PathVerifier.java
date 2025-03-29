package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathVerifier implements MazeExplorer {
    private static final Logger logger = LogManager.getLogger();
    private Maze maze;
    private PositionManager positionManager;
    private DirectionManager directionManager;
    private String moveSequence;

    public PathVerifier(char startingDirection, Maze maze, String moveSequence) {
        this.maze = maze;
        this.positionManager = new PositionManager(maze, maze.getEntrance());
        this.directionManager = new DirectionManager(startingDirection);
        this.moveSequence = moveSequence;
    }

    @Override
    public void exploreMaze() {
        // continue looping until every path movement has been read
        for (int i = 0; i < moveSequence.length(); i++) {
            char currentMove = moveSequence.charAt(i);
            // checks if the direction is accepted before processing the move
            if (currentMove == 'R' || currentMove == 'L') {
                directionManager.turnExplorer(currentMove);  // passes through each move to update the position
                logger.trace("Move {}: {} in direction {}", i + 1, currentMove, directionManager.getFacingDirection()); // log each move
            }
            else if (currentMove == 'F') {
                positionManager.moveExplorer(directionManager.getCurrentDirection());
            }
            // if the current character is a space, do nothing
            else if (currentMove != ' ') {
                logger.error("Error: Cannot make the move {}", currentMove);
                throw new IllegalArgumentException("Invalid move in path: " + currentMove);
            }
        }
    }
    
    private boolean isExplorerAtExit() {
        // initializes arrays for the exit position and the position that the explorer ends at
        int[] exitPosition = maze.getExit();
        int[] currentPosition = positionManager.getPosition();
        logger.trace("Exit position: {} ", exitPosition);
        logger.info("Current position: {} ", currentPosition);
        // compares row and column positions after all path moves have been made --> boolean results determines if explorer escaped
        return (currentPosition[0] == exitPosition[0] && currentPosition[1] == exitPosition[1]);
    }

    @Override
    public String getPathResult() {
        // returns a associated result based on the explorers final position
        if (isExplorerAtExit()) {
            return "correct path";
        }
        return "incorrect path";
    }

    public void setNewPath(String moveSequence) {
        this.moveSequence = moveSequence;
    }
}