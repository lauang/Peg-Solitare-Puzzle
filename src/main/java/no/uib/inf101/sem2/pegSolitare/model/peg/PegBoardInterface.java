package no.uib.inf101.sem2.pegSolitare.model.peg;

public interface PegBoardInterface {
    /**
     * @param shape of a pegboard
     * @return a pegboard shape
     */
    PegBoardShape getBoard(char shape);
}
