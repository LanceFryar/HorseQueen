/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horsequeen.ia;

import horsequeen.gamelogic.HorseQueenStatus;
import static horsequeen.gamelogic.HorseQueenStatus.WHITE;

/**
 *
 * @author josue
 */
public class QueenStackHeuristic implements Heuristic{

    @Override
    public double h(HorseQueenStatus status) {
         if (status.getActualPlayer()==WHITE){
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
        if (status.getActualPlayer()==HorseQueenStatus.WHITE) 
            return status.getWhiteQueen().getStack();
        else 
            return status.getBlackQueen().getStack();
       
    
    }
    
}
