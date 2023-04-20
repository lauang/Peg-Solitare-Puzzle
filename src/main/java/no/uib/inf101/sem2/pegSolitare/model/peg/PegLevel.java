package no.uib.inf101.sem2.pegSolitare.model.peg;

public class PegLevel implements PegBoardInterface {
    //Random Tetromino factory

    @Override
    public PegBoardShape getBoard() {

        return PegBoardShape.newPegBoardShape('E');
    }

    
}
