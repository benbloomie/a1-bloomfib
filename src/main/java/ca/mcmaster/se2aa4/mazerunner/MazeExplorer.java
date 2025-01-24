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
        if (moveSequence == null || moveSequence.isEmpty()) {
            throw new IllegalArgumentException("Path cannot be empty.");
        }
        moveSequence = checkPathFormat(moveSequence);

        // continue looping until every path movement has been read
        for (int i = 0; i < moveSequence.length(); i++) {
            char currentMove = moveSequence.charAt(i);
            if (currentMove != 'F' && currentMove != 'R' && currentMove != 'L') {
                logger.error("Error: Cannot make the move {}", currentMove);
                throw new IllegalArgumentException("Invalid move in path: " + currentMove);
            }
            directionAnalyzer.moveExplorer(currentMove);  // passes through each move to update the position
            logger.trace("Move {}: {} in direction {}", i + 1, currentMove, directionAnalyzer.getFacingDirection()); // log each move
        }
        // checks if the explorer successfully completed the maze 
        if (verifyMaze()) {
            System.out.println("Explorer has escaped the maze!");
        }
        else {
            System.out.println("Explorer did not reach the exit.");
        }
    }

    public boolean verifyMaze() {
        // initializes arrays for the exit position and the position that the explorer ends at
        int[] exitPosition = maze.getExit();
        int[] currentPosition = directionAnalyzer.getPosition();
        logger.info("Exit position: {} ", exitPosition);
        logger.info("Final position: {} ", currentPosition);
        // compares row and column positions after all path moves have been made
        if (currentPosition[0] == exitPosition[0] && currentPosition[1] == exitPosition[1]) {
            return true;
        }
        return false;
    }

    public String checkPathFormat(String pathString) {
        // reads through the move sequence to check for a number
        for (int i = 0; i < pathString.length(); i++) {
            // if a number is present convert from factorized form to canonical form
            if (Character.isDigit(pathString.charAt(i))) {
                String canonicalPath = factorizedToCanonical(pathString);
                return canonicalPath;
            }
        }
        return pathString;  // return the same String if no number was detected in the sequence
    }

    public String factorizedToCanonical(String pathString) {
        StringBuffer canonicalSequence = new StringBuffer();
        int repeatNumber;
        String repeatDirection;
        // loops through the entire path sequence
        for (int i = 0; i < pathString.length(); i++) {
            // case I: repeat direction number occurs before the move direction
            if (Character.isDigit(pathString.charAt(i))) {
                // handles multi-digit numbers
                StringBuffer numberString = new StringBuffer();
                while (i < pathString.length() && Character.isDigit(pathString.charAt(i))) {
                    numberString.append(pathString.charAt(i));
                    i++;
                }
                if (i >= pathString.length()) {
                    throw new IllegalArgumentException("Invalid path format");
                }
                repeatNumber = Integer.parseInt(numberString.toString());  // parses 'repeat number' char to its corresponding int value
                repeatDirection = String.valueOf(pathString.charAt(i));  // parses 'move direction' char to string
                // adds the direction for the number of times it is repeated
                for (int j = 1; j <= repeatNumber; j++) {
                    canonicalSequence.append(repeatDirection);
                }
            }
            else {
                canonicalSequence.append(pathString.charAt(i));
            }
        }
        logger.trace("Canonical path sequence: {}", canonicalSequence.toString());
        return canonicalSequence.toString();
    }

    public String findPath() {
        // This method will implement the pathfinding logic and generate the sequence of moves 
        return null;
    }

    public String findShortestPath() {
        // Placeholder for shortest path finding logic.
        return null;
    }

    public String generateFactorizedPath(String canonicalPath) {
        // This method will convert the canonical path to the factorized path
        return null;
    }
}