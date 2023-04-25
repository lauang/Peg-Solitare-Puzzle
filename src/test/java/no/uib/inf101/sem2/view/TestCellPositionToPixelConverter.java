package no.uib.inf101.sem2.view;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.pegSolitare.model.PegBoard;
import no.uib.inf101.sem2.pegSolitare.view.CellPositionRecord;
import no.uib.inf101.sem2.pegSolitare.view.CellPositionToPixelConverter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class TestCellPositionToPixelConverter {
  @Test
  public void sanityTest() {
    GridDimension gd = new PegBoard(3, 4);

    CellPositionToPixelConverter converter2 = new CellPositionToPixelConverter(
      new Rectangle2D.Double(29, 29, 340, 240), gd, 30);

    Rectangle2D expected = new Rectangle2D.Double(214, 129, 47.5, 40);
    Ellipse2D expected2 = new Ellipse2D.Double(214, 129, 47.5, 40);

    CellPositionRecord expectedRecord = new CellPositionRecord(expected, expected2);
    
    assertEquals(expectedRecord, converter2.getBoundsForCell(new CellPosition(1, 2)));
}

}

