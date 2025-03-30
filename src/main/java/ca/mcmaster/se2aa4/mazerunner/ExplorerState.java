package ca.mcmaster.se2aa4.mazerunner;

public class ExplorerState extends Subject {

    public void setState(char move, Direction direction) {
        this.move = move;
        this.direction = direction;
        this.notifyAllObservers();
    }
}
