package ca.mcmaster.se2aa4.mazerunner;

public interface MoveAlgorithm {
    public void findPath();
    public String getAlgorithmPath();
    public void setManagers(PositionManager positionManager, DirectionManager directionManager);
}