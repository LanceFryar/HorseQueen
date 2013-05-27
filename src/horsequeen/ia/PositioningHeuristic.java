/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horsequeen.ia;

import horsequeen.gamelogic.HorseQueenStatus;
import static horsequeen.gamelogic.HorseQueenStatus.COLS;
import static horsequeen.gamelogic.HorseQueenStatus.ROWS;
import static horsequeen.gamelogic.HorseQueenStatus.WHITE;
import horsequeen.util.Position;

/**
 *
 * @author josue
 */
public class PositioningHeuristic implements Heuristic{
    
    @Override
    public double h(HorseQueenStatus status){
        double result = 0;
        for (int i=0; i<COLS; i++){
            for (int j=0; j<ROWS; j++){
                if (status.getBoard().getPieceAt(new Position(i, j))!=null
                       && status.getBoard().getPieceAt(new Position(i, j)).getColor()==status.getActualPlayer()){
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
        if (status.getActualPlayer()==WHITE){
            if (status.getBlackQueen()==null 
                    || status.getPosibleMovementsFor(status.getBlackQueen().getPosition())==null
                    || status.getBlackQueen().getStack()==1){
                result=Double.POSITIVE_INFINITY;
            }
            else if (status.getWhiteQueen()==null 
                    || status.getPosibleMovementsFor(status.getWhiteQueen().getPosition())==null
                    || status.getWhiteQueen().getStack()==1){
                result=Double.NEGATIVE_INFINITY;
            }
        }
        else{
            if (status.getBlackQueen()==null 
                    || status.getPosibleMovementsFor(status.getBlackQueen().getPosition())==null
                    || status.getBlackQueen().getStack()==1){
                result=Double.NEGATIVE_INFINITY;
            }
            else if (status.getWhiteQueen()==null 
                    || status.getPosibleMovementsFor(status.getWhiteQueen().getPosition())==null
                    || status.getWhiteQueen().getStack()==1){
                result=Double.POSITIVE_INFINITY;
            }
        }
        return result;
    }
    
}
