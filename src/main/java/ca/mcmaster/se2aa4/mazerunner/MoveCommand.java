package ca.mcmaster.se2aa4.mazerunner;

public interface MoveCommand {
    public void execute();
    public void undo();
}
