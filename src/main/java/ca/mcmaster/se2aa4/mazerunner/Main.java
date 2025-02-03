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
            // assigns the associate argument with the flag to a String
            String mazeFile = cmd.getOptionValue("i");  
            String moveSequence = cmd.getOptionValue("p");

            Maze maze = new Maze(mazeFile);
            PathFormatter formatter = new PathFormatter();
            logger.info("** Starting Maze Runner");

            // checks if the user provided a move sequence to determine which algorithm to call
            if (moveSequence != null) {
                logger.info("**** Verifying path");
                formatter.setCanonicalPath(moveSequence);
                moveSequence = formatter.getCanonicalPath();  // converts the moveSequence to its canonical form before proceeding

                String result1 = verifyPath(maze, 'E', moveSequence);
                String result2 = verifyPath(maze, 'W', moveSequence);
                System.out.println(getResult(result1, result2));
            }

            // if no -p flag is present, find the exit path sequence
            else {
                // assume we start at the east entrance
                maze.setEntrance('E');
                DirectionAnalyzer directionAnalyzer = new DirectionAnalyzer('E', maze, maze.getEntrance());
                logger.info("**** Computing path");
                logger.warn("PATH NOT COMPUTED");
                MazeExplorer finder = new PathFinder(maze, directionAnalyzer, new RightHandAlgorithm());
                finder.exploreMaze();
                String result = finder.getPathResult();
                formatter.setFactorizedPath(result);
                String factorizedPathSequence = formatter.getFactorizedPath();  // converts the result to its factoirzed form
                System.out.println(factorizedPathSequence);
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
        // creates a new option 
        Option cmdFlag;
        cmdFlag = Option.builder(flag) 
                .hasArg()
                .desc(message)
                .build();
        return cmdFlag;
    }

    private static String getResult(String result1, String result2) {
        // prints correct path if one of them was determined to be true
        if (result2.equalsIgnoreCase("correct path") || result1.equalsIgnoreCase("correct path")) {
            return "correct path";
        }
        else {
            return "incorrect path";
        }
    }

    private static String verifyPath(Maze maze, char entrance, String moveSequence) {
        // verifies the path based on the given entrance
        maze.setEntrance(entrance);
        DirectionAnalyzer directionAnalyzer = new DirectionAnalyzer(entrance, maze, maze.getEntrance());
        MazeExplorer verifier = new PathVerifier(maze, directionAnalyzer, moveSequence);
        verifier.exploreMaze();
        return verifier.getPathResult();
    }
}