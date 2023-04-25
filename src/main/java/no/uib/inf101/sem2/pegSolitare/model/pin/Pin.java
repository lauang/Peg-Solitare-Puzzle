package no.uib.inf101.sem2.pegSolitare.model.pin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.pegSolitare.model.PegBoard;
import no.uib.inf101.sem2.pegSolitare.model.peg.PegBoardShape;

/**
 * Class represent a 2D list of pins
 */
public class Pin implements Iterable<GridCell<Character>>{
    int rows;
    int cols;
    private PegBoardShape pegBoardShape;
    private List<List<GridCell<Character>>> pegList;
    private PegBoard board;

    //Konstruktør
    public Pin (PegBoardShape pegBoardShape, PegBoard board) {
        this.pegBoardShape = pegBoardShape;
        this.board = board;
        this.pegList = new ArrayList<>();

        for (int x = 0; x < board.rows(); x ++){
            //Lager en rekke
            this.pegList.add(x, new ArrayList<>());
            for (int y = 0; y < board.cols(); y++){
                //Lager en kollonne
                pegList.get(x).add(new GridCell<Character>(new CellPosition(x, y), '-'));
            }
        }
        fillTheBoard();
    }
    //Fyller brettet med brikker på alle plasser der det allerede finnes en B
    public void fillTheBoard() {
        for (GridCell<Character> gc : pegBoardShape) {
            if (gc.value() == 'B') {
                this.setPeg(gc.pos(), 'i');
            }
        }
    }

    //Iterator, går igjennom brettet og legger til verdier i result
    //Henter verdier fra pegList
    @Override
    public Iterator<GridCell<Character>> iterator() {
        List<GridCell<Character>> result = new ArrayList<>();
        for (int i = 0; i < board.rows(); i++) {
          for (int j = 0; j < board.cols(); j++){
            result.add(new GridCell<Character>(new CellPosition(i, j), pegList.get(i).get(j).value()));
          }
        }
        return result.iterator();
    }

    //Setter en peg på en gitt celle 
    public void setPeg(CellPosition cp, char c) {
        this.pegList.get(cp.row()).set(cp.col(), new GridCell<Character>(cp, c));
    }
    //Henter ut verdien på en gitt celle
    public char getPegValue(CellPosition cp) {
        return pegList.get(cp.row()).get(cp.col()).value();
    }
    
}
