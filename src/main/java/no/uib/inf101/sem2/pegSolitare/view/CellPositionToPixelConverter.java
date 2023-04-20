package no.uib.inf101.sem2.pegSolitare.view;


import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridDimension;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class CellPositionToPixelConverter {
  private Rectangle2D box;
  private GridDimension gd;
  private double margin;

  public CellPositionToPixelConverter(Rectangle2D box, GridDimension gd, double margin) {
      this.box = box;
      this.gd = gd;
      this.margin = margin; 
  }

  public CellPositionRecord getBoundsForCell(CellPosition cp) {
      double cellWidth = (box.getWidth() - (gd.cols() + 1) * margin) / gd.cols();
      double cellHeight = (box.getHeight() - (gd.rows() + 1) * margin) /gd.rows();
      double cellX = box.getX() + margin + cp.col() * (margin + cellWidth);
      double cellY = box.getY() + margin + cp.row() * (margin + cellHeight);
      return new CellPositionRecord(new Rectangle2D.Double(cellX, cellY, cellWidth, cellHeight), 
      new Ellipse2D.Double(cellX, cellY, cellWidth, cellHeight));
  }
}

