package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
            String mazeFile = cmd.getOptionValue("i");  // assigns the maze text file into to mazeFile
            logger.info("** Starting Maze Runner");

            try {
                logger.info("**** Reading the maze from file: {}", mazeFile);  
                BufferedReader reader = new BufferedReader(new FileReader(mazeFile));  // reads from retreived file path
                String line;
                while ((line = reader.readLine()) != null) {
                    for (int idx = 0; idx < line.length(); idx++) {
                        if (line.charAt(idx) == '#') {
                            logger.trace("WALL ");
                        } else if (line.charAt(idx) == ' ') {
                            logger.trace("PASS ");
                        }
                    }
                    logger.trace(System.lineSeparator());
                }
            } catch(Exception e) {
                logger.error("/!\\ An error has occured /!\\");
            }
        } catch (Exception e) {
            logger.error("/!\\ An error has occured while reading command-line arguments /!\\");
        }
        logger.info("**** Computing path");
        logger.warn("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}