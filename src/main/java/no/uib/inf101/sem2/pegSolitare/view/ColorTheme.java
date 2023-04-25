package no.uib.inf101.sem2.pegSolitare.view;

import java.awt.Color;

/**
 * Interface for the colors
 */
public interface ColorTheme {
    
    /**
     * 
     * @param c
     * @return a color of each cell given a charater
     * @throws IllegalArgumentException throws exception if value = null and if the char is undefined 
     */
    Color getCellColor(char c) throws IllegalArgumentException;

    /**
     * This returns the color for the game-frame
     * @return color
     */
    Color getFrameColor();

    /**
     * This returns the backgorund color of the game-board
     * @return color
     */
    Color getBackgroundColor();

    /**
     * This returns the game over color on top of the game-board
     * @return color
     */

    Color getGameOverColor ();

    /**
     * This returns the text color
     * @return color
     */

    Color getTextColor ();

    /**
     * This returns the game menu color
     * @return color
     */

     Color getGameMenuColor ();
}
