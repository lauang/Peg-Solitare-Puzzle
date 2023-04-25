package no.uib.inf101.sem2.pegSolitare.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.Font;
import javax.swing.JPanel;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.pegSolitare.model.GameState;
import no.uib.inf101.sem2.pegSolitare.model.PegModel;

/**
 * Class represents the view for the game
 */
public class PegSolitareView extends JPanel {
    private static final double OUTERMARGIN = 16;
    private static final double cellMargin = 1;

    private PegModel vPegModel;
    private ColorTheme colorTheme;

    //Konstruktør
    public PegSolitareView(PegModel vPegModel) {
        this.colorTheme = new DefaultColorTheme();
        this.vPegModel = vPegModel;
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(400, 400));
        this.setBackground(this.colorTheme.getBackgroundColor());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawGame(g2);
    }

    private void drawGame(Graphics2D graphics2d) {
        if (vPegModel.getGameState() == GameState.GAME_MENU) {
            Rectangle2D lerret = new Rectangle2D.Double(
            OUTERMARGIN, OUTERMARGIN, getWidth() -2*OUTERMARGIN, getHeight() - 2*OUTERMARGIN);
            drawGameMenu(graphics2d, lerret);
        }
        else {
            Rectangle2D lerret = new Rectangle2D.Double(
            OUTERMARGIN, OUTERMARGIN, getWidth() -2*OUTERMARGIN, getHeight() - 2*OUTERMARGIN);
            graphics2d.setColor(colorTheme.getFrameColor());
            graphics2d.fill(lerret);

            CellPositionToPixelConverter cellPixels = new CellPositionToPixelConverter(
                lerret, vPegModel.getDimension(), cellMargin);

            drawCells(graphics2d, vPegModel.getTilesOnBoard(), cellPixels, colorTheme, 'o');
            drawCells(graphics2d, vPegModel.getPins(), cellPixels, colorTheme, 'i');

        if (vPegModel.getGameState() == GameState.GAME_OVER || vPegModel.getGameState() == GameState.GAME_WON) drawGameState(graphics2d, lerret);
        }
    }

    private void drawGameState(Graphics2D graphics2d, Rectangle2D lerret) {
        graphics2d.setColor(colorTheme.getGameOverColor());
        graphics2d.fill(lerret);
      
        graphics2d.setColor(colorTheme.getTextColor());
        graphics2d.setFont(new Font("Arial", Font.BOLD, 34));
        if (vPegModel.getGameState() == GameState.GAME_OVER) {
            Inf101Graphics.drawCenteredString(graphics2d, "GAME OVER", lerret);
            graphics2d.drawString("Press R for main menu", 16, getHeight()/2 + 60);
        }
        if (vPegModel.getGameState() == GameState.GAME_WON) {
            Inf101Graphics.drawCenteredString(graphics2d, "GAME WON", lerret);
            graphics2d.drawString("Press R for main menu", 16, getHeight()/2 + 60);
        }
    }

    private void drawGameMenu(Graphics2D graphics2d, Rectangle2D lerret) {
        Rectangle2D menu = new Rectangle2D.Double(
            OUTERMARGIN, OUTERMARGIN, getWidth() -2*OUTERMARGIN, getHeight() - 2*OUTERMARGIN);
            graphics2d.setColor(colorTheme.getGameMenuColor());
            graphics2d.fill(menu);
            graphics2d.setColor(colorTheme.getTextColor());
            graphics2d.setFont(new Font("Arial", Font.BOLD, 40));
            graphics2d.drawString("Game menu", 90, getHeight()/4);
            graphics2d.drawString("Press 1 for level 1", 30, getHeight()/2);
            graphics2d.drawString("Press 2 for level 2", 30, getHeight()/2 + 40);
            graphics2d.drawString("Press 3 for level 3", 30, getHeight()/2 + 80);
            graphics2d.drawString("Press 4 for level 4", 30, getHeight()/2 + 120);
    }

    private void drawCells(Graphics2D graphics2d, Iterable<GridCell<Character>> iterable, CellPositionToPixelConverter cellPositionToPixelConverter, ColorTheme colorTheme, char c){
        if (c == 'o') {
            for (GridCell<Character> cell : iterable) {
                Rectangle2D rectangle = cellPositionToPixelConverter.getBoundsForCell(cell.pos()).box();
                Color color = colorTheme.getCellColor(cell.value());
                graphics2d.setColor(color);
                graphics2d.fill(rectangle);
            }
        } else {
            for (GridCell<Character> cell : iterable) {
                Ellipse2D rectangle = cellPositionToPixelConverter.getBoundsForCell(cell.pos()).circle();
                Color color = colorTheme.getCellColor(cell.value());
                graphics2d.setColor(color);
                graphics2d.fill(rectangle);
            }
        }
    }

}
