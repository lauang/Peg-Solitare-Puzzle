package no.uib.inf101.sem2.pegSolitare.controller;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import javax.swing.Timer;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.pegSolitare.model.GameState;
import no.uib.inf101.sem2.pegSolitare.model.PixelToCellPositionConverter;
import no.uib.inf101.sem2.pegSolitare.model.Position;
import no.uib.inf101.sem2.pegSolitare.view.PegSolitareView;

public class PegController implements java.awt.event.MouseMotionListener, java.awt.event.MouseListener{

    private ControllablePegModel controllablePegModel;
    private PegSolitareView pegSolitareView;
    private boolean mouseIsClicked = false;
    private Timer timer;
    GameState gameState;
    int xmouse=0, ymouse=0;

    public PegController(ControllablePegModel controllablePegModel, PegSolitareView pegSolitareView){
        this.controllablePegModel = controllablePegModel;
        this.pegSolitareView = pegSolitareView;
        this.pegSolitareView.addMouseListener(this);
        this.timer = new Timer(1000/60, this::clockTick);
        this.gameState = GameState.ACTIVE_GAME;
        this.timer.start();
    }

    private void clockTick(ActionEvent actionEvent) {
        if (controllablePegModel.getGameState() == GameState.ACTIVE_GAME){
            controllablePegModel.clockTick();
            pegSolitareView.repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseIsClicked = true;
        xmouse = e.getX();
        ymouse = e.getY();
        controllablePegModel.cellClicked(new Position(xmouse, ymouse));
        pegSolitareView.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
    }
    
}
