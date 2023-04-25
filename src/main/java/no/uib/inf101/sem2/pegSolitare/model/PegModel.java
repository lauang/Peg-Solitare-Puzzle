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

    //Posisjonen på den peggen som var sist selected
    private void lastSelected(CellPosition cp){
        lastPegClicked = cp;
    }

    //Fjerner en peg på en gitt posisjon
    void removePeg(CellPosition cp) {
        this.pins.setPeg(cp, 'o');
    }

    //Sjekker om en peg er selected, unselected eller om den skal bli selected
    public void selectPeg(CellPosition cp) {
        //Sjekker om gitt peg ikke er selected
        if (pins.getPegValue(cp) == 'i' && selectCounter == 0) {
            //Setter gitt peg til å være selected
            pins.setPeg(cp, 'S');
            //Endrer selectCounter til 1
            selectCounter += 1;
            //Oppdaterer lastSelected med denne celle posisjonen
            lastSelected(cp);
            //Sjekker om det er noen lovlige flytt for denne celle posisjonen
            availableMoves(lastPegClicked);
        } 
        //Sjeker om gitt peg som er selected
        else if(pins.getPegValue(cp) =='S' && selectCounter == 1) {
            //Fjerner skyggen
            removeShadows();
            //Setter gitt peg til å være unselected
            pins.setPeg(cp, 'i');
            selectCounter -= 1;

        } 
        //Sjekker om gitt celle posisjon er en skygge
        else if(pins.getPegValue(cp) =='g' && selectCounter == 1) {
            //Fjerner skygger
            removeShadows();
            //Fjerner den peggen som er selected
            removePeg(lastPegClicked);
            //Fjerner den peggen vi hoppet over
            removeJumpedPeg(cp);
            //Setter en peg på gitt celle posisjon
            pins.setPeg(cp, 'i');
            selectCounter -= 1;
            //Sjekker om vi har tapt eller vunnet
            checkIfGameLost();
            checkIfGameWon();
        }
    }

    //Fjerner peggen vi hoppet over
    private void removeJumpedPeg(CellPosition cp) {
        int col = cp.col();
        int row = cp.row();
        int lastrow = lastPegClicked.row();
        int lastCol = lastPegClicked.col();

        //Sjekker hvilken peg vi må fjerne
        if (col < lastCol) removePeg(new CellPosition(row, col + 1));
        if (col > lastCol) removePeg(new CellPosition(row, col - 1));
        if (row < lastrow) removePeg(new CellPosition(row + 1, col));
        if (row > lastrow) removePeg(new CellPosition(row - 1, col));
        
    }

    //Sjekker om man har tapt
    private void checkIfGameLost() {
        GameState newState = GameState.GAME_OVER;
        //Går gjennom alle pins og sjekker om det er flere lovlige trekk igjen
        for (GridCell<Character> gc : pins) {
            if (gc.value() == 'i') {
                if (availableMoves(gc.pos())){
                    newState = GameState.ACTIVE_GAME;
                }
            }
        }
        //Hvis ikke det er flere lovlige trekk, endrer vi gamestate 
        gameState = newState;
    }

    //Sjekker om man har vunnet
    private void checkIfGameWon() {
        int pinCount = 0;
        //Går gjennom pins og sjekker om det kun er en peg igjen
        for (GridCell<Character> gc : pins) {
            if (gc.value() == 'i') {
                pinCount += 1;
            }    
        }
        //Hvis det er en peg igjen, endrer vi gamestate
        if(pinCount == 1) {
           gameState = GameState.GAME_WON;
        } 
    }

    //Sjekker om det er lovlige trekk
    private boolean availableMoves(CellPosition cp) {
        boolean moveIsAvailable = false;
        //Sjekker om det er en peg til venstre og om det er en tom plass til venstre igjen for den peggen
        if (
            pins.getPegValue(new CellPosition(cp.row(), cp.col() - 1)) == 'i'
            && pins.getPegValue(new CellPosition(cp.row(), cp.col() - 2)) == 'o'
        ) {
            //Setter en skygge på lovlig trekk hvis en peg er selected
            if (selectCounter == 1) pins.setPeg(new CellPosition(cp.row(), cp.col() - 2), 'g');
            moveIsAvailable = true;
            }
        //Sjekker om det er en peg til høyre og om det er en tom plass til høyre igjen for den peggen
        if (
            pins.getPegValue(new CellPosition(cp.row(), cp.col() + 1)) == 'i'
            && pins.getPegValue(new CellPosition(cp.row(), cp.col() + 2)) == 'o'
        ) {
            //Setter en skygge på lovlig trekk hvis en peg er selected
            if (selectCounter == 1) pins.setPeg(new CellPosition(cp.row(), cp.col() + 2), 'g');
            moveIsAvailable = true;
            }
        //Sjekker om det er en peg over og om det er en tom plass til over igjen for den peggen
        if (
            pins.getPegValue(new CellPosition(cp.row() - 1, cp.col())) == 'i'
            && pins.getPegValue(new CellPosition(cp.row() - 2, cp.col())) == 'o'
        ) {
            //Setter en skygge på lovlig trekk hvis en peg er selected
            if (selectCounter == 1) pins.setPeg(new CellPosition(cp.row() - 2, cp.col()), 'g');
            moveIsAvailable = true;
            }
        //Sjekker om det er en peg under og om det er en tom plass til under der igjen for den peggen
        if (
            pins.getPegValue(new CellPosition(cp.row() + 1, cp.col())) == 'i'
            && pins.getPegValue(new CellPosition(cp.row() + 2, cp.col())) == 'o'
        ) {
            //Setter en skygge på lovlig trekk hvis en peg er selected
            if (selectCounter == 1) pins.setPeg(new CellPosition(cp.row() + 2, cp.col()), 'g');
            moveIsAvailable = true;
            }
        //Returnerer true hvis det finnes ett lovlig trekk
        return moveIsAvailable; 
    }
    
    //Package private for å kunne teste 
    void removeShadows() {
        for (GridCell<Character> gc : pins) {
            if (gc.value() == 'g') {
                pins.setPeg(gc.pos(), 'o');
            }
        }
    }

    //Setter gamestate
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    //Oppdaterer pegboard
    public void updatePegBoard(PegBoardShape pegBoardShape){
        this.pegBoardShape = pegBoardShape;
        this.pins = new Pin(this.pegBoardShape, this.pegBoard);
        boardValues();
        removePeg(new CellPosition(4, 4));
        this.lastPegClicked = new CellPosition(0, 0);
    }

    
}
