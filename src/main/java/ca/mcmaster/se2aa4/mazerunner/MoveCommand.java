package ca.mcmaster.se2aa4.mazerunner;

public abstract class MoveCommand {
    protected ExplorerState explorerState;
    protected DirectionManager directionManager;

    MoveCommand(ExplorerState explorerState, DirectionManager directionManager) {
        this.explorerState = explorerState;
        this.directionManager = directionManager;
    }

    public abstract void execute();

    public abstract void undo();
}