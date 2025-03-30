package ca.mcmaster.se2aa4.mazerunner;

public class TurnRightCommand implements MoveCommand {
    private ExplorerMovement explorerMovement;
    private Direction direction;

    public TurnRightCommand(ExplorerMovement explorerMovement, Direction direction) {
        this.explorerMovement = explorerMovement;
        this.direction = direction;
    }

    @Override
    public void execute() {
        explorerMovement.setState('R', direction);
    }

    @Override
    public void undo() {
        explorerMovement.setState('L', direction);
    }
}
