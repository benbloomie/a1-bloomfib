package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PositionManagerTest {
    private Maze maze;
    private PositionManager positionManager;
    private static final String MAZE = "./examples/tiny.maz.txt";

    @BeforeEach
    void createMaze() {
        maze = new Maze(MAZE);
        positionManager = new PositionManager(maze, new int[]{3, 3}, new ExplorerState());  // starts explorer at an open position
    }

    @Test
    public void testInitialPosition() {
        int[] position = positionManager.getPosition();
        assertArrayEquals(new int[]{3, 3}, position);
    }

    @Test
    public void testNorthMovement() {
        positionManager.moveExplorer(Direction.NORTH);
        assertArrayEquals(new int[]{2, 3}, positionManager.getPosition());
    }

    @Test
    public void testEastMovement() {
        positionManager.moveExplorer(Direction.EAST);
        assertArrayEquals(new int[]{3, 4}, positionManager.getPosition());
    }

    @Test
    public void testSouthMovement() {
        positionManager.moveExplorer(Direction.SOUTH);
        assertArrayEquals(new int[]{4, 3}, positionManager.getPosition());
    }

    @Test
    public void testWestMovement() {
        positionManager.moveExplorer(Direction.WEST);
        assertArrayEquals(new int[]{3, 2}, positionManager.getPosition());
    }
}

