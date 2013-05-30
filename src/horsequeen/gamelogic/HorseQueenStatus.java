package horsequeen.gamelogic;

import horsequeen.util.Position;
import java.util.LinkedList;
import java.util.List;

/**
 * Esta clase representa un estado de juego.
 *
 * @author josue
 *
 *
 */
public class HorseQueenStatus implements Cloneable {

    public static final int ROWS = 12;
    public static final int COLS = 12;
    public static final int WHITE = 0;
    public static final int BLACK = 1;
    public static final int[][] OFFSETS = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};
    private Board board;
    private int actualPlayer;
    private Queen whiteQueen;
    private Queen blackQueen;

    public HorseQueenStatus() {
        board = new Board(COLS, ROWS);

        whiteQueen = new Queen(WHITE);
        whiteQueen.setPosition(new Position(ROWS-1, COLS/2));
        board.setPieceAt(whiteQueen.getPosition(), whiteQueen);

        blackQueen = new Queen(BLACK);
        blackQueen.setPosition(new Position(((ROWS/2)-1), 0));
        board.setPieceAt(blackQueen.getPosition(), blackQueen);

        actualPlayer = WHITE;
    }

    private HorseQueenStatus(Board board) {
        this.board = board;
    }

    public int getActualPlayer() {
        return actualPlayer;
    }

    public Queen getWhiteQueen() {
        return whiteQueen;
    }

    public Queen getBlackQueen() {
        return blackQueen;
    }

    /**
     * Metodo para mover una ficha. Es la operacion basica del juego
     *
     * @param movement movimiento a llevar a cabo
     */
    public void move(Movement movement) {

        if (!isCorrectMovement(movement)) {
        } else if (board.getPieceAt(movement.getSource()) instanceof Baby) {
            babyMovement(movement);
            actualPlayer = 1 - actualPlayer;
        } else if (board.getPieceAt(movement.getSource()) instanceof Queen) {
            queenMovement(movement);
            actualPlayer = 1 - actualPlayer;
        }

        

    }

    
    /**
     * Evalua si el estado es terminal (una reina ha sido comida o solo le queda
     * una ficha en el stack o un jugador no tiene movimientos posibles
     * @return verdadero si es terminal, falso en caso contrario
     */
    
    public boolean isTerminal() {
        if (whiteQueen == null
                || blackQueen == null
                || getPosibleMovements()==null
                || whiteQueen.getStack() == 1
                || blackQueen.getStack() == 1
                || getPosibleMovementsFor(whiteQueen.getPosition()) == null
                || getPosibleMovementsFor(blackQueen.getPosition()) == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Necesario para que la heuristica pueda acceder al tablero
     * @return el tablero del estado actual del juego
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Devuelve todos los posibles movimientos a partir de un estado.
     *
     * @return lista de todos los posibles movimientos
     */
    public List<Movement> getPosibleMovements() {
        List posibleMovements = new LinkedList();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                Position position = new Position(i, j);
                if (board.getPieceAt(position) != null
                        && board.getPieceAt(position).getColor() == actualPlayer) {
                    posibleMovements.addAll(
                            getPosibleMovementsFor(position));
                }
            }
        }
        return posibleMovements;
    }

    /**
     * Devulvel los posibles movimientos para una pieza localizada en un lugar
     * dado
     * @param position posicion de la pieza
     * @return lista de posibles movimientos
     */
    public List<Movement> getPosibleMovementsFor(Position position) {
        List<Movement> posibleMovements = new LinkedList();
        Movement movement;
        for (int[] offset : OFFSETS) {
            movement = new Movement(position, position.move(offset));
            if (isCorrectMovement(movement)) {
                posibleMovements.add(movement);
            }
        }
        return posibleMovements;
    }

    /**
     * Realiza el moviemendo de un Baby, si es posible y legal
     * @param movement movimient deseado
     */
    private void babyMovement(Movement movement) {
        if (board.getPieceAt(movement.getDestination()) == null) {
            babyPositioningMove(movement);
        } else {
            babyConquestMove(movement);
        }
    }

    /**
     * Movimiento de posicionamiento de un baby
     *
     * @param movement movimiemto deseado
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
     *
     * @param movement movimiento deseado
     */
    private void babyConquestMove(Movement movement) {
        if (board.getPieceAt(movement.getDestination()) == whiteQueen) {
            whiteQueen = null;
        } else if (board.getPieceAt(movement.getDestination()) == blackQueen) {
            blackQueen = null;
        }


        board.setPieceAt(movement.getDestination(),
                board.getPieceAt(movement.getSource()));
        board.setPieceAt(movement.getSource(), null);
    }

    /**
     * Moviemiento de una reina.
     *
     * @param movement movimiento deseado
     */
    private void queenMovement(Movement movement) {
        // Movimiento de posicionamiento -> reduce el stack y deja un baby
        if (board.getPieceAt(movement.getDestination()) == null) {
            if (actualPlayer == BLACK) {
                blackQueen.reduceStack();
            } else {
                whiteQueen.reduceStack();
            }
            board.setPieceAt(movement.getDestination(),
                    board.getPieceAt(movement.getSource()));
            board.setPieceAt(movement.getSource(), new Baby(actualPlayer));
        } // Movimiento de conquista -> come y no deja baby
        else {
            if (board.getPieceAt(movement.getDestination()) == whiteQueen) {
                whiteQueen = null;
            } else if (board.getPieceAt(movement.getDestination()) == blackQueen) {
                blackQueen = null;
            }
            board.setPieceAt(movement.getDestination(),
                    board.getPieceAt(movement.getSource()));
            board.setPieceAt(movement.getSource(), null);
        }
    }

    /**
     * Determina si el movimiento es dentro del tablero
     *
     * @param movement
     * @return
     */
    private boolean inBoardMove(Movement movement) {
        return movement.getSource().getCol() >= 0
                && movement.getSource().getCol() < COLS
                && movement.getDestination().getCol() >= 0
                && movement.getDestination().getCol() < COLS
                && movement.getSource().getRow() >= 0
                && movement.getSource().getRow() < ROWS
                && movement.getDestination().getRow() >= 0
                && movement.getDestination().getRow() < ROWS;
    }

    /**
     * Determina si es un movimiento correcto (en forma de L).
     *
     * @param movement
     * @return
     */
    private boolean isLMovement(Movement movement) {
        int x = Math.abs(movement.getDestination().getRow() - movement.getSource().getRow());
        int y = Math.abs(movement.getDestination().getCol() - movement.getSource().getCol());
        return (x > 0) && (y > 0) && (x + y == 3);
    }

    /**
     * Determina si un Baby esta mas cerca de la reina enemiga
     * @param movement movimiento deseado
     * @return verdadero si esta mas cerca de la reina enemiga, falso en caso
     * contrario
     */
    private boolean isNearer(Movement movement) {
        if (actualPlayer == BLACK) {
            return movement.getDestination().distance(whiteQueen.getPosition())
                    < movement.getSource().distance(whiteQueen.getPosition());
        } else {
            return movement.getDestination().distance(blackQueen.getPosition())
                    < movement.getSource().distance(blackQueen.getPosition());
        }
    }

    /**
     * 
     * Determina si un movimiento es correcto
     * @param movement movimiento deseado
     * @return verdadero si el movimiento es correcto, galse en caso contrario
     */
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

    /*public void showState() {
        for (int i = 0; i < COLS; i++) {
            System.out.print(" " + i + " ");
        }
        System.out.println();
        for (int i = 0; i < COLS; i++) {
            for (int j = 0; j < ROWS; j++) {
                Position position = new Position(j, i);
                if (board.getPieceAt(position) instanceof Baby) {
                    System.out.print("[B");
                    if (board.getPieceAt(position).getColor() == BLACK) {
                        System.out.print("B]");
                    } else {
                        System.out.print("W]");
                    }
                }
                if (board.getPieceAt(position) instanceof Queen) {
                    System.out.print("Q");
                    if (board.getPieceAt(position).getColor() == BLACK) {
                        System.out.print("B" + blackQueen.getStack());
                    } else {
                        System.out.print("W" + whiteQueen.getStack());
                    }
                }
                if (board.getPieceAt(position) == null) {
                    System.out.print("[ ]");
                }
            }
            System.out.print(i);
            System.out.println();
        }
    }*/
    public String getWiner(){
        if (whiteQueen==null || whiteQueen.getStack()==1
                || getPosibleMovementsFor(whiteQueen.getPosition()).isEmpty()){
            return "Las negras ";
        }
        else if (blackQueen==null || blackQueen.getStack()==1
                || getPosibleMovementsFor(blackQueen.getPosition()).isEmpty()){
            return "Las blancas ";
        }
        return "Nadie ";
    }

    @Override
    public HorseQueenStatus clone() {
        HorseQueenStatus horseQueenStatus = new HorseQueenStatus(board.clone());
        horseQueenStatus.whiteQueen = whiteQueen.clone();
        horseQueenStatus.blackQueen = blackQueen.clone();
        horseQueenStatus.actualPlayer = actualPlayer;
        return horseQueenStatus;
    }
}
