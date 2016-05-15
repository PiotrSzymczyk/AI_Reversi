/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi_model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Whatever
 */
public class Board {
    static final int SIZE = 8;  
    Disc[][] boardState;
    boolean isWhiteTurn = true;
    List<Disc> discs;
    
    public Board(){
        initializeBoard();
    }
    public Board(Board currState, Disc newDisc){
        boardState = new Disc[SIZE][SIZE];
        discs = new ArrayList<>();
        
        for(Disc d : currState.discs){
            boardState[d.x][d.y] = new Disc(d);
            discs.add(boardState[d.x][d.y]);
        }
        
        boardState[newDisc.x][newDisc.y] = newDisc;
        discs.add(newDisc);
    }
    

    private void initializeBoard() {
        boardState = new Disc[SIZE][SIZE];
        discs = new ArrayList<>();
        boardState[3][3] = new Disc(3, 3, false);
        boardState[4][4] = new Disc(4, 4, false);
        boardState[3][4] = new Disc(3, 4, true);
        boardState[4][3] = new Disc(4, 3, true);
        
        discs.add(boardState[3][3]);
        discs.add(boardState[4][4]);
        discs.add(boardState[3][4]);
        discs.add(boardState[4][3]);
    }
    public boolean addDisc(int x, int y){
        if(boardState[x][y] != null) return false;
        Disc newDisc = new Disc(x, y, isWhiteTurn);
        isWhiteTurn = !isWhiteTurn;
        boardState[x][y] = newDisc;
        discs.add(newDisc);
        makeCaptures(newDisc);
        return true;
    }
    
    public void makeCaptures(Disc d) {
        Direction[] directions = Direction.values();

        for (Direction direction : directions) {
            // get next piece's index along the direction
            Disc nextDisc = next(d, direction);

            if ( nextDisc != null && nextDisc.isWhite != d.isWhite) { // if the disc is not valid (i.e. out of the board) ignore it

                // find all pieces that should be captured in this direction 
                List<Disc> candidatesToCapture = new ArrayList<>();
                findCaptureCandidates(candidatesToCapture, nextDisc, direction, d.isWhite);

                for (Disc found : candidatesToCapture) {
                    // flip the color (WHITE to BLACK and vice-versa)
                    found.flip(); 
                }
            } 
        }
    }
    
    private void findCaptureCandidates(List<Disc> captured, Disc curr, Direction dir, boolean firstColorIsWhite) {
        Disc next = next(curr, dir);

        if(next == null){
            // found a blank piece or end of the board. Stop the search and clear any captured pieces found so far
            captured.clear();
        } else if (next.isWhite == firstColorIsWhite) {
            captured.add(curr);
            // next piece has the same color with the first one.
            // No need to search further for this direction. All pieces collected in the list
            // between the first one and this one, have opposite color and should be captured.
        } else {
            // this piece has the opposite color of the first
            // this is not the last piece in this direction. 
            // Since it has a flipped color it is a candidate for capturing
            if(curr.isWhite != firstColorIsWhite) captured.add(curr);
            // ask the next piece recursively to also check itself
            findCaptureCandidates(captured, next, dir, firstColorIsWhite);
        } 
    }
    
    public Disc next(Disc d, Direction direction) {
        switch (direction) {
            case UP:
                return isValidCoor(d.x, d.y+1) ? boardState[d.x][d.y+1] : null;
            case UP_RIGHT:
                return isValidCoor(d.x+1, d.y+1)? boardState[d.x+1][d.y+1] : null;
            case RIGHT:
                return isValidCoor(d.x+1, d.y) ? boardState[d.x+1][d.y] : null;
            case BOTTOM_RIGHT:
                return isValidCoor(d.x+1, d.y-1) ? boardState[d.x+1][d.y-1] : null;
            case BOTTOM:
                return isValidCoor(d.x, d.y-1) ? boardState[d.x][d.y-1] : null;
            case BOTTOM_LEFT:
                return isValidCoor(d.x-1, d.y-1) ? boardState[d.x-1][d.y-1] : null;
            case LEFT:
                return isValidCoor(d.x-1, d.y) ? boardState[d.x-1][d.y] : null;
            case UP_LEFT:
                return isValidCoor(d.x-1, d.y+1) ? boardState[d.x-1][d.y+1] : null;
            default:
                return null;
        }
    }
    
    public void addAdjacentIfAvailable(List<Disc> available, Disc d, Direction direction) {
        switch (direction) {
            case UP:
                if(isValidCoor(d.x, d.y+1) && boardState[d.x][d.y+1] == null &&
                        checkIfCaptureAvailable(d.x, d.y+1)) available.add(new Disc(d.x, d.y+1,false)); 
                break;
            case UP_RIGHT:
                if(isValidCoor(d.x+1, d.y+1) && boardState[d.x+1][d.y+1] == null && 
                        checkIfCaptureAvailable(d.x+1, d.y+1)) available.add(new Disc(d.x+1, d.y+1,false));
                break;
            case RIGHT:
                if(isValidCoor(d.x+1, d.y) && boardState[d.x+1][d.y] == null &&
                        checkIfCaptureAvailable(d.x+1, d.y) ) available.add(new Disc(d.x+1, d.y,false));
                break;
            case BOTTOM_RIGHT:
                if(isValidCoor(d.x+1, d.y-1) && boardState[d.x+1][d.y-1] == null &&
                        checkIfCaptureAvailable(d.x+1, d.y-1) ) available.add(new Disc(d.x+1, d.y-1,false));
                break;
            case BOTTOM:
                if(isValidCoor(d.x, d.y-1) && boardState[d.x][d.y-1] == null &&
                        checkIfCaptureAvailable(d.x, d.y-1) ) available.add(new Disc(d.x, d.y-1,false));
                break;
            case BOTTOM_LEFT:
                if(isValidCoor(d.x-1, d.y-1) && boardState[d.x-1][d.y-1] == null &&
                        checkIfCaptureAvailable(d.x-1, d.y-1) ) available.add(new Disc(d.x-1, d.y-1,false));
                break;
            case LEFT:
                if(isValidCoor(d.x-1, d.y) && boardState[d.x-1][d.y] == null &&
                        checkIfCaptureAvailable(d.x-1, d.y) ) available.add(new Disc(d.x-1, d.y,false));
                break;
            case UP_LEFT:
                if(isValidCoor(d.x-1, d.y+1) && boardState[d.x-1][d.y+1] == null &&
                        checkIfCaptureAvailable(d.x-1, d.y+1) ) available.add(new Disc(d.x-1, d.y+1,false));
                break;
        }
    }
    
    public boolean checkIfCaptureAvailable(int x, int y) {
        Direction[] directions = Direction.values();
        Disc d = new Disc(x, y, isWhiteTurn);

        for (Direction direction : directions) {
            // get next piece's index along the direction
            if(checkIfCapturePossibleInDirection(next(d,direction), direction, d.isWhite)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean checkIfCapturePossibleInDirection(Disc curr, Direction dir, boolean firstColorIsWhite) {
        Disc next = curr != null ? next(curr, dir) : null;
        
        if(next == null){
            return false;
        } else if(curr.isWhite == firstColorIsWhite){
            return false;
        } else if (curr.isWhite != firstColorIsWhite && next.isWhite == firstColorIsWhite) {
            return true;
        } else {
            return checkIfCapturePossibleInDirection(next, dir, firstColorIsWhite);
        } 
    }

    
    public static boolean isValidCoor(int x, int y){
        return x >= 0 && x < SIZE&& y >= 0 && y < SIZE;
    }
    
    public static int getSize() {
        return SIZE;
    }

    public List<Disc> getDiscs() {
        return discs;
    }

    public List<Disc> getPossibleMoves() {
        List<Disc> res = new ArrayList<>();
        for(Disc disc : discs){
            // if disc color diffrent from current playing color check adjacent fields
            if( isWhiteTurn != disc.isWhite ){ 
                // look if any capture available for this field
                for(Direction dir : Direction.values()){
                    addAdjacentIfAvailable(res, disc,dir);
                }
            }
        }
        return res;
    }

    public boolean isWhiteTurn() {
        return isWhiteTurn;
    }
    public int[] getPoints(){
        int[] res = new int[2];
        for(Disc disc : discs){
            if(disc.isWhite){
                res[0] += 1;
            } else{
                res[1] += 1;
            }
        }
        return res;
    }
    public boolean manuallySwitchPlayer(){
        isWhiteTurn = ! isWhiteTurn;
        return isWhiteTurn;
    }
}
