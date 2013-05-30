package horsequeen.gamelogic;

import static horsequeen.gamelogic.HorseQueenStatus.WHITE;
import horsequeen.ia.Heuristic;

/**
 * Heuristica que valora el numero posible de movimientos del jugador
 * @author josue
 */
public class PossibleMovementsHeuristic implements Heuristic{

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
        return status.getPosibleMovements().size();
    }
    
    
}