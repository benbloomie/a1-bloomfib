package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class PathFormatterTest {
    private PathFormatter pathFormatter;

    @BeforeEach
    void createFormatter() {
        pathFormatter = new PathFormatter();
    }

    @Test
    void testFactorizedPathToCanonicalPath() {
        pathFormatter.setCanonicalPath("FR3FL4FR");
        assertEquals("FRFFFLFFFFR", pathFormatter.getCanonicalPath());
    }

    @Test
    void testFactorizedPathToCanonicalPathWithSpaces() {
        pathFormatter.setCanonicalPath("F R 11F L 2F F0R");
        assertEquals("F R FFFFFFFFFFF L FF F", pathFormatter.getCanonicalPath());
    }

    @Test 
    void testCanonicalToFactorized() {
        pathFormatter.setFactorizedPath("FRFFLFFFFRFFLFFF");
        assertEquals("FR2FL4FR2FL3F", pathFormatter.getFactorizedPath());
    }

    @Test 
    void testCanonicalToFactorizedWithSpaces() {
        pathFormatter.setFactorizedPath("F L FFFF R F RR F R L FFF FFFF");
        assertEquals("F L 4F R F 2R F R L 3F 4F", pathFormatter.getFactorizedPath());
    }
}