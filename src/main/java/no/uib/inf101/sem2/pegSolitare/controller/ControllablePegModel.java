package no.uib.inf101.sem2.pegSolitare.controller;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.pegSolitare.model.GameState;
import no.uib.inf101.sem2.pegSolitare.model.Position;

/**
 * Interface for the controller
 */
public interface ControllablePegModel {

    /**
     * 
     * @param pos of the clicked cell
     * @return a cell position for the clicked cell
     */
    CellPosition cellClicked(Position pos);

     /**
     * 
     * @return current gamestate
     */
    GameState getGameState();
}
