package ca.mcmaster.se2aa4.mazerunner;

public class TurnLeftCommand implements MoveCommand {
    private ExplorerMovement explorerMovement;
    private Direction direction;

    public TurnLeftCommand(ExplorerMovement explorerMovement, Direction direction) {
        this.explorerMovement = explorerMovement;
        this.direction = direction;
    }

    @Override
    public void execute() {
        explorerMovement.setState('L', direction);
    }

    @Override
    public void undo() {
        explorerMovement.setState('E', direction);
    }
}
