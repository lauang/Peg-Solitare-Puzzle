package no.uib.inf101.sem2.pegSolitare.model;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridDimension;


/**
 * @author Mathias Hop Ness
 * Class converts a pixel position to a cellposition
 */

public class PixelToCellPositionConverter { 

    private CellData area;
    private GridDimension gd;
    private double margin;
    
    //Constructor
    public PixelToCellPositionConverter(CellData area, GridDimension gd, double margin) {
        this.area = area;
        this.gd = gd;
        this.margin = margin;
    }
    
    /**
     * Converts a position to cellposition
     * @param pos a position
     * @return a cellposition
     */
    public CellPosition PixelToCellPosition(Position pos){
        double x = pos.x();
        double y = pos.y();
        double areaY = this.area.cellY();
        double areaX = this.area.cellX();
        double areaWidth = this.area.cellWidth();
        double areaHeight = this.area.cellHeight();
        double cellWidth = (areaWidth - ((gd.cols() + 1) * margin)) / gd.cols();
        double cellHeight = (areaHeight - ((gd.rows() + 1) * margin)) / gd.rows();
        double row = ((y - areaY - (gd.rows() + 1) * margin) / cellHeight);
        double col = ((x - areaX - (gd.cols() + 1) * margin) / cellWidth);
        return new CellPosition((int)row, (int)col);
    }
    
    /**
     * Checks if a position is on the grid, 
     * @param pos a position
     * @return false if not on grid, and true if on grid
     */
    public boolean pixelIsOnGrid(Position pos) {
        double x = pos.x();
        double y = pos.y();
        if (x < this.area.cellX()) return false;
        else if (x > this.area.cellX() + this.area.cellWidth()) return false;
        else if (y < this.area.cellY()) return false;
        else if (y > this.area.cellY() + this.area.cellHeight()) return false;
        else return true;
    }

    
}