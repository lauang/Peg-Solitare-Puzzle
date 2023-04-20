package no.uib.inf101.sem2.pegSolitare.view;

import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.pegSolitare.model.GameState;
import no.uib.inf101.sem2.pegSolitare.model.pin.Pin;

public interface ViewablePegSolitareModel {
    /**
     * 
     * @return griddimensions
     */
    GridDimension getDimension();

    /**
     * 
     * @return iterable
     */
    Iterable<GridCell<Character>> getTilesOnBoard();

    /**
     * 
     * @return iterable
     */
    Iterable<GridCell<Character>> getBoardShape();

    /**
     * 
     * @return iterable
     */

    Iterable<GridCell<Character>> getPins();


    /**
     * 
     * @return gamestate
     */
    GameState getGameState();
}
