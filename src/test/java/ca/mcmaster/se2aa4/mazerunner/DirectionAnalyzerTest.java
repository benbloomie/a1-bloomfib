package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class DirectionAnalyzerTest {
    private Maze maze;
    private DirectionAnalyzer directionAnalyzer;
    private static final String MAZE = "./examples/tiny.maz.txt";

    @BeforeEach
    void createMaze() {
        maze = new Maze(MAZE);
        int[] entrance = {5, 0};  // sets entrance to west wall --> start facing east
        directionAnalyzer = new DirectionAnalyzer('E', maze, entrance);
    }

    @Test
    void testInitialDirection() {
        Direction initialDirection = Direction.NORTH.setInitialDirection('N');
        assertEquals(Direction.NORTH, initialDirection);
    }

    @Test
    void testPositionRetrieval() {
        assertArrayEquals(new int[]{5, 0}, directionAnalyzer.getPosition());
    }

    @Test
    void testLeftTurn() {
        directionAnalyzer.moveExplorer('L');
        assertEquals('N', directionAnalyzer.getFacingDirection());
    }

    @Test
    void testRightTurn() {
        directionAnalyzer.moveExplorer('R');
        assertEquals('S', directionAnalyzer.getFacingDirection());
    }

    @Test
    void testForwardMove() {
        directionAnalyzer.moveExplorer('F');
        assertArrayEquals(new int[]{5, 1}, directionAnalyzer.getPosition());
    }
}
