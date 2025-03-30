package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class DirectionManagerTest {
    private DirectionManager directionManager;

    @BeforeEach
    void createMaze() {
        directionManager = new DirectionManager('E', new ExplorerMovement());
    }

    @Test
    void testInitialDirection() {
        directionManager.setInitialDirection('N');
        assertEquals(Direction.NORTH, directionManager.getCurrentDirection());
    }

    @Test
    void testLeftTurn() {
        directionManager.turnExplorer('L');
        assertEquals(Direction.NORTH, directionManager.getCurrentDirection());
    }

    @Test
    void testRightTurn() {
        directionManager.turnExplorer('R');
        assertEquals(Direction.SOUTH, directionManager.getCurrentDirection());
    }


}
