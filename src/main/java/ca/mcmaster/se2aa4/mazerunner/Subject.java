package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private List<MoveObserver> observers = new ArrayList<MoveObserver>();
    protected char move; 
    protected Direction direction;

    public void attach(MoveObserver observer) {
        this.observers.add(observer);
    }

    public void detach(MoveObserver observer) {
        this.observers.remove(observer);
    }

    public void notifyAllObservers() {
        for (MoveObserver observer : this.observers) {
            observer.update();
        }
    }

    public char getMove() {
        return move;
    }

    public Direction getDirection() {
        return direction;
    }
}