/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horsequeen.gamelogic;

import horsequeen.ia.Heuristic;
import static horsequeen.gamelogic.HorseQueenStatus.COLS;
import static horsequeen.gamelogic.HorseQueenStatus.ROWS;
import static horsequeen.gamelogic.HorseQueenStatus.WHITE;
import horsequeen.util.Position;

/**
 * Heuristica que valora el posicionamiento de las fichas valorando mejor las
 * casillas centrales
 * @author josue
 */
public class PositioningHeuristic implements Heuristic{
    
    @Override
    public double h(HorseQueenStatus status, int player){
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
        
        double result = 0;
        for (int i=0; i<ROWS; i++){
            for (int j=0; j<COLS; j++){
                if (status.getBoard().getPieceAt(new Position(i, j))!=null
                       && status.getBoard().getPieceAt(new Position(i, j)).getColor()==player){
                    switch (j){
                        case 4: 
                            result+=3;
                            break;
                        case 5:
                            result+=2;
                            break;
                        case 6:
                            result+=1;
                            break;
                        case 7:
                            result+=0;
                            break;
                        default: result+=i;
                    }
                }
            }
        }
        return result;
    }
    
}
