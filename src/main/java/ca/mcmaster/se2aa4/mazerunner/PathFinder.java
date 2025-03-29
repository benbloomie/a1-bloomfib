package ca.mcmaster.se2aa4.mazerunner;

public class PathFinder implements MazeExplorer {
    private Maze maze;
    private DirectionAnalyzer directionAnalyzer;
    private MoveAlgorithm algorithm;
    private String pathResult;

    public PathFinder(char startingDirection, Maze maze, MoveAlgorithm algorithm) {
        this.maze = maze;
        this.directionAnalyzer = new DirectionAnalyzer(startingDirection, maze, maze.getEntrance());
        this.algorithm = algorithm;
    }

    @Override 
    public void exploreMaze() {
        // calls the MoveAlgorithm defined methods to find the path
        algorithm.findPath(maze, directionAnalyzer);
        this.pathResult = algorithm.getAlgorithmPath();
    }

    @Override
    public String getPathResult() {
        return this.pathResult;
    }
}