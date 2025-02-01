package ca.mcmaster.se2aa4.mazerunner;

public class PathFinder implements MazeExplorer {
    private Maze maze;
    private DirectionAnalyzer directionAnalyzer;
    private MoveAlgorithm algorithm;
    private String pathResult;

    public PathFinder(Maze maze, DirectionAnalyzer directionAnalyzer, MoveAlgorithm algorithm) {
        this.maze = maze;
        this.directionAnalyzer = directionAnalyzer;
        this.algorithm = algorithm;
    }

    @Override 
    public void exploreMaze() {
        algorithm.findPath(maze, directionAnalyzer);
        this.pathResult = algorithm.getAlgorithmPath();
    }

    @Override
    public String getPathResult() {
        return this.pathResult;
    }
}