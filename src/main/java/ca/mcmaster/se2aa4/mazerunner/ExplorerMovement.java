package ca.mcmaster.se2aa4.mazerunner;

import java.util.Stack;

public class ExplorerMovement {
    private Stack<MoveCommand> commandHistory = new Stack<>();

    public void makeMove(MoveCommand newMove) {
        newMove.execute();
        commandHistory.push(newMove);
    }

    public void undo() {
        if (commandHistory.isEmpty()) {
            return;
        }
        MoveCommand prevMove = commandHistory.pop();
        if (prevMove != null) {
            prevMove.undo();
        }
    }
}
