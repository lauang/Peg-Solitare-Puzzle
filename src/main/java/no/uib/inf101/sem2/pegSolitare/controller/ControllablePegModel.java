package no.uib.inf101.sem2.pegSolitare.controller;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.pegSolitare.model.GameState;
import no.uib.inf101.sem2.pegSolitare.model.Position;

public interface ControllablePegModel {
    
    CellPosition cellClicked(Position pos);

    void selectPeg(CellPosition cp);

    void removePeg(CellPosition cp);

    void movePeg(CellPosition cp);

    void clockTick();

     /**
     * 
     * @return gamestate
     */
    GameState getGameState();
}
