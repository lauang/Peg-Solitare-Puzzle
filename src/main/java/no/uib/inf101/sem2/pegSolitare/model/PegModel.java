package no.uib.inf101.sem2.pegSolitare.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.pegSolitare.model.peg.PegBoardShape;
import no.uib.inf101.sem2.pegSolitare.controller.ControllablePegModel;
import no.uib.inf101.sem2.pegSolitare.model.peg.PegBoardInterface;
import no.uib.inf101.sem2.pegSolitare.model.peg.PegLevel;
import no.uib.inf101.sem2.pegSolitare.model.pin.Pin;
import no.uib.inf101.sem2.pegSolitare.view.ViewablePegSolitareModel;

public class PegModel implements ViewablePegSolitareModel, ControllablePegModel {
    PegBoard pegBoard;
    PegBoardShape pegBoardShape;
    PegLevel pegLevel;
    PegBoardInterface pegInterface;
    Pin pins;
    CellData cellData = new CellData(16, 16, 400-(16*2), 400-(16*2));
    int selectCounter = 0;
    CellPosition lastPegClicked;
    GameState gameState;

    public PegModel(PegBoard pegBoard, PegBoardInterface pegInterface){
        this.pegInterface = pegInterface;
        this.pegBoard = pegBoard;
        this.pegBoardShape = pegInterface.getBoard();
        this.pins = new Pin(this.pegBoardShape, this.pegBoard);
        boardValues();
        removePeg(new CellPosition(4, 4));
        this.lastPegClicked = new CellPosition(0, 0);
        this.gameState = GameState.ACTIVE_GAME;
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

    private void boardValues(){
        for (GridCell<Character> gridCell : this.pegBoardShape) {
            if (gridCell.value() == 'B') this.pegBoard.set(gridCell.pos(), gridCell.value());
        }
    }

    @Override
    public CellPosition cellClicked(Position pos){
        PixelToCellPositionConverter pixelCell = new PixelToCellPositionConverter(cellData, pegBoard, 0);
        if (pixelCell.pixelIsOnGrid(pos)) {
            System.out.println(pixelCell.PixelToCellPosition(pos));
            selectPeg(pixelCell.PixelToCellPosition(pos));
        } 
        else {
            System.out.println("Du er homo");
        }

        return pixelCell.PixelToCellPosition(pos);
    }

    /*private boolean isLegalMove(Pin pins) {

    }*/

    public void lastSelected(CellPosition cp){
        lastPegClicked = cp;
    }

    public void movePeg(CellPosition cp) {
        for (GridCell<Character> gc : pins) {
            if (gc.value() == 'g') {
                pins.setPeg(cp, 'i');
            
            }
        }
        removePeg(cp);
    }

    public void removePeg(CellPosition cp) {
        this.pins.setPeg(cp, 'o');
    }

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

        } else if(pins.getPegValue(cp) =='g' && selectCounter == 1) {
            removeShadows();
            removePeg(lastPegClicked);
            removeJumpedPeg(cp);
            pins.setPeg(cp, 'i');
            selectCounter -= 1;

        }
        else {
            System.out.println("Jabbadabbadu");
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
        for (GridCell<Character> gc : pins) {
            if (availableMoves(gc.pos()) == true){
                break;
            }  
        }
        this.gameState = GameState.GAME_OVER;
    }

    private boolean availableMoves(CellPosition cp) {
        boolean moveIsAvailable = false;
        if (
            pins.getPegValue(new CellPosition(cp.row(), cp.col() - 1)) == 'i'
            && pins.getPegValue(new CellPosition(cp.row(), cp.col() - 2)) == 'o'
        ) {
            pins.setPeg(new CellPosition(cp.row(), cp.col() - 2), 'g');
            moveIsAvailable = true;
            }

        if (
            pins.getPegValue(new CellPosition(cp.row(), cp.col() + 1)) == 'i'
            && pins.getPegValue(new CellPosition(cp.row(), cp.col() + 2)) == 'o'
        ) {
            pins.setPeg(new CellPosition(cp.row(), cp.col() + 2), 'g');
            moveIsAvailable = true;
            }
        
        if (
            pins.getPegValue(new CellPosition(cp.row() - 1, cp.col())) == 'i'
            && pins.getPegValue(new CellPosition(cp.row() - 2, cp.col())) == 'o'
        ) {
            pins.setPeg(new CellPosition(cp.row() - 2, cp.col()), 'g');
            moveIsAvailable = true;
            }

        if (
            pins.getPegValue(new CellPosition(cp.row() + 1, cp.col())) == 'i'
            && pins.getPegValue(new CellPosition(cp.row() + 2, cp.col())) == 'o'
        ) {
            pins.setPeg(new CellPosition(cp.row() + 2, cp.col()), 'g');
            moveIsAvailable = true;
            }
        System.out.println(moveIsAvailable);
        return moveIsAvailable;
    }

    private void removeShadows() {
        for (GridCell<Character> gc : pins) {
            if (gc.value() == 'g') {
                pins.setPeg(gc.pos(), 'o');
            }
        }
    }

    @Override
    public GameState getGameState() {
        return this.gameState;
    }


    @Override
    public void clockTick() {
        availableMoves(lastPegClicked);
        System.out.println("This is clockTick");
        checkIfGameLost();
    }
}
