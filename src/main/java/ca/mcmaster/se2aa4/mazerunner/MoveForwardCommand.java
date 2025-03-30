package ca.mcmaster.se2aa4.mazerunner;

public class MoveForwardCommand extends MoveCommand {

    public MoveForwardCommand(ExplorerState explorerState, DirectionManager directionManager) {
        super(explorerState, directionManager);
    }

    @Override
    public void execute() {
        explorerState.setState('F', directionManager.getCurrentDirection());
    }

    @Override 
    public void undo() {
        turnAround();
        explorerState.setState('F', directionManager.getCurrentDirection());
        turnAround();
    }

    private void turnAround() {
        explorerState.setState('R', directionManager.getCurrentDirection());
        explorerState.setState('R', directionManager.getCurrentDirection());
    }
}
