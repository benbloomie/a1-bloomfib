package ca.mcmaster.se2aa4.mazerunner;

public class DirectionAnalyzer {
    private char[] directions;
    private int[] position;
    private int currentDirection; 
    private Maze maze;

    public DirectionAnalyzer(char startingDirection, Maze maze, int[] entrance) {
        setInitialDirection(startingDirection);
        this.directions = new char[] {'N', 'E', 'S', 'W'};  // corresponds the direction value to its array index to easily retrieve the direction
        this.position = entrance;  // assigns starting position to the entrance position
        this.maze = maze;
    }

    public void setInitialDirection(char direction) {
        // uses a char to represent the direction that the explorer is facing when starting
        switch(direction) {
            case 'N':
                this.currentDirection = 0;
                break;
            case 'E':
                this.currentDirection = 1;
                break;
            case 'S':
                this.currentDirection = 2;
                break;
            case 'W':
                this.currentDirection = 3;
                break;
        }
    }

    public void moveExplorer(char move) {
        // assigns current row and column to variables for better readability in 'F' case
        int currentRow = this.position[0];
        int currentColumn = this.position[1];
        
        // uses modular arithmetic to calculate the new position of the explorer
        if (move =='L') {
            this.currentDirection = (this.currentDirection + 3) % 4;
        }
        else if (move == 'R') {
            this.currentDirection = (this.currentDirection + 1) % 4;
        }
        // moves the explorers position based on what direction it currently faces
        else if (move == 'F') {
            switch(getFacingDirection()) {
                case 'N':
                    // moves the explorer up the maze when reading 'F' in North direction
                    if (maze.getMaze()[currentRow - 1][currentColumn] == ' ') {
                        position[0] = position[0] - 1;
                    }
                    break;
                case 'E':
                    // moves the explorer to the right when reading 'F' in East direction
                    if (maze.getMaze()[currentRow][currentColumn + 1] == ' ') {
                        position[1] = position[1] + 1;
                    }
                    break;
                case 'S':
                    // moves the explorer down the maze when reading 'F' in South direction
                    if (maze.getMaze()[currentRow + 1][currentColumn] == ' ') {
                        position[0] = position[0] + 1;
                    }
                    break;
                case 'W':
                    // moves the explorer to the left when reading 'F' in West direction
                    if (maze.getMaze()[currentRow][currentColumn - 1] == ' ') {
                        position[1] = position[1] - 1;
                    }
                    break;
            }
        }
    }

    // getter method to retrieve the direction as its char representation
    public char getFacingDirection() {   
        return this.directions[this.currentDirection];
    }

    // getter method to access the current position of the traveler
    public int[] getPosition() {
        return this.position;
    }
    
}