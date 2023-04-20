package no.uib.inf101.sem2.pegSolitare.model;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.Grid;

public class PegBoard extends Grid<Character>{
    
    public PegBoard(int rows, int cols) {
        super(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++){
                this.set(new CellPosition(i, j), '-');
            }
        }
    }

    

    

    
}
