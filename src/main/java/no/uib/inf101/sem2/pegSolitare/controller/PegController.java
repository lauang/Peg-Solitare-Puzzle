package no.uib.inf101.sem2.pegSolitare.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

import no.uib.inf101.sem2.pegSolitare.model.GameState;
import no.uib.inf101.sem2.pegSolitare.model.PegModel;
import no.uib.inf101.sem2.pegSolitare.model.Position;
import no.uib.inf101.sem2.pegSolitare.model.peg.PegBoardShape;
import no.uib.inf101.sem2.pegSolitare.model.peg.PegLevel;
import no.uib.inf101.sem2.pegSolitare.view.PegSolitareView;

/**
 * Class represent the controller of the game
 */
public class PegController implements java.awt.event.MouseMotionListener, java.awt.event.MouseListener, java.awt.event.KeyListener{

    private PegModel pegModel;
    private PegSolitareView pegSolitareView;
    boolean mouseIsClicked = false;
    private Timer timer;
    private int xmouse=0, ymouse=0;
    private boolean gameHasStarted = false;

    //Konstruktør
    public PegController(PegModel pegModel, PegSolitareView pegSolitareView){
        this.pegModel = pegModel;
        this.pegSolitareView = pegSolitareView;
        this.pegSolitareView.addMouseListener(this);
        this.timer = new Timer(1000/60, this::clockTick);
        pegSolitareView.addKeyListener(this);
    }

    private void clockTick(ActionEvent actionEvent) {
        if (pegModel.getGameState() == GameState.ACTIVE_GAME){
            pegSolitareView.repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (pegModel.getGameState() == GameState.ACTIVE_GAME) {
            mouseIsClicked = true;
            xmouse = e.getX();
            ymouse = e.getY();
            pegModel.cellClicked(new Position(xmouse, ymouse));
            pegSolitareView.repaint();

            if(gameHasStarted == false) {
                this.timer.start();
            }
            gameHasStarted = true;
        }
        
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
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

    @Override
    public void keyPressed(KeyEvent e) {
        if (pegModel.getGameState() == GameState.GAME_OVER || pegModel.getGameState() == GameState.GAME_WON){

            if(e.getKeyCode() == KeyEvent.VK_R){
                pegModel.setGameState(GameState.GAME_MENU);
            }
        }
        
        if (pegModel.getGameState() == GameState.GAME_MENU){

            PegLevel pegLevel = new PegLevel();
            PegBoardShape pegBoardShape;
            //Level 1 is a winning board
            if(e.getKeyCode() == KeyEvent.VK_1){
                pegBoardShape = pegLevel.getBoard('W');
                pegModel.updatePegBoard(pegBoardShape);
                pegModel.setGameState(GameState.ACTIVE_GAME);
            }
            //Level 2 is a losing board
            if(e.getKeyCode() == KeyEvent.VK_2){
                pegBoardShape = pegLevel.getBoard('T');
                pegModel.updatePegBoard(pegBoardShape);
                pegModel.setGameState(GameState.ACTIVE_GAME);
            }
            if(e.getKeyCode() == KeyEvent.VK_3){
                pegBoardShape = pegLevel.getBoard('F');
                pegModel.updatePegBoard(pegBoardShape);
                pegModel.setGameState(GameState.ACTIVE_GAME);
            }
            if(e.getKeyCode() == KeyEvent.VK_4){
                pegBoardShape = pegLevel.getBoard('O');
                pegModel.updatePegBoard(pegBoardShape);
                pegModel.setGameState(GameState.ACTIVE_GAME);
            }
        }
        pegSolitareView.repaint();
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }
    
    
}
