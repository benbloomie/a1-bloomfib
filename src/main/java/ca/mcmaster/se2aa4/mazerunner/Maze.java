package ca.mcmaster.se2aa4.mazerunner;

import java.io.*;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {
    // private attributes to store the information of the maze properties
    private char[][] maze;
    private int numColumns;
    private int numRows;
    private int[] exit;
    private int[] entrance;

    private Maze(MazeBuilder mazeBuilder) {
        this.maze = mazeBuilder.maze;
        this.numRows = mazeBuilder.numRows;
        this.numColumns = mazeBuilder.numColumns;
        this.entrance = mazeBuilder.entrance;
        this.exit = mazeBuilder.exit;
    }

    public static class MazeBuilder {
        private static final Logger logger = LogManager.getLogger(); 
        private char[][] maze;
        private int numColumns;
        private int numRows;
        private int[] exit;
        private int[] entrance;

        public MazeBuilder loadMazeFromFile(String mazeFile)  {
            logger.info("**** Reading the maze from file: {}", mazeFile);
            try {
                List<String> linesInMaze = readMazeFile(mazeFile);  // stores each line of the text file in an ArrayList    
                this.numColumns = linesInMaze.get(0).length();  // calculates the number of columns using the length of a line
                this.numRows = linesInMaze.size();  // calculates the number of rows using the number of elements in the array
                
                this.maze = new char[numRows][numColumns];      
                populateMaze(linesInMaze);
            } 
            catch (IOException e) {
                throw new IllegalArgumentException("Could not read maze file: " + mazeFile);
            }
            return this;
        }
        
        public MazeBuilder withStartingDirection(char facingDirection) {
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
            return this;
        }

        public Maze build() {
            return new Maze(this);
        }

        private List<String> readMazeFile(String mazeFile) throws IOException {
            BufferedReader reader = new BufferedReader(new FileReader(mazeFile));
            List<String> linesInMaze = new ArrayList<>();
            String mazeLine;

            // reads in each line of the maze as a string, and adds it to the ArrayList
            while ((mazeLine = reader.readLine()) != null) {
                linesInMaze.add(mazeLine);
            }
            reader.close();
            return linesInMaze;
        }

        private void populateMaze(List<String> linesInMaze) {
            for (int i = 0; i < numRows; i++) {
                String line = linesInMaze.get(i);
                if (line.isEmpty()) {
                    line = " ".repeat(numRows);  // replace empty lines with the empty line string
                }
                logger.trace("Adding string: {}", line);

                for (int j = 0; j < numColumns; j++) {
                    this.maze[i][j] = line.charAt(j);
                }
            }
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

    public int[] getEntrance() {
        return this.entrance;
    }

    public int[] getExit() {
        return this.exit;
    }
}