package ca.mcmaster.se2aa4.mazerunner;

public class MazeExplorer {
    private String moveSequence;  // inputted path sequence from command-line argument
    private StringBuffer movesMade;  // tracks the moves the program has registered 
    private Maze maze;  
 
    public MazeExplorer(String moveSequence, Maze maze) {
        this.moveSequence = moveSequence;
        this.maze = maze;
        this.movesMade = new StringBuffer();
        // using east facing direction as starting posiiton 
        DirectionAnalyzer directionAnalyzer = new DirectionAnalyzer('E');
    }

    public void verifyPath() {
        // continue looping until every path movement has been read
        while (!movesMade.toString().equals(moveSequence)) {

        }
    }
 
}
