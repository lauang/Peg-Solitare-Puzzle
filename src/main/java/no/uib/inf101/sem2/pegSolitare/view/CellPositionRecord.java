package no.uib.inf101.sem2.pegSolitare.view;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * @param box a Rectangle2D
 * @param cirkle a Ellipse2D
 */
public record CellPositionRecord(Rectangle2D box, Ellipse2D circle) {
}
