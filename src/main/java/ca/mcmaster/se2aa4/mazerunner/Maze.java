package ca.mcmaster.se2aa4.mazerunner;

import java.io.*;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {
    private static final Logger logger = LogManager.getLogger();  
    // private attributes to store the information of the maze properties
    private char[][] maze;
    private int numColumns;
    private int numRows;
    private int[] exit;
    private int[] entrance;

    public Maze(String mazeFile) {
        this.maze = createMaze(mazeFile);
        // throw an exception when reading from a file that does not exist
        if (this.maze == null) {
            throw new IllegalArgumentException("Could not read maze file. Missing -i flag, or bad file path.");  
        }
    }

    public char[][] createMaze(String mazeFile) {
        logger.info("**** Reading the maze from file: {}", mazeFile);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(mazeFile));
            ArrayList<String> linesInMaze = new ArrayList<>();  // stores each line of the text file in an ArrayList
            String mazeLine;
    
            // reads in each line of the maze as a string, and adds it to the ArrayList
            while ((mazeLine = reader.readLine()) != null) {
                linesInMaze.add(mazeLine);
            }
    
            this.numColumns = linesInMaze.get(0).length();  // calculates the number of columns using the length of a line
            this.numRows = linesInMaze.size();  // calculates the number of rows using the number of elements in the array
            char[][] tempMaze = new char[numRows][numColumns];  
            String emptyLine = createEmptyLine();
    
            // iterates through each string in the ArrayList to create the 2D array of the maze
            for (int i = 0; i < this.numRows; i++) {
                String line = linesInMaze.get(i);
                if (line.isEmpty()) {
                    line = emptyLine;  // replace empty lines with the empty line string
                }
                logger.trace("Adding string: {}", line);
                for (int j = 0; j < this.numColumns; j++) {
                    tempMaze[i][j] = line.charAt(j); 
                }
            }
            reader.close();
            return tempMaze;

        } catch(IOException e) {
            logger.error("/!\\ An error has occured: Unable to read data from {} /!\\", mazeFile);
        } catch(Exception e) {
            logger.error("/!\\\\ An error has occured /!\\\\: {}", e.getMessage());
        }
        return null;  // returns null if an error occurs while reading the maze
    }

    public String createEmptyLine() {
        StringBuffer emptyLineString = new StringBuffer();
        // reads over the number of columns, and adds an empty space for each occurence
        for (int i = 0; i < this.numColumns; i++) {
            emptyLineString.append(" ");
        }
        return emptyLineString.toString();  // returns a String for that represents a completely empty line
    }

    private int[] findWestOpening() {
        logger.trace("**** Searching west entrance...");  
        for (int i = 0; i < this.numRows; i++) {
            // searches the left wall for an opening, and returns the position of the open tile
            if (this.maze[i][0] == ' ') {
                logger.info("West opening at: [{}, {}] (Row, column)", i, 0);
                return new int[]{i, 0};
            }
        }
        throw new IllegalArgumentException("Unsolvable maze. Contains no east opening");  
    }

    private int[] findEastOpening() {
        logger.trace("**** Searching for east entrance...");  
        for (int i = 0; i < this.numRows; i++) {
            // searches the right wall for an opening, and returns the position of the open tile
            if (this.maze[i][numColumns - 1] == ' ') {
                logger.info("East opening at: [{}, {}] (Row, column)", i, numColumns - 1);
                return new int[]{i, numColumns - 1};
            }
        }
        throw new IllegalArgumentException("Unsolvable maze. Contains no west opening");  
    }

    public void printMaze() {
        for (int i = 0; i < this.numRows; i++) {
            for (int j = 0; j < this.numColumns; j++) {
                System.out.print(this.maze[i][j]);
            }
            System.out.println();
        }
    }

    public char[][] getMaze() {
        return this.maze;
    }

    public int getLength() {
        return this.numColumns;
    }

    public int getHeight() {
        return this.numRows;
    }

    public char getTile(int row, int column) {
        return maze[row][column];
    }

    public void setMazeOpenings(char facingDirection) {
        // determines what side of the maze the explorer starts on
        if (facingDirection == 'E') {
            this.entrance = findWestOpening();
            this.exit = findEastOpening();
        }
        else if (facingDirection == 'W') {
            this.entrance = findEastOpening();
            this.exit = findWestOpening();
        }
        logger.info("Set entrance position to [{},{}]", this.entrance[0], this.entrance[1]);
        logger.info("Set exit position to [{},{}]", this.exit[0], this.exit[1]);
    }

    public int[] getEntrance() {
        return this.entrance;
    }

    public int[] getExit() {
        return this.exit;
    }
}