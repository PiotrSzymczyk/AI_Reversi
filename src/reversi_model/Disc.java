/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi_model;

/**
 *
 * @author Whatever
 */
public class Disc {
    boolean isWhite;
    int x, y;
    public Disc(int x, int y, boolean isWhite){
        this.x = x;
        this.y = y;
        this.isWhite = isWhite;
    }
    public Disc(){
        this(-1, -1, true);
    }
    public int row(){
        return y;
    }
    public int column(){
        return x;
    }
    public boolean isWhite(){
        return isWhite;
    }
    public void flip(){
        isWhite = !isWhite;
    }
}