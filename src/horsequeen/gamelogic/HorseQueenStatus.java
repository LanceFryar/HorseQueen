package horsequeen.gamelogic;
import horsequeen.util.Position;
import java.util.LinkedList;
import java.util.List;
/**
 * Esta clase representa un estado de juego.
 * @author josue
 * 
 * 
 */
public class HorseQueenStatus implements Cloneable{
    
    public static final int ROWS = 12;
    public static final int COLS = 12;
    public static final int WHITE = 0;
    public static final int BLACK = 1;
    public static final int[][] OFFSETS=
    {{2,1}, {2,-1}, {-2,1}, {-2,-1}, {1,2}, {1,-2}, {-1,2}, {-1,-2}};
    
    private Board board;
    private int actualPlayer;
    private Queen whiteQueen;
    private Queen blackQueen;

    public HorseQueenStatus() {
        board = new Board(ROWS, COLS);
        
        whiteQueen = new Queen(WHITE);
        whiteQueen.setPosition(new Position(COLS-1,(ROWS/2)));
        board.setPieceAt(whiteQueen.getPosition(), whiteQueen);
        
        blackQueen = new Queen(BLACK);
        blackQueen.setPosition(new Position(0, (ROWS/2)-1));
        board.setPieceAt(blackQueen.getPosition(), blackQueen);
        
        actualPlayer=WHITE;
    }

    public int getActualPlayer() {
        return actualPlayer;
    }
    
    /**
     * Metodo para mover una ficha. Es la operacion basica del juego
     * @param movement movimiento a llevar a cabo
     */
    public void move(Movement movement) {

        if (!isCorrectMovement(movement)) {
        } 
        else if (board.getPieceAt(movement.getSource()) instanceof Baby) {
            babyMovement(movement);
            actualPlayer = 1 - actualPlayer;
        } 
        else if (board.getPieceAt(movement.getSource()) instanceof Queen) {
            queenMovement(movement);
            actualPlayer = 1 - actualPlayer;
        }

        

    }
    
    /**
     * Devuelve un valor numerico que representa quien ha ganado
     * @return 
     */
    
    public double getUtility(){
        // -1 si no ha ganado nadie, 0 si ha ganado WHITE o 1 si ha ganado BLACK
        if (whiteQueen==null 
                || getPosibleMovementsFor(whiteQueen.getPosition())==null) 
            return 1;
        if (blackQueen==null
                || getPosibleMovementsFor(blackQueen.getPosition())==null) 
            return 0;
        return -1;
    }
    
    public boolean isTerminal(){
        if (whiteQueen==null || blackQueen==null) return true;
        else return false;
    }
    
    /**
     * Devuelve todos los posibles movimientos a partir de un estado.
     * @return 
     */
    public List<Movement> getPosibleMovements(){
        List posibleMovements = new LinkedList();
        for (int i=0; i<ROWS; i++){
            for (int j=0; j<COLS; j++){
                Position position = new Position(i, j);
                if (board.getPieceAt(position)!= null 
                        && board.getPieceAt(position).getColor()==actualPlayer){
                    posibleMovements.addAll(
                            getPosibleMovementsFor(position));
                }
            }
        }
        return posibleMovements;
    }
    
    public List<Movement> getPosibleMovementsFor(Position position){
        List<Movement> posibleMovements= new LinkedList();
        Movement movement;
        for (int[] offset : OFFSETS){
            movement=new Movement(position, position.move(offset));
            if (isCorrectMovement(movement))
                posibleMovements.add(movement);
        }
        return posibleMovements;
    }

    public Queen getWhiteQueen() {
        return whiteQueen;
    }

    public Queen getBlackQueen() {
        return blackQueen;
    }
    
    
    private void babyMovement(Movement movement) {
        if (board.getPieceAt(movement.getDestination())==null)
            babyPositioningMove(movement);
        else
            babyConquestMove(movement);
    }

    /**
     * Movimiento de posicionamiento de un baby
     * @param movement 
     */
    private void babyPositioningMove(Movement movement) {
        // Comprobar si el destino es valido y mover si es posible
        if (isNearer(movement)) {
            board.setPieceAt(movement.getDestination(),
                    board.getPieceAt(movement.getSource()));
            board.setPieceAt(movement.getSource(), null);
        }

    }

    /**
     * Movimiento de conquista de un baby
     * @param movement 
     */
    private void babyConquestMove(Movement movement) {
        board.setPieceAt(movement.getDestination(), 
                        board.getPieceAt(movement.getSource()));
        board.setPieceAt(movement.getSource(), null);
    }

    /**
     * Moviemiento de una reina.
     * @param movement 
     */
    private void queenMovement(Movement movement) {
        // Movimiento de posicionamiento -> reduce el stack y deja un baby
       if (board.getPieceAt(movement.getDestination())==null) {
           if (actualPlayer==BLACK){
               blackQueen.reduceStack();
           }
           else{
               whiteQueen.reduceStack();
           }
           board.setPieceAt(movement.getDestination(), 
                   board.getPieceAt(movement.getSource()));
           board.setPieceAt(movement.getSource(), new Baby(actualPlayer));
       }
       // Movimiento de conquista -> come y no deja baby
       else {
           board.setPieceAt(movement.getDestination(), 
                   board.getPieceAt(movement.getSource()));
           board.setPieceAt(movement.getSource(), null);
       }
    }

    /**
     * Determina si el movimiento es dentro del tablero
     * @param movement
     * @return 
     */
    private boolean inBoardMove(Movement movement) {
        return movement.getSource().getCol()>=0  
                   && movement.getSource().getCol()<COLS
                   && movement.getDestination().getCol()>=0 
                   && movement.getDestination().getCol()<COLS
                   && movement.getSource().getRow()>=0 
                   && movement.getSource().getRow()<ROWS
                   && movement.getDestination().getRow()>=0 
                   && movement.getDestination().getRow()<ROWS;
    }
    
    /**
     * Determina si es un movimiento correcto (en forma de L).
     * @param movement
     * @return 
     */
    private boolean isLMovement(Movement movement){
        int x = Math.abs(movement.getDestination().getRow() - movement.getSource().getRow());
        int y = Math.abs(movement.getDestination().getCol() - movement.getSource().getCol());
        return (x>0)&&(y>0)&&(x+y==3);
    }

    public Board getBoard() {
        return board;
    }

   

    private boolean isNearer(Movement movement) {
        if (actualPlayer==BLACK)
            return movement.getDestination().distance(whiteQueen.getPosition())<
                   movement.getSource().distance(whiteQueen.getPosition());
        else 
            return movement.getDestination().distance(blackQueen.getPosition())<
                   movement.getSource().distance(blackQueen.getPosition());
    }

    private boolean isCorrectMovement(Movement movement) {
         boolean nearer = true;

        if (board.getPieceAt(movement.getSource()) instanceof Baby) {
            nearer = isNearer(movement);
        }

        return inBoardMove(movement)
                && isLMovement(movement)
                && board.getPieceAt(movement.getSource()) != null
                && board.getPieceAt(movement.getSource()).getColor() == actualPlayer
                && nearer
                && (board.getPieceAt(movement.getDestination()) == null
                || board.getPieceAt(movement.getDestination()).getColor() != actualPlayer);
    }
    
    /*public void showState(){
        for (int i=0; i<COLS; i++){
            System.out.print(" " + i + " ");
        }
        System.out.println();
        for (int i=0; i<ROWS; i++){
            for (int j=0; j<COLS; j++){
               Position position = new Position(i, j);
               if (board.getPieceAt(position) instanceof Baby){
                   System.out.print("[B]");
               }
               if (board.getPieceAt(position) instanceof Queen){
                   System.out.print("Q");
                   if (board.getPieceAt(position).getColor()==BLACK)
                       System.out.print("B" + blackQueen.getStack());
                   else
                       System.out.print("W" + whiteQueen.getStack());
               }
               if (board.getPieceAt(position)== null){
                   System.out.print("[ ]");
               }
            }
            System.out.print(i);
            System.out.println();
        }
    }*/
    
    @Override
    public HorseQueenStatus clone(){
        return new HorseQueenStatus();
    }
    
}
