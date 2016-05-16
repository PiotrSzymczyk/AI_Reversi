/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import reversi_AI.PlayerAI;
import reversi_model.Board;
import reversi_model.Disc;
import reversi_view.MainWindow;

/**
 *
 * @author Whatever
 */
public class Reversi implements ActionListener {

    Board m;
    MainWindow v;
    PlayerAI zenon;
    String heur = "";
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
                //m=new Board(m,new Disc(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]),m.isWhiteTurn()), m.isWhiteTurn());
                v.updateBoard(m.getDiscs());
                v.updatePlayer(m.isWhiteTurn()); 
                v.updatePossibleMoves(m.getPossibleMoves());
                v.updatePoints(m.getPoints());
                
                if(v.getHeur() != "PvP") moveAI();
                break;
            case 4:
                switch(e.getActionCommand()){
                    case "pass":
                        m.manuallySwitchPlayer();
                        v.switchImage();
                        v.updatePossibleMoves(m.getPossibleMoves());
                        moveAI();
                        break;
                }
        }
    }
    public void moveAI(){
        if(zenon == null) zenon = new PlayerAI(m,m.isWhiteTurn(),v.getSearchDepth(),v.getHeur() );
        else {
            zenon.setDepth(v.getSearchDepth());
            zenon.setHeur(v.getHeur());
            zenon.updatePlayerMove(m);
        }
        
        System.out.print("Moves for Zenon: ");
        for(Disc d : m.getPossibleMoves()){
            System.out.print(d.shortStr() + " | "); 
        }

        Disc tmp = zenon.findMoveMinMax();

        System.out.println("\nZenon says: " + tmp);
        m.addDisc(tmp);
        v.updateBoard(m.getDiscs());
        v.updatePlayer(m.isWhiteTurn()); 
        v.updatePossibleMoves(m.getPossibleMoves());
        v.updatePoints(m.getPoints());
    }
    
}
