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

    /**
     * To test the pegboard
     * @return a string
     */
    public String prettyString() {
        String result = "";
        for (int i = 0; i < rows; i++){
            String row = "";
            for (int j = 0; j < cols; j++) {
                row += this.get(new CellPosition(i, j));
            }
            if (i == rows - 1) {
                result += row;
            }
            else{
                result +=row +"\n";
            }
        }
        return result;
    }
}
