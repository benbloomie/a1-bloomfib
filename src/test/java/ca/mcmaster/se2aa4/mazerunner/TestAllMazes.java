package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestAllMazes {

    @Test
    void verifyDirectMaze() {
        Maze maze = new Maze.MazeBuilder()
            .loadMazeFromFile("./examples/direct.maz.txt")
            .withStartingDirection('E')
            .build();  
        PathFormatter pathFormatter = new PathFormatter();
        MazeExplorer finder = new PathFinder('E', maze, new RightHandAlgorithm('E', maze));
        finder.exploreMaze();
        String result = finder.getPathResult();  
        pathFormatter.setFactorizedPath(result);
        String factorizedPathSequence = pathFormatter.getFactorizedPath();
        assertEquals("FR2FL3FRFLFRFL2F", factorizedPathSequence);

        Maze maze2 = new Maze.MazeBuilder()
            .loadMazeFromFile("./examples/direct.maz.txt")
            .withStartingDirection('E')
            .build(); 
        MazeExplorer verifier = new PathVerifier('E', maze2, result);
        verifier.exploreMaze();

        assertEquals("correct path", verifier.getPathResult());
    }

    @Test
    void verifyGiantMaze() {
        Maze maze = new Maze.MazeBuilder()
            .loadMazeFromFile("./examples/giant.maz.txt")
            .withStartingDirection('E')
            .build();  
        MazeExplorer finder = new PathFinder('E', maze, new RightHandAlgorithm('E', maze));
        finder.exploreMaze();
        String result = finder.getPathResult();  

        Maze maze2 = new Maze.MazeBuilder()
            .loadMazeFromFile("./examples/giant.maz.txt")
            .withStartingDirection('E')
            .build();  
        MazeExplorer verifier = new PathVerifier('E', maze2, result);
        verifier.exploreMaze();
        assertEquals("correct path", verifier.getPathResult());
    }

    @Test
    void verifyHugeMaze() {
        Maze maze = new Maze.MazeBuilder()
            .loadMazeFromFile("./examples/huge.maz.txt")
            .withStartingDirection('E')
            .build();  
        MazeExplorer finder = new PathFinder('E', maze, new RightHandAlgorithm('E', maze));
        finder.exploreMaze();
        String result = finder.getPathResult();  

        Maze maze2 = new Maze.MazeBuilder()
            .loadMazeFromFile("./examples/huge.maz.txt")
            .withStartingDirection('E')
            .build();  
        MazeExplorer verifier = new PathVerifier('E', maze2, result);
        verifier.exploreMaze();
        assertEquals("correct path", verifier.getPathResult());
    }

    @Test
    void verifyLargeMaze() {
        Maze maze = new Maze.MazeBuilder()
            .loadMazeFromFile("./examples/large.maz.txt")
            .withStartingDirection('E')
            .build();  
        MazeExplorer finder = new PathFinder('E', maze, new RightHandAlgorithm('E', maze));
        finder.exploreMaze();
        String result = finder.getPathResult();  

        Maze maze2 = new Maze.MazeBuilder()
            .loadMazeFromFile("./examples/large.maz.txt")
            .withStartingDirection('E')
            .build();  
        MazeExplorer verifier = new PathVerifier('E', maze2, result);
        verifier.exploreMaze();
        assertEquals("correct path", verifier.getPathResult());
    }

    @Test
    void verifyMediumMaze() {
        Maze maze = new Maze.MazeBuilder()
            .loadMazeFromFile("./examples/medium.maz.txt")
            .withStartingDirection('E')
            .build();      
        MazeExplorer finder = new PathFinder('E', maze, new RightHandAlgorithm('E', maze));
        finder.exploreMaze();
        String result = finder.getPathResult();  

        Maze maze2 = new Maze.MazeBuilder()
            .loadMazeFromFile("./examples/medium.maz.txt")
            .withStartingDirection('E')
            .build();   
        MazeExplorer verifier = new PathVerifier('E', maze2, result);
        verifier.exploreMaze();
        assertEquals("correct path", verifier.getPathResult());
    }

    @Test
    void verifyRectangleMaze() {
        Maze maze = new Maze.MazeBuilder()
            .loadMazeFromFile("./examples/rectangle.maz.txt")
            .withStartingDirection('E')
            .build();
        MazeExplorer finder = new PathFinder('E', maze, new RightHandAlgorithm('E', maze));
        finder.exploreMaze();
        String result = finder.getPathResult();  

        Maze maze2 = new Maze.MazeBuilder()
            .loadMazeFromFile("./examples/rectangle.maz.txt")
            .withStartingDirection('E')
            .build();
        MazeExplorer verifier = new PathVerifier('E', maze2, result);
        verifier.exploreMaze();
        assertEquals("correct path", verifier.getPathResult());
    }

    @Test
    void verifyRegularMaze() {
        Maze maze = new Maze.MazeBuilder()
            .loadMazeFromFile("./examples/regular.maz.txt")
            .withStartingDirection('E')
            .build();
        MazeExplorer finder = new PathFinder('E', maze, new RightHandAlgorithm('E', maze));
        finder.exploreMaze();
        String result = finder.getPathResult();  

        Maze maze2 = new Maze.MazeBuilder()
            .loadMazeFromFile("./examples/regular.maz.txt")
            .withStartingDirection('E')
            .build();
        MazeExplorer verifier = new PathVerifier('E', maze2, result);
        verifier.exploreMaze();
        assertEquals("correct path", verifier.getPathResult());
    }

    @Test
    void verifySmallMaze() {
        Maze maze = new Maze.MazeBuilder()
            .loadMazeFromFile("./examples/small.maz.txt")
            .withStartingDirection('E')
            .build();
        PathFormatter pathFormatter = new PathFormatter();
        MazeExplorer finder = new PathFinder('E', maze, new RightHandAlgorithm('E', maze));
        finder.exploreMaze();
        String result = finder.getPathResult();  
        pathFormatter.setFactorizedPath(result);
        String factorizedPathSequence = pathFormatter.getFactorizedPath();
        assertEquals("FRF2L2FR2FR2F2L4FR2FR4F2L2FR4FR2FR2F2L2FL2FL4FR2FR2F2L4FR2FR2F2L2FR2FR4FR2FL2FR2FLF", factorizedPathSequence);

        Maze maze2 = new Maze.MazeBuilder()
            .loadMazeFromFile("./examples/small.maz.txt")
            .withStartingDirection('E')
            .build();
        MazeExplorer verifier = new PathVerifier('E', maze2, result);
        verifier.exploreMaze();
        assertEquals("correct path", verifier.getPathResult());
    }

    @Test
    void verifyStraightMaze() {
        Maze maze = new Maze.MazeBuilder()
            .loadMazeFromFile("./examples/straight.maz.txt")
            .withStartingDirection('E')
            .build();
        PathFormatter pathFormatter = new PathFormatter();
        MazeExplorer finder = new PathFinder('E', maze, new RightHandAlgorithm('E', maze));
        finder.exploreMaze();
        String result = finder.getPathResult();  
        pathFormatter.setFactorizedPath(result);
        String factorizedPathSequence = pathFormatter.getFactorizedPath();
        assertEquals("4F", factorizedPathSequence);

        Maze maze2 = new Maze.MazeBuilder()
            .loadMazeFromFile("./examples/straight.maz.txt")
            .withStartingDirection('E')
            .build();
        MazeExplorer verifier = new PathVerifier('E', maze2, result);
        verifier.exploreMaze();
        assertEquals("correct path", verifier.getPathResult());
    }

    @Test
    void verifyTinyMaze() {
        Maze maze = new Maze.MazeBuilder()
            .loadMazeFromFile("./examples/tiny.maz.txt")
            .withStartingDirection('E')
            .build();
        PathFormatter pathFormatter = new PathFormatter();
        MazeExplorer finder = new PathFinder('E', maze, new RightHandAlgorithm('E', maze));
        finder.exploreMaze();
        String result = finder.getPathResult();  
        pathFormatter.setFactorizedPath(result);
        String factorizedPathSequence = pathFormatter.getFactorizedPath();
        assertEquals("5F2L2FR2FR2F2L2FR2FR3F", factorizedPathSequence);

        Maze maze2 = new Maze.MazeBuilder()
            .loadMazeFromFile("./examples/tiny.maz.txt")
            .withStartingDirection('E')
            .build();
        MazeExplorer verifier = new PathVerifier('E', maze2, result);
        verifier.exploreMaze();
        assertEquals("correct path", verifier.getPathResult());
    }
}