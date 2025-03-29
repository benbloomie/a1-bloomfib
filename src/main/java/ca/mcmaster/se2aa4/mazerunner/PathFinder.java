package ca.mcmaster.se2aa4.mazerunner;

public class PathFinder implements MazeExplorer {
    private MoveAlgorithm algorithm;
    private String pathResult;

    public PathFinder(char startingDirection, Maze maze, MoveAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    @Override 
    public void exploreMaze() {
        // calls the MoveAlgorithm defined methods to find the path
        algorithm.findPath();
        this.pathResult = algorithm.getAlgorithmPath();
    }

    @Override
    public String getPathResult() {
        return this.pathResult;
    }
}