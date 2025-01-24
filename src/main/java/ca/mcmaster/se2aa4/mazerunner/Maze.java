package ca.mcmaster.se2aa4.mazerunner;

import java.io.*;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {
    private static final Logger logger = LogManager.getLogger();  
    // stores the information about the maze properties
    private char[][] maze;
    private int numColumns;
    private int numRows;
    // stores the positions of the entrances
    public final int[] westEntrance;
    public final int[] eastEntrance;
    public int[] exit;

    public Maze(String mazeFile) {
        this.maze = createMaze(mazeFile);
        this.westEntrance = new int[2];
        this.eastEntrance = new int[2];
        findEntrances();
    }

    public char[][] createMaze(String mazeFile) {
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
            logger.error("Unable to read data from the file {}", mazeFile);
        } catch(Exception e) {
            logger.error("An unexpected error occured while reading the file: {}", e.getMessage());
        }
        return null;
    }

    // method to create a String for a completely empty line
    public String createEmptyLine() {
        StringBuffer emptyLineString = new StringBuffer();
        for (int i = 0; i < this.numColumns; i++) {
            emptyLineString.append(" ");
        }
        return emptyLineString.toString();
    }

    public void findEntrances() {
        // NOTE: position [0][0] is top left corner of the maze
        logger.trace("**** Searching for entrances...");  
        for (int i = 0; i < this.numRows; i++) {
            // searches the left wall for an opening
            if (this.maze[i][0] == ' ') {
                // assigns first position the row, and the second position the column
                this.westEntrance[0] = i;
                this.westEntrance[1] = 0;
            }
            // searches the right wall for an opening, and assigns the same position
            if (this.maze[i][numColumns - 1] == ' ') {
                this.eastEntrance[0] = i;
                this.eastEntrance[1] = numColumns - 1;
            }
        }
        logger.info("West entrance at: {}, {} (Row, column)", westEntrance[0], westEntrance[1]);
        logger.info("East entrance at: {}, {} (Row, column)", eastEntrance[0], eastEntrance[1]);
    }

    public void printMaze() {
        // iterates through each row and column of the maze, and prints out each item
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

    public int[] getEntrance(char facingDirection) {
        // determines what side of the maze the explorer starts on
        if (facingDirection == 'E') {
            setExit(facingDirection);  // sets the exit position while setting the entrance
            return westEntrance;
        }
        else if (facingDirection == 'W') {
            setExit(facingDirection);
            return eastEntrance;
        }
        return null;
    }

    public void setExit(char facingDirection) { 
        // determines the exit position based on what the starting position was 
        if (facingDirection == 'E') {
            this.exit = eastEntrance;
        }
        else if (facingDirection == 'W') {
            this.exit = westEntrance;
        }
    }

    public int[] getExit() {
        return this.exit;
    }

    public int getLength() {
        return this.numColumns;
    }
}