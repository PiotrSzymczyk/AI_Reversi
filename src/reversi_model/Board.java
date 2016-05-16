/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi_model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import reversi_AI.GameTree;

/**
 *
 * @author Whatever
 */
public class Board {
    static final int SIZE = 8;  
    Disc[][] boardState;
    boolean isWhiteTurn;
    List<Disc> discs;
    
    public Board(){
        isWhiteTurn = true;
        initializeBoard();
    }
    public Board(Board currState, Disc newDisc, boolean isWhiteTurn){
        this.isWhiteTurn = isWhiteTurn;
        boardState = new Disc[SIZE][SIZE];
        discs = new ArrayList<>();
        
        for(Disc d : currState.discs){
            boardState[d.x][d.y] = new Disc(d);
            discs.add(boardState[d.x][d.y]);
        }
        
        addDisc(newDisc);
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
    public boolean addDisc(Disc d){
        if(boardState[d.x][d.y] != null) return false;
        Disc newDisc = new Disc(d.x, d.y, isWhiteTurn);
        isWhiteTurn = !isWhiteTurn;
        boardState[d.x][d.y] = newDisc;
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
                if(isValidCoor(d.x, d.y+1) && boardState[d.x][d.y+1] == null 
                        && checkIfCaptureAvailable(d.x, d.y+1) 
                        && !contains(available, new Disc(d.x, d.y+1,isWhiteTurn))) 
                    available.add(new Disc(d.x, d.y+1,isWhiteTurn)); 
                break;
            case UP_RIGHT:
                if(isValidCoor(d.x+1, d.y+1) && boardState[d.x+1][d.y+1] == null 
                        && checkIfCaptureAvailable(d.x+1, d.y+1)
                        && !contains(available, new Disc(d.x+1, d.y+1,isWhiteTurn))) 
                    available.add(new Disc(d.x+1, d.y+1,isWhiteTurn));
                break;
            case RIGHT:
                if(isValidCoor(d.x+1, d.y) && boardState[d.x+1][d.y] == null &&
                        checkIfCaptureAvailable(d.x+1, d.y) 
                        && !contains(available, new Disc(d.x+1, d.y,isWhiteTurn)))
                    available.add(new Disc(d.x+1, d.y,isWhiteTurn));
                break;
            case BOTTOM_RIGHT:
                if(isValidCoor(d.x+1, d.y-1) && boardState[d.x+1][d.y-1] == null &&
                        checkIfCaptureAvailable(d.x+1, d.y-1) 
                        && !contains(available, new Disc(d.x+1, d.y-1,isWhiteTurn)))
                    available.add(new Disc(d.x+1, d.y-1,isWhiteTurn));
                break;
            case BOTTOM:
                if(isValidCoor(d.x, d.y-1) && boardState[d.x][d.y-1] == null &&
                        checkIfCaptureAvailable(d.x, d.y-1) 
                        && !contains(available, new Disc(d.x, d.y-1,isWhiteTurn)))
                    available.add(new Disc(d.x, d.y-1,isWhiteTurn));
                break;
            case BOTTOM_LEFT:
                if(isValidCoor(d.x-1, d.y-1) && boardState[d.x-1][d.y-1] == null &&
                        checkIfCaptureAvailable(d.x-1, d.y-1) 
                        && !contains(available, new Disc(d.x-1, d.y-1,isWhiteTurn)))
                    available.add(new Disc(d.x-1, d.y-1,isWhiteTurn));
                break;
            case LEFT:
                if(isValidCoor(d.x-1, d.y) && boardState[d.x-1][d.y] == null &&
                        checkIfCaptureAvailable(d.x-1, d.y) 
                        && !contains(available, new Disc(d.x-1, d.y,isWhiteTurn)))
                    available.add(new Disc(d.x-1, d.y,isWhiteTurn));
                break;
            case UP_LEFT:
                if(isValidCoor(d.x-1, d.y+1) && boardState[d.x-1][d.y+1] == null &&
                        checkIfCaptureAvailable(d.x-1, d.y+1) 
                        && !contains(available, new Disc(d.x-1, d.y+1,isWhiteTurn)))
                    available.add(new Disc(d.x-1, d.y+1,isWhiteTurn));
                break;
        }
    }
    public boolean contains(List<Disc> ls, Disc d){
        for(Disc disc : ls){
            if(disc.x == d.x && disc.y == d.y) return true;
        }
        return false;
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
    public double evaluate(boolean isWhiteEvaluated){
        return eavaluateUsingFieldValues(isWhiteEvaluated) 
                + evaluateUsingDiscNumber(isWhiteEvaluated) 
                +evaluateUsingMobility(isWhiteEvaluated);
    }
    public int eavaluateUsingFieldValues(boolean isWhiteEvaluated){
        int whiteScore = 0, blackScore = 0;
        for(Disc d : discs){
            if(d.isWhite ) {
                whiteScore += fieldValues[d.x][d.y];
            } else{
                blackScore += fieldValues[d.x][d.y];
            }
        } 
        return isWhiteEvaluated ? (whiteScore - blackScore) / (whiteScore + blackScore) 
                : (blackScore - whiteScore) / (whiteScore + blackScore);
    }
    public int evaluateUsingDiscNumber(boolean isWhiteEvaluated){
        int whiteCount = 0, blackCount = 0; 
        for(Disc d : discs){
            if(d.isWhite) {
                whiteCount++;
            } else {
                blackCount++;
            }
        } 
        return isWhiteEvaluated ? (whiteCount - blackCount) / (whiteCount + blackCount)
                : (blackCount - whiteCount) / (whiteCount + blackCount);
    }
    public int evaluateUsingMobility(boolean isWhiteEvaluated){
        int whiteMobility, blackMobility;
        if(isWhiteTurn){
            whiteMobility = getPossibleMoves().size();
            isWhiteTurn = ! isWhiteTurn;
            blackMobility = getPossibleMoves().size(); 
            isWhiteTurn = ! isWhiteTurn;
        } else {            
            blackMobility = getPossibleMoves().size(); 
            isWhiteTurn = ! isWhiteTurn;
            whiteMobility = getPossibleMoves().size();
            isWhiteTurn = ! isWhiteTurn;
        }
        return isWhiteEvaluated ? (whiteMobility - blackMobility) / (whiteMobility + blackMobility)
                : (blackMobility - whiteMobility) / (whiteMobility + blackMobility);
    }
    public static int[][] fieldValues = { 
        {200, -8,  8,  6,  6,  8,  -8,  200}, 
        {-8, -24, -4, -3, -3, -4, -24, -8},
        {8,  -4,  7,  4,  4,  7,  -4,  8}, 
        {6,  -3,  4,  0,  0,  4,  -3,  6}, 
        {6,  -3,  4,  0,  0,  4,  -3,  6}, 
        {8,  -4,  7,  4,  4,  7,  -4,  8}, 
        {-8, -24, -4, -3, -3, -4, -24, -8},
        {200, -8,  8,  6,  6,  8,  -8,  200}};
    // Doesn't work :(
    public Disc substract(Board b){        
        List<Disc> tmpDiscs = new ArrayList<>();
        
        for(Disc d : discs){
            tmpDiscs.add(new Disc(d));
        }
        
        Iterator<Disc> it = tmpDiscs.iterator();
            while (it.hasNext()) {
                Disc curr = it.next();
                if(contains(b.discs,curr)) it.remove();
            }
        return tmpDiscs.get(0);
        
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Disc d : discs)
            sb.append(d + " | ");
        sb.append(eavaluateUsingFieldValues(false));
        return sb.toString();
        //return "score: " + eavaluateUsingFieldValues(false);
    }
    
    public boolean equals(Board other){
        if(discs.size() != other.discs.size()) return false;
        for(int i = 0; i < discs.size(); i++ ){
            if(!discs.get(i).equals(other.discs.get(i))) return false;
        }
        return true;
    }
}
