package horsequeen.gamelogic;

import horsequeen.ia.Heuristic;

/**
 * Heuristica que valora la cantidad de fichas apilasdas en la reina
 * @author josue
 */
public class QueenStackHeuristic implements Heuristic{

    @Override
    public double h(HorseQueenStatus status, int player) {
        if (player==status.WHITE){
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
            return status.getWhiteQueen().getStack()-status.getBlackQueen().getStack();
        else 
            return status.getBlackQueen().getStack()-status.getWhiteQueen().getStack();
       
    
    }
    
    public String toString(){
        return "QueenStackHeuristic";
    }
    
}
