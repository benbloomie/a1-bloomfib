package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class DirectionTest {
    private Maze maze;
    private int[] position;
    private static final String MAZE = "./examples/rectangle.maz.txt";

    @BeforeEach
    void setUpExplorer() {
        maze = new Maze(MAZE);
        position = new int[]{3, 11};  // starts the explorer in an open position
    }

    @Test
    void testNorthMovement() {
        Direction north = Direction.NORTH;
        north.makeMove(position, maze);  
        assertArrayEquals(new int[]{2, 11}, position);
    }

    @Test
    void testEastMovement() {
        Direction east = Direction.EAST;
        east.makeMove(position, maze);
        assertArrayEquals(new int[]{3, 12}, position);
    }

    @Test
    void testSouthMovement() {
        Direction south = Direction.SOUTH;
        south.makeMove(position, maze);
        assertArrayEquals(new int[]{4, 11}, position);
    }

    @Test
    void testWestMovement() {
        Direction west = Direction.WEST;
        west.makeMove(position, maze);
        assertArrayEquals(new int[]{3, 10}, position);
    }

    @Test
    void testMovementAgainstWall() {
        position = new int[]{1, 1};  // moves explorer against a wall
        Direction north = Direction.NORTH;  // makes explorer face the wall
        north.makeMove(position, maze);
        assertArrayEquals(new int[]{1, 1}, position);
    }
}