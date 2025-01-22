package ca.mcmaster.se2aa4.mazerunner;

public class Direction {
    private int currentDirection; 

    public Direction(String startingDirection) {
        determineDirection(startingDirection);
    }

    public void determineDirection(String direction) {
        // uses a string to represent the direction that the explorer is currently facing
        switch(direction) {
            case "N":
                this.currentDirection = 0;
            case "E":
                this.currentDirection = 1;
            case "S":
                this.currentDirection = 2;
            case "W":
                this.currentDirection = 3;
        }
    }

    public void moveExplorer(String move) {
        // uses modular arithmetic to calculate the new position of the explorer
        if (move.equals("L")) {
            this.currentDirection = (this.currentDirection + 3) % 4;
        }
        if (move.equals("R")) {
            this.currentDirection = (this.currentDirection + 1) % 4;
        }
    }

    public int getFacingDirection() {   
        return this.currentDirection;
    }
    
}