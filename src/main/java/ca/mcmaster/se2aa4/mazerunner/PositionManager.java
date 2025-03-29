package ca.mcmaster.se2aa4.mazerunner;

public class PositionManager {
    private int[] position;
    private Maze maze;

    public PositionManager(Maze maze, int[] initialPosition) {
        this.position = initialPosition;
        this.maze = maze;
    }

    public void moveExplorer(Direction direction) {
        direction.makeMove(position, maze);
    }

    // getter method to access the current position of the traveler
    public int[] getPosition() {
        return position;
    }
}