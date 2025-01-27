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
            logger.info("** Starting Maze Runner");
            Maze maze = new Maze(mazeFile);
            maze.printMaze();

            String moveSequence = cmd.getOptionValue("p");
            MazeExplorer explorer = new MazeExplorer(maze);
            // checks if the user provided a move sequence to determine which algorithm to call
            if (moveSequence != null) {
                logger.info("**** Verifying path");
                explorer.verifyInputPath(moveSequence);
            }
            else {
                logger.info("**** Computing path");
                String pathSequence = explorer.findPath();
                logger.warn("PATH NOT COMPUTED");
                System.out.println(pathSequence);

            }
            logger.info("** End of MazeRunner");

        } catch (ParseException e) {
            logger.error("/!\\ An error has occured: {} /!\\", e.getMessage());
        } catch (Exception e) {
            logger.error("/!\\ An error has occured: {} /!\\", e.getMessage());
        }
    }

    public static Options setOptions() {
        Options flagOptions = new Options();  
        // sets the "-i" and "-p" flag with its corresponding message, and their requirement
        Option iFlag = setOption("i", "Text file that contains the maze.", "R");
        flagOptions.addOption(iFlag);
        Option pFlag = setOption("p", "Move path containing various move directions", "N");
        flagOptions.addOption(pFlag);

        return flagOptions;
    }

    public static Option setOption(String flag, String message, String required) {
        // checks if it is a required flag
        Option cmdFlag;
        if (required.equalsIgnoreCase("R")) {
            cmdFlag = Option.builder(flag) 
                .hasArg()  // flag must have an argument proceeding it
                .desc(message)
                .build();
        }
        else {
            cmdFlag = Option.builder(flag) 
                .hasArg()
                .desc(message)
                .build();
        }
        return cmdFlag;
    }
}