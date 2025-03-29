package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Options options = setOptions();  
        CommandLineParser parser = new DefaultParser();  

        try {
            CommandLine cmd = parser.parse(options, args);  // parses the command-line arguments
            String mazeFile = cmd.getOptionValue("i");  // gets the string associated witb the i flag
            String moveSequence = cmd.getOptionValue("p");  // gets the string associated witb the p flag

            Maze maze = new Maze(mazeFile);
            PathFormatter formatter = new PathFormatter();
            logger.info("** Starting Maze Runner");

            // checks if the user provided a move sequence to determine which algorithm to call
            if (moveSequence != null) {
                analyzePath(maze, formatter, moveSequence);
            }

            // if no -p flag is present, find the exit path sequence
            else {
                findPath(maze, formatter);
            }
            logger.info("** End of MazeRunner");

        } catch (Exception e) {
            System.err.println("/!\\ An error has occured: " + e.getMessage() + " /!\\");
        }
    }

    private static Options setOptions() {
        Options flagOptions = new Options();  
        // sets the "-i" and "-p" flag with its corresponding message, and their requirement
        Option iFlag = setOption("i", "Text file that contains the maze.", "R");
        flagOptions.addOption(iFlag);
        Option pFlag = setOption("p", "Move path containing various move directions", "N");
        flagOptions.addOption(pFlag);

        return flagOptions;
    }

    private static Option setOption(String flag, String message, String required) {
        // creates a new command-line option 
        Option cmdFlag;
        cmdFlag = Option.builder(flag) 
                .hasArg()
                .desc(message)
                .build();
        return cmdFlag;
    }

    private static void analyzePath(Maze maze, PathFormatter formatter, String moveSequence) {
        logger.info("**** Verifying path");
        formatter.setCanonicalPath(moveSequence);
        moveSequence = formatter.getCanonicalPath();  // converts the moveSequence to its canonical form before proceeding
        
        // calls the verify path method for both starting directions
        String result1 = verifyPath(maze, 'E', moveSequence);
        String result2 = verifyPath(maze, 'W', moveSequence);
        System.out.println(getResult(result1, result2));
    }

    private static void findPath(Maze maze, PathFormatter formatter) {
        maze.setMazeOpenings('E');  // assume we start at the east entrance
        
        logger.info("**** Computing path");
        logger.warn("PATH NOT COMPUTED");
        // determines the path sequence to exit the maze starting from the west side
        MazeExplorer finder = new PathFinder('E', maze, new RightHandAlgorithm());
        finder.exploreMaze();

        String result = finder.getPathResult();  
        formatter.setFactorizedPath(result);
        String factorizedPathSequence = formatter.getFactorizedPath();  // converts the result to its factoirzed form
        System.out.println(factorizedPathSequence);
    }

    private static String verifyPath(Maze maze, char entrance, String moveSequence) {
        // verifies the path sequence based on the given entrance
        maze.setMazeOpenings(entrance);
        MazeExplorer verifier = new PathVerifier(entrance, maze, moveSequence);
        verifier.exploreMaze();
        return verifier.getPathResult();
    }

    private static String getResult(String result1, String result2) {
        // prints correct path if one of the starting entrances is resulted in the explorer to exit
        if (result2.equalsIgnoreCase("correct path") || result1.equalsIgnoreCase("correct path")) {
            return "correct path";
        }
        else {
            return "incorrect path";
        }
    }
}