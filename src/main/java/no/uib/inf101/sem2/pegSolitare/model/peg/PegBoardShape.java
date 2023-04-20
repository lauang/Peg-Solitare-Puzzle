package no.uib.inf101.sem2.pegSolitare.model.peg;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.pegSolitare.model.pin.Pin;

public class PegBoardShape implements Iterable<GridCell<Character>> {

    private char c;
    public boolean[][] boardShape;
    private CellPosition pos;

    public PegBoardShape(char c, boolean[][] boardShape) {
        //Lagrer feltvariablene 
        this.c = c;
        this.boardShape = boardShape;
        this.pos = new CellPosition(0, 0);
    }

    public static PegBoardShape newPegBoardShape(char c){
        boolean[][] boardShape = switch (c) {
            case 'E' -> boardShape = new boolean[][] {
                { false, false, false, false, false, false, false, false, false},
                { false, false, false, false, false, false, false, false, false},
                { false, false, false, true, true, true, false, false, false},
                { false, false, true, true, true, true, true, false, false},
                { false, false, true, true, true, true, true, false, false},
                { false, false, true, true, true, true, true, false, false},
                { false, false, false, true, true, true, false, false, false},
                { false, false, false, false, false, false, false, false, false},
                { false, false, false, false, false, false, false, false, false},
            };

            case 'F' -> boardShape = new boolean[][] {
                { false, false, false, false, false, false, false, false, false},
                { false, false, false, true, true, true, false, false, false},
                { false, false, false, true, true, true, false, false, false},
                { false, true, true, true, true, true, true, true, false},
                { false, true, true, true, true, true, true, true, false},
                { false, true, true, true, true, true, true, true, false},
                { false, false, false, true, true, true, false, false, false},
                { false, false, false, true, true, true, false, false, false},
                { false, false, false, false, false, false, false, false, false},
            };

            case 'O' -> boardShape = new boolean[][] {
                { false, false, false, false, false, false, false, false, false},
                { false, false, false, true, true, true, false, false, false},
                { false, false, true, true, true, true, true, false, false},
                { false, true, true, true, true, true, true, true, false},
                { false, true, true, true, true, true, true, true, false},
                { false, true, true, true, true, true, true, true, false},
                { false, false, true, true, true, true, true, false, false},
                { false, false, false, true, true, true, false, false, false},
                { false, false, false, false, false, false, false, false, false},
            };
            default -> throw new IllegalArgumentException(
                "No available shape for '" + c + "'");
        };
        return new PegBoardShape(c, boardShape);
    }

    @Override
    public Iterator<GridCell<Character>> iterator() {
        
        List<GridCell<Character>> ifTrueList = new ArrayList<>();
        for (int i = 0; i < boardShape.length; i ++) {
          for (int j = 0; j < boardShape[0].length; j ++){

            if (boardShape [i][j] == true) {
                ifTrueList.add(new GridCell<Character>(
                    new CellPosition(this.pos.row() + i, this.pos.col() + j), 'B'));
            }

          }
        }
        return ifTrueList.iterator();
    }

    

    
}
