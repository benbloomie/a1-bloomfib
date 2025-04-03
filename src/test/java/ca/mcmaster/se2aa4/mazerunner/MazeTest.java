package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class MazeTest {
    private static final String MAZE = "./examples/small.maz.txt";
    private Maze maze;

    @BeforeEach
    void createMaze() {
        maze = new Maze.MazeBuilder()
            .loadMazeFromFile(MAZE)
            .withStartingDirection('E')
            .build();
    }

    @Test
    void testNumberOfMazeColumns() {
        int length = maze.getLength();
        assertEquals(11, length);
    }
   
    @Test 
    void testNumberOfMazeRows() {
        int height = maze.getHeight();
        assertEquals(11, height);
    }

    @Test
    void testMazeEntranceFacingEast() {
        Maze maze = new Maze.MazeBuilder()
            .loadMazeFromFile(MAZE)
            .withStartingDirection('E')
            .build();
        assertArrayEquals(new int[]{8, 0}, maze.getEntrance());
    }

    @Test
    void testMazeEntranceFacingWest() {
        Maze maze = new Maze.MazeBuilder()
            .loadMazeFromFile(MAZE)
            .withStartingDirection('W')
            .build(); 
        assertArrayEquals(new int[]{5, 10}, maze.getEntrance());
    }

    @Test
    void testMazeExitFacingEast() {
        Maze maze = new Maze.MazeBuilder()
            .loadMazeFromFile(MAZE)
            .withStartingDirection('E')
            .build();
        assertArrayEquals(new int[]{5, 10}, maze.getExit());
    }
     
    @Test
    void testMazeExitFacingWest() {
        Maze maze = new Maze.MazeBuilder()
            .loadMazeFromFile(MAZE)
            .withStartingDirection('W')
            .build();
        assertArrayEquals(new int[]{8, 0}, maze.getExit());
    }

    @Test
    void testEmptyTileRetrieval() {
        char tile = maze.getTile(8, 0);
        assertEquals(' ', tile);
    }

    @Test
    void testFilledTileRetrieval() {
        char tile = maze.getTile(0, 0);
        assertEquals('#', tile);
    }
}