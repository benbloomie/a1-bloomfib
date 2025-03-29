package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestAllMazes {

    @Test
    void verifyDirectMaze() {
        Maze maze = new Maze("./examples/direct.maz.txt");
        maze.setMazeOpenings('E');  // assume we start at the east entrance
        PathFormatter pathFormatter = new PathFormatter();
        MazeExplorer finder = new PathFinder('E', maze, new RightHandAlgorithm());
        finder.exploreMaze();
        String result = finder.getPathResult();  
        pathFormatter.setFactorizedPath(result);
        String factorizedPathSequence = pathFormatter.getFactorizedPath();
        assertEquals("FR2FL3FRFLFRFL2F", factorizedPathSequence);

        Maze maze2 = new Maze("./examples/direct.maz.txt");
        maze2.setMazeOpenings('E');  // assume we start at the east entrance
        MazeExplorer verifier = new PathVerifier('E', maze2, result);
        verifier.exploreMaze();

        assertEquals("correct path", verifier.getPathResult());
    }

    @Test
    void verifyGiantMaze() {
        Maze maze = new Maze("./examples/giant.maz.txt");
        maze.setMazeOpenings('E');  // assume we start at the east entrance
        MazeExplorer finder = new PathFinder('E', maze, new RightHandAlgorithm());
        finder.exploreMaze();
        String result = finder.getPathResult();  

        Maze maze2 = new Maze("./examples/giant.maz.txt");
        maze2.setMazeOpenings('E');  // assume we start at the east entrance
        MazeExplorer verifier = new PathVerifier('E', maze2, result);
        verifier.exploreMaze();

        assertEquals("correct path", verifier.getPathResult());
    }

    @Test
    void verifyHugeMaze() {
        Maze maze = new Maze("./examples/huge.maz.txt");
        maze.setMazeOpenings('E');  // assume we start at the east entrance
        MazeExplorer finder = new PathFinder('E', maze, new RightHandAlgorithm());
        finder.exploreMaze();
        String result = finder.getPathResult();  

        Maze maze2 = new Maze("./examples/huge.maz.txt");
        maze2.setMazeOpenings('E');  // assume we start at the east entrance
        MazeExplorer verifier = new PathVerifier('E', maze2, result);
        verifier.exploreMaze();

        assertEquals("correct path", verifier.getPathResult());
    }

    @Test
    void verifyLargeMaze() {
        Maze maze = new Maze("./examples/large.maz.txt");
        maze.setMazeOpenings('E');  // assume we start at the east entrance
        MazeExplorer finder = new PathFinder('E', maze, new RightHandAlgorithm());
        finder.exploreMaze();
        String result = finder.getPathResult();  

        Maze maze2 = new Maze("./examples/large.maz.txt");
        maze2.setMazeOpenings('E');  // assume we start at the east entrance
        MazeExplorer verifier = new PathVerifier('E', maze2, result);
        verifier.exploreMaze();

        assertEquals("correct path", verifier.getPathResult());
    }

    @Test
    void verifyMediumMaze() {
        Maze maze = new Maze("./examples/medium.maz.txt");
        maze.setMazeOpenings('E');  // assume we start at the east entrance        
        MazeExplorer finder = new PathFinder('E', maze, new RightHandAlgorithm());
        finder.exploreMaze();
        String result = finder.getPathResult();  

        Maze maze2 = new Maze("./examples/medium.maz.txt");
        maze2.setMazeOpenings('E');  // assume we start at the east entrance
        MazeExplorer verifier = new PathVerifier('E', maze2, result);
        verifier.exploreMaze();

        assertEquals("correct path", verifier.getPathResult());
    }

    @Test
    void verifyRectangleMaze() {
        Maze maze = new Maze("./examples/rectangle.maz.txt");
        maze.setMazeOpenings('E');  // assume we start at the east entrance
        MazeExplorer finder = new PathFinder('E', maze, new RightHandAlgorithm());
        finder.exploreMaze();
        String result = finder.getPathResult();  

        Maze maze2 = new Maze("./examples/rectangle.maz.txt");
        maze2.setMazeOpenings('E');  // assume we start at the east entrance
        MazeExplorer verifier = new PathVerifier('E', maze2, result);
        verifier.exploreMaze();

        assertEquals("correct path", verifier.getPathResult());
    }

    @Test
    void verifyRegularMaze() {
        Maze maze = new Maze("./examples/regular.maz.txt");
        maze.setMazeOpenings('E');  // assume we start at the east entrance
        MazeExplorer finder = new PathFinder('E', maze, new RightHandAlgorithm());
        finder.exploreMaze();
        String result = finder.getPathResult();  

        Maze maze2 = new Maze("./examples/regular.maz.txt");
        maze2.setMazeOpenings('E');  // assume we start at the east entrance
        MazeExplorer verifier = new PathVerifier('E', maze2, result);
        verifier.exploreMaze();

        assertEquals("correct path", verifier.getPathResult());
    }

    @Test
    void verifySmallMaze() {
        Maze maze = new Maze("./examples/small.maz.txt");
        maze.setMazeOpenings('E');  // assume we start at the east entrance
        PathFormatter pathFormatter = new PathFormatter();
        MazeExplorer finder = new PathFinder('E', maze, new RightHandAlgorithm());
        finder.exploreMaze();
        String result = finder.getPathResult();  
        pathFormatter.setFactorizedPath(result);
        String factorizedPathSequence = pathFormatter.getFactorizedPath();
        assertEquals("FRF2L2FR2FR2F2L4FR2FR4F2L2FR4FR2FR2F2L2FL2FL4FR2FR2F2L4FR2FR2F2L2FR2FR4FR2FL2FR2FLF", factorizedPathSequence);

        Maze maze2 = new Maze("./examples/small.maz.txt");
        maze2.setMazeOpenings('E');  // assume we start at the east entrance
        MazeExplorer verifier = new PathVerifier('E', maze2, result);
        verifier.exploreMaze();

        assertEquals("correct path", verifier.getPathResult());
    }

    @Test
    void verifyStraightMaze() {
        Maze maze = new Maze("./examples/straight.maz.txt");
        maze.setMazeOpenings('E');  // assume we start at the east entrance
        PathFormatter pathFormatter = new PathFormatter();
        MazeExplorer finder = new PathFinder('E', maze, new RightHandAlgorithm());
        finder.exploreMaze();
        String result = finder.getPathResult();  
        pathFormatter.setFactorizedPath(result);
        String factorizedPathSequence = pathFormatter.getFactorizedPath();
        assertEquals("4F", factorizedPathSequence);

        Maze maze2 = new Maze("./examples/straight.maz.txt");
        maze2.setMazeOpenings('E');  // assume we start at the east entrance
        MazeExplorer verifier = new PathVerifier('E', maze2, result);
        verifier.exploreMaze();


        assertEquals("correct path", verifier.getPathResult());
    }

    @Test
    void verifyTinyMaze() {
        Maze maze = new Maze("./examples/tiny.maz.txt");
        maze.setMazeOpenings('E');  // assume we start at the east entrance
        PathFormatter pathFormatter = new PathFormatter();
        MazeExplorer finder = new PathFinder('E', maze, new RightHandAlgorithm());
        finder.exploreMaze();
        String result = finder.getPathResult();  
        pathFormatter.setFactorizedPath(result);
        String factorizedPathSequence = pathFormatter.getFactorizedPath();
        assertEquals("5F2L2FR2FR2F2L2FR2FR3F", factorizedPathSequence);

        Maze maze2 = new Maze("./examples/tiny.maz.txt");
        maze2.setMazeOpenings('E');  // assume we start at the east entrance
        MazeExplorer verifier = new PathVerifier('E', maze2, result);
        verifier.exploreMaze();

        assertEquals("correct path", verifier.getPathResult());
    }
}