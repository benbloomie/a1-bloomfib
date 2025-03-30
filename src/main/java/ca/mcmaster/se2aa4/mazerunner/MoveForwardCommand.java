package ca.mcmaster.se2aa4.mazerunner;

public class MoveForwardCommand implements MoveCommand {
    private ExplorerMovement explorerMovement;
    private Direction direction;

    public MoveForwardCommand(Direction direction, ExplorerMovement explorerMovement) {
        this.explorerMovement = explorerMovement;
        this.direction = direction;
    }

    @Override
    public void execute() {
        explorerMovement.setState('F', direction);
    }

    @Override 
    public void undo() {
        turnAround();
        explorerMovement.setState('F', direction);
        turnAround();
    }

    private void turnAround() {
        explorerMovement.setState('R', direction);
        explorerMovement.setState('R', direction);
    }
}
