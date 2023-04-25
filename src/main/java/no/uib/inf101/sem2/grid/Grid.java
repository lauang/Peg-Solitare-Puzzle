package no.uib.inf101.sem2.grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Grid<E> implements IGrid<E>{
    public int rows;
    public int cols;
    //Feltvariabler for å lagre verdiene

    private List<List<E>> grid;
    
    // Konstruktør 1
    public Grid(int rows, int cols){
        this(rows, cols, null);
    }
    
    //Konstruktør 2 for å opprette ett rutenett.
    public Grid(int rows, int cols, E defaultValue) {
        //Lagrer feltvariablene som en int
        this.rows = rows; 
        this.cols = cols;
        //Lagrer grid som en arrayList 
        this.grid = new ArrayList<>();
        for (int x = 0; x < rows; x ++){
            //Lager en rekke
            this.grid.add(x, new ArrayList<>());
            for (int y = 0; y < cols; y++){
                //Lager en kollonne
                grid.get(x).add(y, defaultValue);
            }
        }
        
    }
    

    @Override
    public Iterator<GridCell<E>> iterator() {
        List<GridCell<E>> result = new ArrayList<>();
        for (int i = 0; i < this.rows; i++) {
          for (int j = 0; j < this.cols; j++){
            result.add(new GridCell<E>(new CellPosition(i, j), this.grid.get(i).get(j)));
          }
        }


        return result.iterator();
    }

    @Override
    public void set(CellPosition pos, E value) {
        //Metode for å sette en verdi på en gitt celle-posisjon.
        this.grid.get(pos.row()).set(pos.col(), value);
    }

    @Override
    public E get(CellPosition pos) {
        E e = this.grid.get(pos.row()).get(pos.col());
        return e;
    }

    @Override
    public boolean positionIsOnGrid(CellPosition pos) {
        if (pos.row() <0 ) return false;
        if (pos.col() <0 ) return false;
        if (pos.row() >= this.rows() ) return false;
        if (pos.col() >= this.cols() ) return false;
        
        return true;
    }

    @Override
    public int rows() {
        return this.rows;
    }

    @Override
    public int cols() {
        return this.cols;
    }

}
