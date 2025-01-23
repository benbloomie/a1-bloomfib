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
            directionAnalyzer.moveExplorer(currentMove);  // passes through each move to update the position
            logger.trace("Move {}: {}", i + 1, currentMove); // log each move
        }
        // checks if the explorer successfully completed the maze 
        if (verifyMaze()) {
            logger.info("Explorer has escaped the maze!");
        }
        else {
            logger.info("Explorer did not reach the exit.");
        }
    }

    public boolean verifyMaze() {
        // initializes arrays for the exit position and the position that the explorer ends at
        int[] exitPosition = maze.getExit();
        int[] currentPosition = directionAnalyzer.getPosition();
        // compares row and column positions after all path moves have been made
        if (currentPosition[0] == exitPosition[0] && currentPosition[1] == exitPosition[1]) {
            return true;
        }
        return false;
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
