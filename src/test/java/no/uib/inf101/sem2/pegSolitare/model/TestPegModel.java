package no.uib.inf101.sem2.pegSolitare.model;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.pegSolitare.model.peg.PegBoardInterface;
import no.uib.inf101.sem2.pegSolitare.model.peg.PegBoardShape;
import no.uib.inf101.sem2.pegSolitare.model.peg.PegLevel;
import no.uib.inf101.sem2.pegSolitare.model.pin.Pin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import java.util.ArrayList;


public class TestPegModel {
    @Test
    public void TestRemoveAndSetPeg() {
    PegBoard board = new PegBoard(9, 9);
    PegModel model = new PegModel(board); 
    
    model.pins.setPeg(new CellPosition(5, 5), 'i');

    List<GridCell<Character>> pegCells = new ArrayList<>();
    for (GridCell<Character> gc : model.getPins()) {
        pegCells.add(gc);
    }

    //Sjekker at posisjon 5,5 har en brikke
    assertTrue(pegCells.contains(new GridCell<>(new CellPosition(5, 5), 'i')));

    //Setter en brikke i 5, 5 i en ny liste
    model.pins.setPeg(new CellPosition(5, 5), 'i');
    // Fjerner brikken i posisjon 5, 5 i den nye listen
    model.removePeg(new CellPosition(5, 5));

    List<GridCell<Character>> pegCells2 = new ArrayList<>();
    for (GridCell<Character> gc : model.getPins()) {
        pegCells2.add(gc);
    }

    assertTrue(pegCells2.contains(new GridCell<>(new CellPosition(5, 5), 'o')));

    }

    @Test
    public void TestRemoveShadow(){

    PegBoard board = new PegBoard(9, 9);
    PegModel model = new PegModel(board); 

    //Setter en skygge på posisjonen 5, 5
    model.pins.setPeg(new CellPosition(5, 5), 'g');
    
    List<GridCell<Character>> pegCells = new ArrayList<>();
    for (GridCell<Character> gc : model.getPins()) {
        pegCells.add(gc);
    }

    //Sjekker at posisjon 5,5 har en skygge
    assertTrue(pegCells.contains(new GridCell<>(new CellPosition(5, 5), 'g')));
    //Setter en skygge i 5, 5 i en ny liste
    model.pins.setPeg(new CellPosition(5, 5), 'g');
    //Fjerner skyggen
    model.removeShadows();

    List<GridCell<Character>> pegCells2 = new ArrayList<>();
    for (GridCell<Character> gc : model.getPins()) {
        pegCells2.add(gc);
    }
    //Sjekker at 5, 5 ikke har skygge
    assertTrue(pegCells2.contains(new GridCell<>(new CellPosition(5, 5), 'o')));
    }

    @Test
    public void TestSelectPeg(){
    PegBoard board = new PegBoard(9, 9);
    PegModel model = new PegModel(board); 
    //Setter en peg på posisjonen 5, 5
    model.pins.setPeg(new CellPosition(5, 5), 'i');
    //Selecter peggen på posisjonen 5, 5
    model.selectPeg(new CellPosition(5, 5));
    
    List<GridCell<Character>> pegCells = new ArrayList<>();
    for (GridCell<Character> gc : model.getPins()) {
        pegCells.add(gc);
    }
    //Sjekker om peggen ble selected
    assertTrue(pegCells.contains(new GridCell<>(new CellPosition(5, 5), 'S')));
    model.selectCounter = 1;
    //Setter en selected peg på posisjonen 5, 5
    model.pins.setPeg(new CellPosition(5, 5), 'S');
    //Unselecter peggen på posisjonen 5, 5
    model.selectPeg(new CellPosition(5, 5));
    
    List<GridCell<Character>> pegCells2 = new ArrayList<>();
    for (GridCell<Character> gc : model.getPins()) {
        pegCells2.add(gc);
    }

    //Sjekker om peggen ble unselected
    assertTrue(pegCells2.contains(new GridCell<>(new CellPosition(5, 5), 'i')));
    model.selectCounter = 1;
    //Setter en skygge på posisjonen 5, 5
    model.pins.setPeg(new CellPosition(5, 5), 'g');
    //Flytter en peg til posisjonen 5, 5
    model.selectPeg(new CellPosition(5, 5));
    
    
    List<GridCell<Character>> pegCells3 = new ArrayList<>();
    for (GridCell<Character> gc : model.getPins()) {
        pegCells3.add(gc);
    }

    //Sjekker om skyggen ble omgjort til en peg
    assertTrue(pegCells3.contains(new GridCell<>(new CellPosition(5, 5), 'i')));
    }
    
    
    
}
