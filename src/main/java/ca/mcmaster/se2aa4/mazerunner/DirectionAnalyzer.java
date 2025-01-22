package ca.mcmaster.se2aa4.mazerunner;

public class DirectionAnalyzer {
    private int currentDirection; 
    private char[] directions;

    public DirectionAnalyzer(char startingDirection) {
        determineDirection(startingDirection);
        this.directions = new char[] {'N', 'E', 'S', 'W'};
    }

    public void determineDirection(char direction) {
        // uses a string to represent the direction that the explorer is currently facing
        switch(direction) {
            case 'N':
                this.currentDirection = 0;
            case 'E':
                this.currentDirection = 1;
            case 'S':
                this.currentDirection = 2;
            case 'W':
                this.currentDirection = 3;
        }
    }

    public void moveExplorer(char move) {
        // uses modular arithmetic to calculate the new position of the explorer
        if (move =='L') {
            this.currentDirection = (this.currentDirection + 3) % 4;
        }
        if (move == 'R') {
            this.currentDirection = (this.currentDirection + 1) % 4;
        }
    }

    public char getFacingDirection() {   
        return this.directions[this.currentDirection];
    }
    
}