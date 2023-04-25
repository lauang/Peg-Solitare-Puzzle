package no.uib.inf101.sem2.pegSolitare.model.peg;


public class PegLevel implements PegBoardInterface {
    PegBoardShape pegBoardShape;
    
    @Override
    public PegBoardShape getBoard(char shape) {
        //Returnerer ett pegboardshape utifra hvilken char shape er
        return PegBoardShape.newPegBoardShape(shape);
    }
}
