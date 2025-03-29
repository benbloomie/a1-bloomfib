package ca.mcmaster.se2aa4.mazerunner;

public class PathFinder implements MazeExplorer {
    private PositionManager positionManager;
    private DirectionManager directionManager;
    private MoveAlgorithm algorithm;
    private String pathResult;

    public PathFinder(char startingDirection, Maze maze, MoveAlgorithm algorithm) {
        this.positionManager = new PositionManager(maze, maze.getEntrance());
        this.directionManager = new DirectionManager(startingDirection);
        this.algorithm = algorithm;
    }

    @Override 
    public void exploreMaze() {
        algorithm.setManagers(positionManager, directionManager);
        // calls the MoveAlgorithm defined methods to find the path
        algorithm.findPath();
        this.pathResult = algorithm.getAlgorithmPath();
    }

    @Override
    public String getPathResult() {
        return this.pathResult;
    }
}