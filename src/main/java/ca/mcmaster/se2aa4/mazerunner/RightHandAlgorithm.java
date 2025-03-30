package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Arrays;

public class RightHandAlgorithm implements MoveAlgorithm {
    private static final Logger logger = LogManager.getLogger();
    private Maze maze;
    private int[][] positions = {{-1,0}, {0,1}, {1,0}, {0,-1}};  // move positions to represent each direction
    private PositionManager positionManager;
    private DirectionManager directionManager;
    private ExplorerState explorerState;
    private PathManager pathManager;
    private ExplorerMovement movement;

    public RightHandAlgorithm(char startingDirection, Maze maze) {
        this.maze = maze;
        this.explorerState = new ExplorerState();
        this.positionManager = new PositionManager(maze, maze.getEntrance(), explorerState);
        this.directionManager = new DirectionManager(startingDirection, explorerState);
        this.pathManager = new PathManager(explorerState);    
        this.movement = new ExplorerMovement();
    }

    @Override
    public void findPath() {
        // loops until the explorer has reached the exit
        while (!isExplorerAtExit()) {

            // Case I: if the space to the right of the explorer is empty, move the explorer right
            if (isRightFree()) { 
                MoveCommand rightTurn = new TurnRightCommand(explorerState, directionManager);
                movement.makeMove(rightTurn);
                MoveCommand moveForward = new MoveForwardCommand(explorerState, directionManager);
                movement.makeMove(moveForward);
            }
            // Case II: if an empty space is present in the direction that the explorer is facing, move the explorer forward
            else if (isForwardFree()) {
                MoveCommand moveForward = new MoveForwardCommand(explorerState, directionManager);
                movement.makeMove(moveForward);
            } 
            // Case III: the explorer is currently facing the wall
            else {
                MoveCommand leftTurn = new TurnLeftCommand(explorerState, directionManager);
                movement.makeMove(leftTurn);
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

    private boolean isForwardFree() {
        int currentRow = positionManager.getPosition()[0];
        int currentColumn = positionManager.getPosition()[1];
    
        // only checks the new row and column position based on which way the explorer is facing
        int facingDirection = directionManager.getFacingDirectionValue();
        int newRow = currentRow + positions[facingDirection][0];
        int newColumn = currentColumn + positions[facingDirection][1];
    
        // only returns true under the two cases
        return isPositionValid(newRow, newColumn) && (maze.getTile(newRow, newColumn) == ' ');
    }

    private boolean isRightFree() {
        int facingDirection = directionManager.getFacingDirectionValue();
        logger.trace("Facing direction " + facingDirection);
    
        int currentRow = positionManager.getPosition()[0];
        int currentColumn = positionManager.getPosition()[1];
    
        // computes the direction to the right of the explorer, and gets the position
        int rightDirection = (facingDirection + 1) % 4;
        int newRow = currentRow + positions[rightDirection][0];
        int newColumn = currentColumn + positions[rightDirection][1];
    
        return isPositionValid(newRow, newColumn) && (maze.getTile(newRow, newColumn) == ' ');
    }
    
    // method to ensure that the position is within the maze
    private boolean isPositionValid(int row, int column) {
        return (row >= 0 && row < maze.getHeight()) && (column >= 0 && column < maze.getLength());
    }
    
    @Override
    public String getAlgorithmPath() {
        return pathManager.getPath();
    }
}