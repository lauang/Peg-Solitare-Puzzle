package no.uib.inf101.sem2.pegSolitare.model.peg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;

public class TestPeg {
    @Test
    public void TestPegBoardShapeW(){
        PegBoardShape shape = PegBoardShape.newPegBoardShape('W');

        List<GridCell<Character>> pegCells = new ArrayList<>();
        for (GridCell<Character> gc : shape) {
            if (gc.value() == 'B') {
                pegCells.add(gc);
            }
        }
        //Sjekker at vi får riktige verdier på antatt posisjon
        assertEquals(4, pegCells.size());
        assertTrue(pegCells.contains(new GridCell<Character>(new CellPosition(3, 4), 'B')));
        assertTrue(pegCells.contains(new GridCell<Character>(new CellPosition(4, 4), 'B')));
        assertTrue(pegCells.contains(new GridCell<Character>(new CellPosition(5, 4), 'B')));
        assertTrue(pegCells.contains(new GridCell<Character>(new CellPosition(2, 4), 'B')));
        assertFalse(pegCells.contains(new GridCell<Character>(new CellPosition(1, 4), 'B')));
    }

    @Test
    public void TestPegBoardShapeT(){
        PegBoardShape shape = PegBoardShape.newPegBoardShape('T');

        List<GridCell<Character>> pegCells = new ArrayList<>();
        for (GridCell<Character> gc : shape) {
            if (gc.value() == 'B') {
                pegCells.add(gc);
            }
        }
        //Sjekker at vi får riktige verdier på antatt posisjon
        assertEquals(10, pegCells.size());
        assertTrue(pegCells.contains(new GridCell<Character>(new CellPosition(3, 3), 'B')));
        assertTrue(pegCells.contains(new GridCell<Character>(new CellPosition(3, 4), 'B')));
        assertTrue(pegCells.contains(new GridCell<Character>(new CellPosition(3, 5), 'B')));
        assertTrue(pegCells.contains(new GridCell<Character>(new CellPosition(4, 4), 'B')));
        assertTrue(pegCells.contains(new GridCell<Character>(new CellPosition(4, 5), 'B')));
        assertTrue(pegCells.contains(new GridCell<Character>(new CellPosition(4, 3), 'B')));
        assertTrue(pegCells.contains(new GridCell<Character>(new CellPosition(5, 3), 'B')));
        assertTrue(pegCells.contains(new GridCell<Character>(new CellPosition(5, 4), 'B')));
        assertTrue(pegCells.contains(new GridCell<Character>(new CellPosition(5, 5), 'B')));
        assertTrue(pegCells.contains(new GridCell<Character>(new CellPosition(2, 4), 'B')));
        assertFalse(pegCells.contains(new GridCell<Character>(new CellPosition(0, 4), 'B')));
    }

    

}
