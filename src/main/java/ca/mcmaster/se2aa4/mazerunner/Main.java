package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Options options = setOptions();  // creates the Options object to define command-line options
        CommandLineParser parser = new DefaultParser();  // creates a parser for reading command-line arguments

        try {
            CommandLine cmd = parser.parse(options, args);  // parses the command-line arguments
            String mazeFile = cmd.getOptionValue("i");  // assigns the maze text file to mazeFile
            String moveSequence = cmd.getOptionValue("p");

            Maze maze = new Maze(mazeFile);
            DirectionAnalyzer directionAnalyzer = new DirectionAnalyzer('E', maze, maze.getEntrance('E'));
            PathFormatter formatter = new PathFormatter();
            logger.info("** Starting Maze Runner");

            // checks if the user provided a move sequence to determine which algorithm to call
            if (moveSequence != null) {
                logger.info("**** Verifying path");
                formatter.setCanonicalPath(moveSequence);
                moveSequence = formatter.getCanonicalPath();
                MazeExplorer verifier = new PathVerifier(maze, directionAnalyzer, moveSequence);
                verifier.exploreMaze();
                String result = verifier.getPathResult();
                System.out.println(result);
            }

            // if no -p flag is present, find the exit path sequence
            else {
                logger.info("**** Computing path");
                logger.warn("PATH NOT COMPUTED");
                MazeExplorer finder = new PathFinder(maze, directionAnalyzer, new RightHandAlgorithm());
                finder.exploreMaze();
                String result = finder.getPathResult();
                formatter.setFactorizedPath(result);
                String factorizedPathSequence = formatter.getFactorizedPath();
                System.out.println(factorizedPathSequence);
            }
            logger.info("** End of MazeRunner");

        } catch (Exception e) {
            logger.error("/!\\ An error has occured: {} /!\\", e.getMessage());
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
}