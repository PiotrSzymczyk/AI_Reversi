/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi_AI;

import java.util.HashSet;
import reversi_AI.GameTree.Node;
import reversi_model.Board;
import reversi_model.Disc;

/**
 *
 * @author Whatever
 */
public class PlayerAI {
    GameTree gt;
    boolean isAIWhite;
    int searchDepth;
    
    public PlayerAI(Board b, boolean isAIWhite, int depth, String heur){
        gt = new GameTree(b, depth);
        this.isAIWhite = isAIWhite;
        searchDepth = depth;
        gt.setHeur(heur);
        gt.buildTree();
        
        System.out.println("Nowe drzewo:");
        gt.traversePreOrder(gt.getRoot(), "");
    }
    
    public void updatePlayerMove(Board b){        
        gt = new GameTree(b, searchDepth);
        gt.buildTree();
        System.out.println("Nowe drzewo:");
        gt.traversePreOrder(gt.getRoot(), "");
        
    }
    public void setHeur(String heur){
        gt.setHeur(heur);
    }
    public Disc findMoveMinMax(){
        Disc res;
        int depth = searchDepth;
        HashSet<Node> states = new HashSet<>();
        HashSet<Node> tmpStates = new HashSet<>();
        for(Node leaf : gt.getLeafs()){
            leaf.calculateScore(isAIWhite);
            //System.out.println(""+ leaf.getScore());
            states.add(leaf.getParent());
        }
        
        while(depth>2){
            // AI Player maximizes his score 
            if(depth%2 == 1){
                for(Node s : states){
                    s.setMaxScore();
                    tmpStates.add(s.getParent());
                }
            } else{ // Other player minimizes AI score
                for(Node s : states){
                    s.setMinScore();
                    tmpStates.add(s.getParent());
                }
            }
            states = tmpStates;
            tmpStates = new HashSet<>();
            depth--;           
        }
        
        Node bestMove = new Node();
        bestMove.setNullScore();
        for(Node s : states){
            s.setMaxScore();
            if(s.getScore() >= bestMove.getScore()) bestMove = s;
        }
        res =  bestMove.getBoard().getDiscs().get(bestMove.getBoard().getDiscs().size()-1);
        
        //gt.moveRoot(bestMove);
        return res;
    }
    public Disc findMoveAlphaBeta(){
        return alphaBeta(gt.getRoot(),searchDepth,Integer.MIN_VALUE, Integer.MAX_VALUE, true);
  }
    public Disc alphaBeta(Node node, int depth, int α, int β, boolean maximizingPlayer){
        Node best = new Node();
        best.setNullScore();
        for(Node n  : gt.getRoot().getChildren()){
            if(n.getScore() >= best.getScore()) best = n;
        }
        Disc res =  best.getBoard().getDiscs().get(best.getBoard().getDiscs().size()-1);
        return res;
    }

    public void setDepth(int searchDepth) {
        this.searchDepth = searchDepth;
    }
}
