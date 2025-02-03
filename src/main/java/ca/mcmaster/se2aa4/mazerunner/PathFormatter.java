package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathFormatter {
    private static final Logger logger = LogManager.getLogger();
    private String canonicalPath;
    private String factorizedPath;

    public void setCanonicalPath(String pathString) {
        this.canonicalPath = pathString; // initially sets the canonical paht to what was received from user input
        // reads through the move sequence to check for a number --> determine if it is in factorized form
        for (int i = 0; i < pathString.length(); i++) {
            if (Character.isDigit(pathString.charAt(i))) {
                this.canonicalPath = factorizedToCanonical(pathString);  // if a number is present, convert from factorized form to canonical form
                return;
            }
        }
    }

    public String getCanonicalPath() {
        return this.canonicalPath;
    }

    private String factorizedToCanonical(String pathString) {
        StringBuffer canonicalSequence = new StringBuffer();
        int repeatNumber;
        String repeatDirection;
        
        // loops through the entire path sequence
        for (int i = 0; i < pathString.length(); i++) {
            // Case I: the repeat number occurs before the move direction
            if (Character.isDigit(pathString.charAt(i))) {
                StringBuffer numberString = new StringBuffer();
                // while loop to handle multi digit numbers
                while (i < pathString.length() && Character.isDigit(pathString.charAt(i))) {
                    numberString.append(pathString.charAt(i));
                    i++;
                }

                // if i is greater than the path lengt, than no direction proceeds a repeat number, so exit
                if (i >= pathString.length()) {
                    throw new IllegalArgumentException("Invalid path format");
                }

                repeatNumber = Integer.parseInt(numberString.toString());  // parses 'repeat number' char to its corresponding int value
                repeatDirection = String.valueOf(pathString.charAt(i));  // parses 'move direction' char to string
                for (int j = 1; j <= repeatNumber; j++) {
                    canonicalSequence.append(repeatDirection);  // adds the direction for the number of times it is repeated
                }
            }
            // Case II: simply append the move direction if no repeat number is present
            else {
                canonicalSequence.append(pathString.charAt(i));
            }
        }
        logger.trace("Canonical path sequence: {}", canonicalSequence.toString());
        return canonicalSequence.toString();
    }

    public void setFactorizedPath(String canonicalPath) {
        StringBuffer factorizedPath = new StringBuffer();

        // iterates through each character in the canonical string path
        for (int i = 0; i < canonicalPath.length(); i++)  {
            int movementCount = 1;
            
            // continues to loop while the current character is the same as the proceeding character
            while (i + 1 < canonicalPath.length() && canonicalPath.charAt(i) == canonicalPath.charAt(i + 1)) {
                movementCount++;  // increments the count for how many times the movement occurs in a row
                i++;
            }

            // only append the movement count if the movement is repeated at least twice
            if (movementCount >= 2) {
                factorizedPath.append(Integer.toString(movementCount));
                factorizedPath.append(canonicalPath.charAt(i));
            }
            // if there is only one occurence of the movement, just append the move
            else {
                factorizedPath.append(canonicalPath.charAt(i));
            }
            logger.trace("Appending {}{}", movementCount, canonicalPath.charAt(i));
        }
        this.factorizedPath = factorizedPath.toString();
    }

    public String getFactorizedPath() {
        return this.factorizedPath;
    }
}