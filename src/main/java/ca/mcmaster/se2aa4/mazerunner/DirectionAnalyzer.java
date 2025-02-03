package ca.mcmaster.se2aa4.mazerunner;

public class DirectionAnalyzer {
    private Direction currentDirection;
    private int[] position;
    private Maze maze;

    public DirectionAnalyzer(char startingDirection, Maze maze, int[] entrance) {
        setInitialDirection(startingDirection);
        this.position = entrance;  // assigns starting position to the entrance position
        this.maze = maze;
    }

    private void setInitialDirection(char direction) {
        this.currentDirection = Direction.NORTH;  // initializes the direction to be north
        this.currentDirection = currentDirection.setInitialDirection(direction);  // updates starting direction to face the corrct way
    }

    // method to move the explorer based on the provided move sequence
    public void moveExplorer(char move) {
        // uses the enum methods to update the facing direction when turning 
        if (move =='L') {
            currentDirection = currentDirection.turnLeft();  
        }
        else if (move == 'R') {
            currentDirection = currentDirection.turnRight();
        }
        // moves the explorer forward by calling the enum method move explorer
        else if (move == 'F') {
            currentDirection.makeMove(position, maze);
        }
    }

    // getter method to retrieve the direction as its char representation
    public char getFacingDirection() {   
        return currentDirection.name().charAt(0);
    }

    // getter method to retrieve the value of the facing direction
    public int getFacingDirectionValue() {
        return currentDirection.getDirectionValue();
    }

    // getter method to access the current position of the traveler
    public int[] getPosition() {
        return this.position;
    }
}