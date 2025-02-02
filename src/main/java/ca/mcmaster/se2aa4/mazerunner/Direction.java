package ca.mcmaster.se2aa4.mazerunner;

// we can use an enum for the direction, as they are a predefined set of values that don't change 
public enum Direction {
    NORTH(0) {
        @Override
        public void makeMove(int[] position, Maze maze) {
            // assigns current row and column to variables for better readability 
            int currentRow = position[0];
            int currentColumn = position[1];
            // moves the explorer up when facing north
            if (currentRow - 1 >= 0 && maze.getTile(currentRow - 1, currentColumn) == ' ') {
                position[0] = currentRow - 1;
            }
        }
    },

    EAST(1) {
        @Override
        public void makeMove(int[] position, Maze maze) {
            int currentRow = position[0];
            int currentColumn = position[1];
            // moves the explorer to the right when facing east
            if (currentColumn + 1 < maze.getLength() && maze.getTile(currentRow, currentColumn + 1) == ' ') {
                position[1] = currentColumn + 1;
            }
        }
    },

    SOUTH(2) {
        @Override
        public void makeMove(int[] position, Maze maze) {
            int currentRow = position[0];
            int currentColumn = position[1];
            // moves the explorer down when facing south
            if (currentRow + 1 < maze.getHeight() && maze.getTile(currentRow +1, currentColumn) == ' ') {
                position[0] = currentRow + 1;
            }
        }
    },

    WEST(3) {
        @Override
        public void makeMove(int[] position, Maze maze) {
            int currentRow = position[0];
            int currentColumn = position[1];
            // moves the explorer to the left when facing west
            if (currentColumn - 1 >= 0 && maze.getTile(currentRow, currentColumn - 1) == ' ') {
                position[1] = currentColumn - 1;
            }
        }
    };

    private int directionValue;  
    Direction(int directionValue) {
        this.directionValue = directionValue;  // assigns a field to each enum --> int representation of the direction
    }

    public int getDirectionValue() {
        return this.directionValue;
    }

    public Direction[] getDirections() {
        return values();
    }

    public Direction setInitialDirection(char startingDirection) {
        Direction[] directionArray = getDirections();
        // loops over each direction in the enum
        for (Direction direction: directionArray) {
            // if the first character of the direction matches the starting direction, intialize that as the starting direction
            if (direction.name().charAt(0) == startingDirection) {
                return direction;
            }
        }
        throw new IllegalArgumentException("Cannot initialize starting direction to be " + startingDirection);
    }

    // method that uses modular arithmetic to calculate the direction the explorer faces after turning left
    public Direction turnLeft() {
        int directionValue = getDirectionValue();
        Direction[] directionArray = getDirections();
        return directionArray[(directionValue + 3) % 4];  // returns the new direction using the position in the Direction[] array
    }  

    // method that uses modular arithmetic to calculate the direction the explorer faces after turning right
    public Direction turnRight() {
        int directionValue = getDirectionValue();
        Direction[] directionArray = getDirections();
        return directionArray[(directionValue + 1) % 4];  
    }  

    public abstract void makeMove(int[] currentPosition, Maze maze);  // abstract method to move the explorer based on the current direction it faces
}   
