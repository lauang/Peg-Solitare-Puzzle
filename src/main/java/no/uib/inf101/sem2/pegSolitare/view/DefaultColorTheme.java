package no.uib.inf101.sem2.pegSolitare.view;

import java.awt.Color;

public class DefaultColorTheme implements ColorTheme {

    @Override
    public Color getCellColor(char c) throws IllegalArgumentException {
        Color color = switch (c) {
            case 'i' -> Color.RED;                
            case 'B' -> Color.BLUE; 
            case 'g' -> Color.GRAY;
            case 'o' -> Color.BLUE; 
            case '-' -> Color.WHITE;
            case 'S' -> Color.PINK;  

            default -> throw new IllegalArgumentException(
                "No available color for '" + c + "'");    
        };
        return color;
    }

    @Override
    public Color getFrameColor() {
        return Color.WHITE;   
    }

    @Override
    public Color getBackgroundColor() {
        return null;
    }

    @Override
    public Color getGameOverColor() {
        return new Color(0, 0, 0, 128);
    }

    @Override
    public Color getTextColor() {
        return Color.WHITE;
    }

    @Override
    public Color getGameMenuColor() {
        return Color.GRAY;
    }
    
}
