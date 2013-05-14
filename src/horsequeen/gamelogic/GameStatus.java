package horsequeen.gamelogic;
import horsequeen.util.Position;

/**
 * Esta clase representa un estado de juego.
 * @author josue
 * 
 * 
 */
public class GameStatus {
    
    public static final int ROWS = 8;
    public static final int COLS = 8;
    public static final int WHITE = 0;
    public static final int BLACK = 1;
    
    private Board board;
    private int actualPlayer;
    private Queen whiteQueen;
    private Queen blackQueen;

    public GameStatus() {
        
    }
    
    public void move (Movement movement){
        // TODO implementar el movimiento
        if (!inBoardMove(movement)) {
            //Movimiento fuera de tablero
        }
        else if (board.getPieceAt(movement.getSource()) instanceof Baby){
            if (board.getPieceAt(movement.getDestination())==null)
                babyPositioningMove(movement);
            else
                babyConquestMove(movement);
        }
        else if (board.getPieceAt(movement.getSource()) instanceof Queen){
            queenMovement(movement);
            
        }
    }
    
    public int getUtility(){
        // TODO calcular y devolver utilidad del estado
        return 0;
    }
    
    public Movement[] getPosibleMovements(){
        //TODO calcular posibles movimentos
        return null;
    }

    private void babyPositioningMove(Movement movement) {
        // Comprobar si el destino es valido y mover si es posible
        if (actualPlayer == BLACK){
            if (movement.getDestination().distance(whiteQueen.getPosition())<
                    movement.getSource().distance(whiteQueen.getPosition())){
                //Movimiento posible
                board.setPieceAt(movement.getDestination(), 
                        board.getPieceAt(movement.getSource()));
                board.setPieceAt(movement.getSource(), null);
            }
        }
        else{
            if (movement.getDestination().distance(blackQueen.getPosition())<
                    movement.getSource().distance(blackQueen.getPosition())){
                //Movimiento posible
                board.setPieceAt(movement.getDestination(), 
                        board.getPieceAt(movement.getSource()));
                board.setPieceAt(movement.getSource(), null);
            }
            
        }
    }

    private void babyConquestMove(Movement movement) {
        board.setPieceAt(movement.getDestination(), 
                        board.getPieceAt(movement.getSource()));
        board.setPieceAt(movement.getSource(), null);
    }

    private void queenMovement(Movement movement) {
        board.setPieceAt(movement.getDestination(), 
                   board.getPieceAt(movement.getSource()));
        board.setPieceAt(movement.getSource(), null);
       
       if (board.getPieceAt(movement.getDestination())==null) {
           if (actualPlayer==BLACK){
               blackQueen.reduceStack();
           }
           else{
               whiteQueen.reduceStack();
           }
       }
    }

    private boolean inBoardMove(Movement movement) {
        return movement.getSource().getCol()>=0 
                   || movement.getSource().getCol()<COLS
                   || movement.getDestination().getCol()>=0 
                   || movement.getDestination().getCol()<COLS
                   || movement.getSource().getRow()>=0 
                   || movement.getSource().getRow()<ROWS
                   || movement.getDestination().getRow()>=0 
                   || movement.getDestination().getRow()<ROWS;
    }
    
    private boolean isCorrectMovement(Movement movement){
        //TODO comprobar si el movimiento es en L.
        return false;
    }
    
    
    
    
    
}
