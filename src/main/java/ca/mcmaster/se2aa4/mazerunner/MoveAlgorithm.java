package ca.mcmaster.se2aa4.mazerunner;

public interface MoveAlgorithm {
    public void findPath(Maze maze, DirectionAnalyzer directionAnalyzer);
    public String getAlgorithmPath();
}