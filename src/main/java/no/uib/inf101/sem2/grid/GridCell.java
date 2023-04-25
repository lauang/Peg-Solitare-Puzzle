package no.uib.inf101.sem2.grid;
/**
 * @param pos the position of a cell
 * @param value the value within a cell
 */

public record GridCell<E>(CellPosition pos, E value){
}
