/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi_AI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import reversi_model.Board;
import reversi_model.Disc;

/**
 *
 * @author Whatever
 */
public class GameTree {
    private Node root;
    private ArrayList<Node> leafs;
    private int depth;
    public String heur = "";

    public GameTree(Board b, int depth) {
        root = new Node();
        root.board = b;
        root.children = new ArrayList<Node>();
        leafs = new ArrayList<Node>();
        this.depth = depth;
    }

    public static class Node {
        private Board board;
        private int score;
        private Node parent;
        private ArrayList<Node> children;
        public Node getParent(){
            return parent;
        }
        public int calculateScore(boolean scoreWhite){
            score = board.eavaluateUsingFieldValues(scoreWhite);
            return score;
        }
        public void setMaxScore(){
            for(Node n : children){
                if(n.score > score) score = n.score;
            }
        }
        public void setMinScore(){
            for(Node n : children){
                if(n.score < score) score = n.score;
            }
        }
        public int getScore(){
            return score;
        }
        public Board getBoard(){
            return board;
        }
        public ArrayList<Node> getChildren(){
            return children;
        }
        public boolean isLeaf(){
            return children == null ? true : children.size() == 0;
        }
        
        public boolean endGame(){
            return board.getDiscs().size() == 64 ? true : false;
        }
        public void setNullScore(){
            score = Integer.MIN_VALUE;
        }
    }
    public Node getRoot(){
        return root;
    }
    public void setHeur(String heur){
        this.heur = heur; 
    }
    public ArrayList<Node> generateChildren(Node n){
        List<Disc> moves = n.board.getPossibleMoves();
        /*for(Disc d : moves){
            System.out.println(d);
        }*/
        ArrayList<Node> children = new ArrayList<>();
        for(Disc d : moves){
            Node tmp = new Node();
            tmp.board = new Board(n.board, d,n.board.isWhiteTurn());
            tmp.children = new ArrayList<Node>();
            tmp.parent = n;
            children.add(tmp);
        }
        return children;
    }
    public void buildTree(){
        int currDepth = depth - 1;
        ArrayList<Node> newLeafs = new ArrayList<>();
        switch(heur){
            case"alpha-beta":
                buildAlphabeta(root, depth,Integer.MIN_VALUE, Integer.MAX_VALUE, true );
                break;
            default:
                root.children = generateChildren(root);
                leafs = root.children;
                currDepth = depth - 1;
                newLeafs = new ArrayList<>();
                while(currDepth>0){
                    Iterator<Node> it = leafs.iterator();
                    while (it.hasNext()) {
                        Node leaf = it.next();
                        leaf.children = generateChildren(leaf);
                        for(Node child : leaf.children) newLeafs.add(child);
                    }
                    leafs = newLeafs;
                    newLeafs = new ArrayList<>();
                    currDepth--;
                }
        }
        //traversePreOrder(root, "");
    }
    public int buildAlphabeta(Node node, int depth, int α, int β, boolean maximizingPlayer){
        List<Disc> moves = node.board.getPossibleMoves();
        
        if (depth == 0 || node.endGame()){
            leafs.add(node);
            return node.calculateScore(!root.getBoard().isWhiteTurn());
        }
        if (maximizingPlayer){
          int v = Integer.MIN_VALUE;
          for (Disc d : moves){
            Node child = new Node();
            child.board = new Board(node.board, d, node.board.isWhiteTurn());
            child.children = new ArrayList<Node>();
            child.parent = node;
            node.children = new ArrayList<>();
            node.children.add(child);
            v = Math.max(v, buildAlphabeta(child, depth - 1, α, β, false));
            α = Math.max(α, v);
            if (β <= α)
                break; //(* β cut-off *)
          }
          node.score= v;
          return v;
        } else {
          int v = Integer.MAX_VALUE;
          for(Disc d : moves){
            Node child = new Node();
            child.board = new Board(node.board, d, node.board.isWhiteTurn());
            child.children = new ArrayList<Node>();
            child.parent = node;
            node.children = new ArrayList<>();
            node.children.add(child);
            v = Math.min(v, buildAlphabeta(child, depth - 1, α, β, true));
            β = Math.min(β, v);
            if (β <= α)
                break; // (* α cut-off *)
          }
          node.score= v;
          return v;
        }
      }
    public void addOneLevel(){
        ArrayList<Node> newLeafs = leafs;
        leafs = new ArrayList<>();
        switch(heur){
            case "alpha-beta":
                for(Node n : newLeafs){
                    buildAlphabeta(n, 1, Integer.MIN_VALUE, Integer.MAX_VALUE, n.board.isWhiteTurn() );
                }
                break;
            default: 
                for(Node n : leafs){
                    n.children = generateChildren(n);
                    for(Node child : n.children) newLeafs.add(child);
                }
        }
        
        leafs = newLeafs;
    }
    public void moveRoot(Node n){
        root = n;
        Iterator<Node> it = leafs.iterator();
        while (it.hasNext()) {
            Node leaf = it.next();
            if(!contains(leaf)) {
                it.remove();
            }
        }
        addOneLevel();
    }
    public void moveRoot(Board b){
        int i = 0;
        while(!root.children.get(i).board.equals(b)) i++;
        root = root.children.get(i);
        Iterator<Node> it = leafs.iterator();
        while (it.hasNext()) {
            Node leaf = it.next();
            if(!contains(leaf)) {
                it.remove();
            }
        }
        addOneLevel();
    }
    
    public boolean contains(Node n){
        if(n.parent == root){
            return true;
        } else if(n.parent != null){
            return contains(n.parent);
        }
        return false;
    }
    
    public int getTreeDepth(){
        return nodeDepth(leafs.get(0));
    }
    
    public int nodeDepth(Node n){
        if(n.parent == null && n == root){
            return 0;
        } else if(n.parent == null){
            return Integer.MIN_VALUE;
        } else return nodeDepth(n.parent) + 1;
    }
     public ArrayList<Node> getLeafs(){
         return leafs;
     }
     
     public void traversePreOrder(Node n, String indent){
         System.out.println(indent + n.board);
         for(Node ch : n.children) traversePreOrder(ch, (indent+"\t"));
     }
}
