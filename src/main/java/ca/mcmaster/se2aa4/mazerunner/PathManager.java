package ca.mcmaster.se2aa4.mazerunner;

public class PathManager extends MoveObserver {
    private StringBuffer movementsMade = new StringBuffer();

    public PathManager(Subject subject) {
        this.subject = subject;
        subject.attach(this);
    }

    public String getPath() {
        return movementsMade.toString();
    }

    @Override
    public void update() {
        movementsMade.append(this.subject.getMove());
    }
    
}
