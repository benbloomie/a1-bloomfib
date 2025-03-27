package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class MazeTest {

    // using small.maz.txt for Maze testing
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
        maze.setEntrance('E'); 
        maze.findEntrances(); 
        int[] entrance = maze.getEntrance();
        assertArrayEquals(new int[]{8, 0}, entrance);
    }

    @Test
    void testMazeEntranceFacingWest() {
        maze.setEntrance('W'); 
        maze.findEntrances(); 
        int[] entrance = maze.getEntrance();
        assertArrayEquals(new int[]{5, 10}, entrance);
    }

    @Test
    void testMazeExitFacingEast() {
        maze.setEntrance('E'); 
        maze.findEntrances(); 
        int[] exit = maze.getExit();
        assertArrayEquals(new int[]{5, 10}, exit);
    }
     
    @Test
    void testMazeExitFacingWest() {
        maze.setEntrance('W'); 
        maze.findEntrances(); 
        int[] exit = maze.getExit();
        assertArrayEquals(new int[]{8, 0}, exit);
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
