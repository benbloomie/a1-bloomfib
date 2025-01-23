package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Options options = new Options();  // creates the Options object to define command-line options
        Option iFlag = Option.builder("i")  // creates an argument option for the "-i" flag
            .hasArg()
            .desc("Text file that contains the maze.")
            .required()
            .build();
        options.addOption(iFlag);  // add the option flag "-i"
        CommandLineParser parser = new DefaultParser();  // creates a parser for reading command-line arguments

        try {
            CommandLine cmd = parser.parse(options, args);  // parses the command-line arguments
            String mazeFile = cmd.getOptionValue("i");  // assigns the maze text file to mazeFile
            logger.info("** Starting Maze Runner");

            Maze maze = new Maze(mazeFile);
            maze.printMaze();

            String basePath = "FFFFF";  // IMPLEMENT -P FLAG LOGIC HERE
            MazeExplorer explorer = new MazeExplorer(maze);
            explorer.verifyInputPath(basePath);

        } catch (Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
        logger.info("**** Computing path");
        logger.warn("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}