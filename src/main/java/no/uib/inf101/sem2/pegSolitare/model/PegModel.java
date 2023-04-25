package no.uib.inf101.sem2.pegSolitare.model;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.pegSolitare.model.peg.PegBoardShape;
import no.uib.inf101.sem2.pegSolitare.controller.ControllablePegModel;
import no.uib.inf101.sem2.pegSolitare.model.peg.PegLevel;
import no.uib.inf101.sem2.pegSolitare.model.pin.Pin;
import no.uib.inf101.sem2.pegSolitare.view.ViewablePegSolitareModel;

/**
 * Class represents the model for the game
 */
public class PegModel implements ViewablePegSolitareModel, ControllablePegModel {
    private PegBoard pegBoard;
    private PegBoardShape pegBoardShape;
    private PegLevel pegLevel = new PegLevel();
    Pin pins;
    private CellData cellData = new CellData(16, 16, 400-(16*2), 400-(16*2));
    int selectCounter = 0;
    private CellPosition lastPegClicked;
    private GameState gameState;

    //Konstruktør
    public PegModel(PegBoard pegBoard){
        this.gameState = GameState.GAME_MENU;
        this.pegBoard = pegBoard;
        this.pegBoardShape = pegLevel.getBoard('W');
        this.pins = new Pin(this.pegBoardShape, this.pegBoard);
        boardValues();
        removePeg(new CellPosition(4, 4));
        this.lastPegClicked = new CellPosition(0, 0);
    }


    @Override
    public GridDimension getDimension() {
        return this.pegBoard;
    }

    @Override
    public Iterable<GridCell<Character>> getTilesOnBoard() {
        return this.pegBoard;
    }

    @Override
    public Iterable<GridCell<Character>> getBoardShape() {
        return this.pegBoardShape;
    }

    @Override
    public Iterable<GridCell<Character>> getPins() {
        return this.pins;
    }

    @Override
    public CellPosition cellClicked(Position pos){
        PixelToCellPositionConverter pixelCell = new PixelToCellPositionConverter(cellData, pegBoard, 0);
        if (pixelCell.pixelIsOnGrid(pos)) {
            selectPeg(pixelCell.PixelToCellPosition(pos));
        }
        return pixelCell.PixelToCellPosition(pos);
    }

    @Override
    public GameState getGameState() {
        return this.gameState;
    }

    private void boardValues(){
        for (GridCell<Character> gridCell : this.pegBoardShape) {
            if (gridCell.value() == 'B') this.pegBoard.set(gridCell.pos(), gridCell.value());
        }
    }

    private void lastSelected(CellPosition cp){
        lastPegClicked = cp;
    }

    /**
     * Removes a peg
     * @param cp cellposition
     */
    void removePeg(CellPosition cp) {
        this.pins.setPeg(cp, 'o');
    }

    /**
     * Checks if a peg is in a selected or unselected state, or if it can be selected
     * @param cp cellposition
     */
    public void selectPeg(CellPosition cp) {
        if (pins.getPegValue(cp) == 'i' && selectCounter == 0) {
            pins.setPeg(cp, 'S');
            selectCounter += 1;
            lastSelected(cp);
            availableMoves(lastPegClicked);
        }
        else if(pins.getPegValue(cp) =='S' && selectCounter == 1) {
            removeShadows();
            pins.setPeg(cp, 'i');
            selectCounter -= 1;

        }
        else if(pins.getPegValue(cp) =='g' && selectCounter == 1) {
            removeShadows();
            removePeg(lastPegClicked);
            removeJumpedPeg(cp);
            pins.setPeg(cp, 'i');
            selectCounter -= 1;
            checkIfGameLost();
            checkIfGameWon();
        }
    }

    private void removeJumpedPeg(CellPosition cp) {
        int col = cp.col();
        int row = cp.row();
        int lastrow = lastPegClicked.row();
        int lastCol = lastPegClicked.col();

        if (col < lastCol) removePeg(new CellPosition(row, col + 1));
        if (col > lastCol) removePeg(new CellPosition(row, col - 1));
        if (row < lastrow) removePeg(new CellPosition(row + 1, col));
        if (row > lastrow) removePeg(new CellPosition(row - 1, col));
        
    }

    private void checkIfGameLost() {
        GameState newState = GameState.GAME_OVER;
        for (GridCell<Character> gc : pins) {
            if (gc.value() == 'i') {
                if (availableMoves(gc.pos())){
                    newState = GameState.ACTIVE_GAME;
                }
            }
        }
        gameState = newState;
    }

    private void checkIfGameWon() {
        int pinCount = 0;
        for (GridCell<Character> gc : pins) {
            if (gc.value() == 'i') {
                pinCount += 1;
            }    
        }
        if(pinCount == 1) {
           gameState = GameState.GAME_WON;
        } 
    }

    private boolean availableMoves(CellPosition cp) {
        boolean moveIsAvailable = false;
        if (
            pins.getPegValue(new CellPosition(cp.row(), cp.col() - 1)) == 'i'
            && pins.getPegValue(new CellPosition(cp.row(), cp.col() - 2)) == 'o'
        ) {
            if (selectCounter == 1) pins.setPeg(new CellPosition(cp.row(), cp.col() - 2), 'g');
            moveIsAvailable = true;
            }
        if (
            pins.getPegValue(new CellPosition(cp.row(), cp.col() + 1)) == 'i'
            && pins.getPegValue(new CellPosition(cp.row(), cp.col() + 2)) == 'o'
        ) {
            if (selectCounter == 1) pins.setPeg(new CellPosition(cp.row(), cp.col() + 2), 'g');
            moveIsAvailable = true;
            }
        if (
            pins.getPegValue(new CellPosition(cp.row() - 1, cp.col())) == 'i'
            && pins.getPegValue(new CellPosition(cp.row() - 2, cp.col())) == 'o'
        ) {
            if (selectCounter == 1) pins.setPeg(new CellPosition(cp.row() - 2, cp.col()), 'g');
            moveIsAvailable = true;
            }
        if (
            pins.getPegValue(new CellPosition(cp.row() + 1, cp.col())) == 'i'
            && pins.getPegValue(new CellPosition(cp.row() + 2, cp.col())) == 'o'
        ) {
            if (selectCounter == 1) pins.setPeg(new CellPosition(cp.row() + 2, cp.col()), 'g');
            moveIsAvailable = true;
            }
        return moveIsAvailable; 
    }
    
    /**
     * Package private for å kunne teste, fjerner skygger
     */
    void removeShadows() {
        for (GridCell<Character> gc : pins) {
            if (gc.value() == 'g') {
                pins.setPeg(gc.pos(), 'o');
            }
        }
    }

    /**
     * Sets a gamestate
     * @param gameState
     */
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * Updates the pegboard
     * @param pegBoardShape shape of a pegboard 
     */
    public void updatePegBoard(PegBoardShape pegBoardShape){
        this.pegBoardShape = pegBoardShape;
        this.pins = new Pin(this.pegBoardShape, this.pegBoard);
        boardValues();
        removePeg(new CellPosition(4, 4));
        this.lastPegClicked = new CellPosition(0, 0);
    }

    
}
