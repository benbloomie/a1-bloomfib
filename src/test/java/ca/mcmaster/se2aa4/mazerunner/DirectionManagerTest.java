package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class DirectionManagerTest {
    private DirectionManager directionManager;

    @BeforeEach
    void createMaze() {
        directionManager = new DirectionManager('E');
    }

    @Test
    void testInitialDirection() {
        directionManager.setInitialDirection('N');
        assertEquals('N', directionManager.getFacingDirection());
    }

    @Test
    void testLeftTurn() {
        directionManager.turnExplorer('L');
        assertEquals('N', directionManager.getFacingDirection());
    }

    @Test
    void testRightTurn() {
        directionManager.turnExplorer('R');
        assertEquals('S', directionManager.getFacingDirection());
    }


}
