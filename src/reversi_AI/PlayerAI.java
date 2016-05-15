/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversi_AI;

import reversi_model.Board;

/**
 *
 * @author Whatever
 */
public class PlayerAI {
    GameTree gt;
    boolean isAIWhite;
    
    public PlayerAI(Board b, boolean isAIWhite, int depth){
        gt = new GameTree(b, depth);
        this.isAIWhite = isAIWhite;
    }
}
