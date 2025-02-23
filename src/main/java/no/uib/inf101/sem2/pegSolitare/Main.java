package no.uib.inf101.sem2.pegSolitare;

import no.uib.inf101.sem2.pegSolitare.controller.PegController;
import no.uib.inf101.sem2.pegSolitare.model.PegBoard;
import no.uib.inf101.sem2.pegSolitare.model.PegModel;
import no.uib.inf101.sem2.pegSolitare.view.PegSolitareView;

import javax.swing.JFrame;

public class Main {
  public static void main(String[] args) {
    PegBoard pegBoard = new PegBoard(9, 9);
    PegModel pegModel = new PegModel(pegBoard);
    PegSolitareView view = new PegSolitareView(pegModel);

    new PegController(pegModel, view);

    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Peg Solitare");
    frame.setContentPane(view);
    frame.pack();
    frame.setVisible(true);

  }
}
