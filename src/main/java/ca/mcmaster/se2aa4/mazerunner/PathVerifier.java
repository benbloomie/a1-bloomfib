package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Arrays;

public class PathVerifier implements MazeExplorer {
    private static final Logger logger = LogManager.getLogger();
    private Maze maze;
    private PositionManager positionManager;
    private DirectionManager directionManager;
    private ExplorerMovement explorerMovement;
    private String moveSequence;

    public PathVerifier(char startingDirection, Maze maze, String moveSequence) {
        this.maze = maze;
        this.explorerMovement = new ExplorerMovement();
        this.positionManager = new PositionManager(maze, maze.getEntrance(), explorerMovement);
        this.directionManager = new DirectionManager(startingDirection, explorerMovement);
        this.moveSequence = moveSequence;
    }

    @Override
    public void exploreMaze() {
        // continue looping until every path movement has been read
        for (int i = 0; i < moveSequence.length(); i++) {
            char currentMove = moveSequence.charAt(i);
            // checks if the direction is accepted before processing the move
            if (currentMove == 'R' || currentMove == 'L' || currentMove == 'F') {
                explorerMovement.setState(currentMove, directionManager.getCurrentDirection());
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
        logger.trace("Exit position: {} ", exitPosition);
        
        int[] currentPosition = positionManager.getPosition();
        logger.info("Current position: {} ", currentPosition);
        // compares row and column positions after all path moves have been made --> boolean results determines if explorer escaped
        return Arrays.equals(currentPosition, exitPosition);
    }

    @Override
    public String getPathResult() {
        // returns a associated result based on the explorers final position
        if (isExplorerAtExit()) {
            return "correct path";
        }
        return "incorrect path";
    }
}