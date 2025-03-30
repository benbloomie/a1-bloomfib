package ca.mcmaster.se2aa4.mazerunner;

public class TurnRightCommand extends MoveCommand {

    public TurnRightCommand(ExplorerState explorerState, DirectionManager directionManager) {
        super(explorerState, directionManager);
    }

    @Override
    public void execute() {
        explorerState.setState('R', directionManager.getCurrentDirection());
    }

    @Override
    public void undo() {
        explorerState.setState('L', directionManager.getCurrentDirection());
    }
}
