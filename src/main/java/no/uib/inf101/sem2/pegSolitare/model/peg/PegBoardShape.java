package no.uib.inf101.sem2.pegSolitare.model.peg;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;

/**
 * Class represents different pegboard shapes
 */
public class PegBoardShape implements Iterable<GridCell<Character>> {

    private boolean[][] boardShape;
    private CellPosition pos;

    //Konstruktør
    public PegBoardShape(char c, boolean[][] boardShape) {
        this.boardShape = boardShape;
        this.pos = new CellPosition(0, 0);
    }

    //Oppretter brettet med hensikt på hvilken char som parameter
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
            //Test board
            case 'T' -> boardShape = new boolean[][] {
                { false, false, false, false, false, false, false, false, false},
                { false, false, false, false, false, false, false, false, false},
                { false, false, false, false, true, false, false, false, false},
                { false, false, false, true, true, true, false, false, false},
                { false, false, false, true, true, true, false, false, false},
                { false, false, false, true, true, true, false, false, false},
                { false, false, false, false, false, false, false, false, false},
                { false, false, false, false, false, false, false, false, false},
                { false, false, false, false, false, false, false, false, false},
            };
            //Winning board
            case 'W' -> boardShape = new boolean[][] {
                { false, false, false, false, false, false, false, false, false},
                { false, false, false, false, false, false, false, false, false},
                { false, false, false, false, true, false, false, false, false},
                { false, false, false, false, true, false, false, false, false},
                { false, false, false, false, true, false, false, false, false},
                { false, false, false, false, true, false, false, false, false},
                { false, false, false, false, false, false, false, false, false},
                { false, false, false, false, false, false, false, false, false},
                { false, false, false, false, false, false, false, false, false},
            };
            default -> throw new IllegalArgumentException(
                "No available shape for '" + c + "'");
        };
        return new PegBoardShape(c, boardShape);
    }

    @Override
    public Iterator<GridCell<Character>> iterator() {
        //Går gjennom alle verdiene i pegboardshape
        List<GridCell<Character>> ifTrueList = new ArrayList<>();
        for (int i = 0; i < boardShape.length; i ++) {
          for (int j = 0; j < boardShape[0].length; j ++){
            //Hvis verdien er true, så får cellen verdien B
            //B er en gyldig posisjon i griden, blå celle
            if (boardShape [i][j] == true) {
                ifTrueList.add(new GridCell<Character>(
                    new CellPosition(this.pos.row() + i, this.pos.col() + j), 'B'));
            }

          }
        }
        return ifTrueList.iterator();
    }


    

    
}
