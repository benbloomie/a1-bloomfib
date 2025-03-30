package ca.mcmaster.se2aa4.mazerunner;

public class TurnLeftCommand extends MoveCommand {

    public TurnLeftCommand(ExplorerState explorerState, DirectionManager directionManager) {
        super(explorerState, directionManager);
    }

    @Override
    public void execute() {
        explorerState.setState('L', directionManager.getCurrentDirection());
    }

    @Override
    public void undo() {
        explorerState.setState('E', directionManager.getCurrentDirection());
    }
}
