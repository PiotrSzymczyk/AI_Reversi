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

    public GameTree(Board b, int depth) {
        root = new Node();
        root.board = b;
        root.children = new ArrayList<Node>();
        leafs = new ArrayList<Node>();
        this.depth = depth;
    }

    public static class Node {
        private Board board;
        private Node parent;
        private ArrayList<Node> children;
    }
    
    public ArrayList<Node> generateChildren(Node n){
        List<Disc> moves = n.board.getPossibleMoves();
        ArrayList<Node> children = new ArrayList<>();
        for(Disc d : moves){
            Node tmp = new Node();
            tmp.board = new Board(n.board, d);
            tmp.parent = n;
            children.add(tmp);
        }
        return children;
    }
    public void buildTree(){
        root.children = generateChildren(root);
        leafs = root.children;
        ArrayList<Node> newLeafs = new ArrayList<>();
        while(depth>0){
            for(Node n : leafs){
                n.children = generateChildren(n);
                for(Node child : n.children) newLeafs.add(child);
            }
            leafs = newLeafs;
            depth--;
        }
    }
    public void addOneLevel(){
        ArrayList<Node> newLeafs = new ArrayList<>();
        for(Node n : leafs){
                n.children = generateChildren(n);
                for(Node child : n.children) newLeafs.add(child);
        }
        
        leafs = newLeafs;
    }
    public void switchRoot(Node n){
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
    
    public boolean contains(Node n){
        if(n.parent == root){
            return true;
        } else if(n.parent != null){
            return contains(n.parent);
        }
        return false;
    }
    
}
