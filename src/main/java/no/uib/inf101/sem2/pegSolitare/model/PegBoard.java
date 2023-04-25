package no.uib.inf101.sem2.pegSolitare.model;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.Grid;

/**
 * Class represents a grid to draw a pegboard on
 */
public class PegBoard extends Grid<Character>{
    //Lager ett brett på gitt antall rader og kolonner
    public PegBoard(int rows, int cols) {
        super(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++){
                //Setter verdien på alle cellene til "-"
                this.set(new CellPosition(i, j), '-');
            }
        }
    }
}
