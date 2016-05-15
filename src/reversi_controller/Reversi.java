/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import reversi_model.Board;
import reversi_view.MainWindow;

/**
 *
 * @author Whatever
 */
public class Reversi implements ActionListener {

    Board m;
    MainWindow v;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Reversi();
        
    }
    
    public Reversi(){
        m = new Board();
        v = new MainWindow(Board.getSize(), this);
        v.updateBoard(m.getDiscs());
        v.updatePossibleMoves(m.getPossibleMoves());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand().length()){
            case 3:
                String[] pos = e.getActionCommand().split(" ");
                m.addDisc(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]));
                v.updateBoard(m.getDiscs());
                v.updatePlayer(m.isWhiteTurn()); 
                v.updatePossibleMoves(m.getPossibleMoves());
                v.updatePoints(m.getPoints());
                break;
            case 4:
                switch(e.getActionCommand()){
                    case "pass":
                        m.manuallySwitchPlayer();
                        v.switchImage();
                        v.updatePossibleMoves(m.getPossibleMoves());
                        break;
                }
        }
    }
    
}
