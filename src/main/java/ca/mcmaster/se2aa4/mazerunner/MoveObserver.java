package ca.mcmaster.se2aa4.mazerunner;

public abstract class MoveObserver {
    protected Subject subject;
    public abstract void update();
}
