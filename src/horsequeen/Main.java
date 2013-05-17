package horsequeen;

import horsequeen.gamelogic.GameStatus;
import horsequeen.gamelogic.Movement;
import horsequeen.util.Position;
import java.util.List;

/**
 *
 * @author josue
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // TODO code application logic here
        GameStatus status = new GameStatus();
        PossibleMovesProove(status);        
    }

    private static void PossibleMovesProove(GameStatus status) {
        List <Movement> posibleMovements = status.getPosibleMovements();
        
        for (Movement movement : posibleMovements){
            System.out.println("x: " + movement.getDestination().getCol()
                    + "      y: " + movement.getDestination().getRow());
        }
    }
}
