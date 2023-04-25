package no.uib.inf101.sem2.grid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
* Testing the class Grid
*/
public class GridTest {
  
  @Test
  void gridTestGetRowsAndCols() {
    IGrid<Integer> grid = new Grid<Integer>(3, 2);
    //Sjekker at grid.rows/grid.cols gir den verdien vi har satt den til.
    assertEquals(3, grid.rows());
    assertEquals(2, grid.cols());
  }
  
  @Test
  void gridSanityTest() {
    String defaultValue = "x";
    IGrid<String> grid = new Grid<String>(3, 2, defaultValue);
    //Sjekker samme som testen over
    assertEquals(3, grid.rows());
    assertEquals(2, grid.cols());
    //Sjekker at den gitte celleposisjonen gir oss defaultValue
    assertEquals("x", grid.get(new CellPosition(0, 0)));
    assertEquals("x", grid.get(new CellPosition(2, 1)));
    
    grid.set(new CellPosition(1, 1), "y");
    //Sjekker om koordinatet (1,1) gir oss veriden y som er satt over, og at de andre verdiene gir oss x
    assertEquals("y", grid.get(new CellPosition(1, 1)));
    assertEquals("x", grid.get(new CellPosition(0, 1)));
    assertEquals("x", grid.get(new CellPosition(1, 0)));
    assertEquals("x", grid.get(new CellPosition(2, 1)));
  }
  
  @Test
  void gridCanHoldNull() {
    String defaultValue = "x";
    IGrid<String> grid = new Grid<>(3, 2, defaultValue);
    //Sjekker at gitte celleposisjoner gir oss defaultVaule som er satt
    assertEquals("x", grid.get(new CellPosition(0, 0)));
    assertEquals("x", grid.get(new CellPosition(2, 1)));
    
    grid.set(new CellPosition(1, 1), null);
    //Sjekker at koordinat(1,1) gir oss verdien null, som er satt over
    assertEquals(null, grid.get(new CellPosition(1, 1)));
    assertEquals("x", grid.get(new CellPosition(0, 1)));
    assertEquals("x", grid.get(new CellPosition(1, 0)));
    assertEquals("x", grid.get(new CellPosition(2, 1)));
  }
  
  @Test
  void gridNullsInDefaultConstructor() {
    IGrid<String> grid = new Grid<>(3, 2);
    //Default value er nå lik null, sjekker om gitte celleposisjoner er lik null
    assertEquals(null, grid.get(new CellPosition(0, 0)));
    assertEquals(null, grid.get(new CellPosition(2, 1)));
    
    grid.set(new CellPosition(1, 1), "y");
    //Setter koordinat (1,1) til y, sjekker om det stemmer, og at de andre veridene fortsatt er null
    assertEquals("y", grid.get(new CellPosition(1, 1)));
    assertEquals(null, grid.get(new CellPosition(0, 1)));
    assertEquals(null, grid.get(new CellPosition(1, 0)));
    assertEquals(null, grid.get(new CellPosition(2, 1)));
  }
  
  @Test
  void coordinateIsOnGridTest() {
    IGrid<Double> grid = new Grid<>(3, 2, 0.9);
    //Sjekker om det gitte koordinatet er innenfor griden
    assertTrue(grid.positionIsOnGrid(new CellPosition(2, 1)));
    assertFalse(grid.positionIsOnGrid(new CellPosition(3, 1)));
    assertFalse(grid.positionIsOnGrid(new CellPosition(2, 2)));
    
    assertTrue(grid.positionIsOnGrid(new CellPosition(0, 0)));
    assertFalse(grid.positionIsOnGrid(new CellPosition(-1, 0)));
    assertFalse(grid.positionIsOnGrid(new CellPosition(0, -1)));
  }
  
  @Test
  void throwsExceptionWhenCoordinateOffGrid() {
    IGrid<String> grid = new Grid<>(3, 2, "x");
    
    try {
      @SuppressWarnings("unused")
      String x = grid.get(new CellPosition(3, 1));
      fail();
    } catch (IndexOutOfBoundsException e) {
      // Test passed
    }
  }
  
  @Test
  void testIterator() {
    IGrid<String> grid = new Grid<>(3, 2, "x");
    grid.set(new CellPosition(0, 0), "a");
    grid.set(new CellPosition(1, 1), "b");
    grid.set(new CellPosition(2, 1), "c");
    
    List<GridCell<String>> items = new ArrayList<>();
    for (GridCell<String> coordinateItem : grid) {
      items.add(coordinateItem);
    }
    //Sjekker at listen er like stor som rows*cols
    assertEquals(3 * 2, items.size());
    //Sjekker at de ulike koordinatene inneholder det vi har satt de til, og at de andre posisjonene fortsatt har defaultValue
    assertTrue(items.contains(new GridCell<String>(new CellPosition(0, 0), "a")));
    assertTrue(items.contains(new GridCell<String>(new CellPosition(1, 1), "b")));
    assertTrue(items.contains(new GridCell<String>(new CellPosition(2, 1), "c")));
    assertTrue(items.contains(new GridCell<String>(new CellPosition(0, 1), "x")));
  }
}
