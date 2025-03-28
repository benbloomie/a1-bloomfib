package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class MazeTest {
    private static final String MAZE = "./examples/small.maz.txt";
    private Maze maze;

    @BeforeEach
    void createMaze() {
        maze = new Maze(MAZE);
    }

    @Test
    void testFileReading() {
        assertNotNull(maze.getMaze());
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
        maze.setMazeOpenings('E'); 
        assertArrayEquals(new int[]{8, 0}, maze.getEntrance());
    }

    @Test
    void testMazeEntranceFacingWest() {
        maze.setMazeOpenings('W'); 
        assertArrayEquals(new int[]{5, 10}, maze.getEntrance());
    }

    @Test
    void testMazeExitFacingEast() {
        maze.setMazeOpenings('E'); 
        assertArrayEquals(new int[]{5, 10}, maze.getExit());
    }
     
    @Test
    void testMazeExitFacingWest() {
        maze.setMazeOpenings('W'); 
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