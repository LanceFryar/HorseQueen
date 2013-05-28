/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horsequeen.gamelogic;

import horsequeen.ia.Heuristic;
import static horsequeen.gamelogic.HorseQueenStatus.WHITE;

/**
 * Heuristica que valora la cantidad de fichas apilasdas en la reina
 * @author josue
 */
public class QueenStackHeuristic implements Heuristic{

    @Override
    public double h(HorseQueenStatus status, int player) {
         if (player==WHITE){
            if (status.getBlackQueen()==null 
                    || status.getPosibleMovementsFor(status.getBlackQueen().getPosition())==null
                    || status.getBlackQueen().getStack()==1){
                return Double.POSITIVE_INFINITY;
            }
            else if (status.getWhiteQueen()==null 
                    || status.getPosibleMovementsFor(status.getWhiteQueen().getPosition())==null
                    || status.getWhiteQueen().getStack()==1){
                return Double.NEGATIVE_INFINITY;
            }
        }
        else{
            if (status.getBlackQueen()==null 
                    || status.getPosibleMovementsFor(status.getBlackQueen().getPosition())==null
                    || status.getBlackQueen().getStack()==1){
                return Double.NEGATIVE_INFINITY;
            }
            else if (status.getWhiteQueen()==null 
                    || status.getPosibleMovementsFor(status.getWhiteQueen().getPosition())==null
                    || status.getWhiteQueen().getStack()==1){
                return Double.POSITIVE_INFINITY;
            }
        }
        if (player==HorseQueenStatus.WHITE) 
            return status.getWhiteQueen().getStack();
        else 
            return status.getBlackQueen().getStack();
       
    
    }
    
}
