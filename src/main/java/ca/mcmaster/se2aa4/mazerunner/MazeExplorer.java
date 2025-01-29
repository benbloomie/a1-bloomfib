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
        // verifies that the provide path can be analyzed before proceeding
        if (moveSequence == null || moveSequence.isEmpty()) {
            throw new IllegalArgumentException("Path cannot be empty.");
        }
        moveSequence = checkPathFormat(moveSequence);

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

    public boolean verifyPath() {
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

    public void didExplorerEscape() {
        // verifies if the path entered from the command-line is valid
        if (verifyPath()) {
            System.out.println("correct path");
        }
        else {
            System.out.println("incorrect path");
        }
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
                // if i is greater, than no direction proceeds the repeat number
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

    public boolean rightIsEmpty(int facingDirection, int rowMovement, int columnMovement) {
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

    public String generateFactorizedPath(String canonicalPath) {
        StringBuffer factorizedPath = new StringBuffer();

        // iterates through each character in the canonical string path
        for (int i = 0; i < canonicalPath.length(); i++)  {
            int movementCount = 1;
            
            // continues to loop while the current character is the same as the proceeding character
            while (i + 1 < canonicalPath.length() && canonicalPath.charAt(i) == canonicalPath.charAt(i + 1)) {
                movementCount++;  // increments the count for how many times the movement occurs in a row
                i++;
            }

            // only append the movement count if the movement is repeated at least twice
            if (movementCount >= 2) {
                factorizedPath.append(Integer.toString(movementCount));
                factorizedPath.append(canonicalPath.charAt(i));
            }
            // if there is only one occurence of the movement, just append the move
            else {
                factorizedPath.append(canonicalPath.charAt(i));
            }
            logger.info("Appending {}{}", movementCount, canonicalPath.charAt(i));
        }
        
        return factorizedPath.toString();
    }
}