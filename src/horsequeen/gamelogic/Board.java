/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horsequeen.gamelogic;
import horsequeen.util.Position;

/**
 * Esta clase representa el tablero de juego
 * @author josue
 */
public class Board implements Cloneable{
    
    private Cell cells[][];

    public Board(int cols, int rows) {
        cells = new Cell[cols][rows];
        for (int i=0; i<cols; i++){
            for (int j=0; j<rows; j++){
                cells[i][j] = new Cell();
            }
        }
    }
    
    /**
     * Devuevle la pieza en la posicion pasada por parametro
     * @param position
     * @return 
     */
    public Piece getPieceAt (Position position){
        return cells[position.getRow()][position.getCol()].getPiece();
    }
    
    /**
     * Coloca una pieza en la posicion pasada por parametro
     * @param position
     * @param piece 
     */
    public void setPieceAt(Position position, Piece piece){
        if (piece!=null)
            piece.setPosition(position);
        cells[position.getRow()][position.getCol()].setPiece(piece);
    }
    
    @Override
    public Board clone(){
        Board board = new Board(HorseQueenStatus.ROWS, HorseQueenStatus.COLS);
        board.cells = new Cell[cells.length][cells[0].length];
        for (int i=0; i<cells.length; i++){
            for (int j=0; j<cells[i].length; j++){
                board.cells[i][j] = this.cells[i][j].clone();
            }
        }
        return board;
    }
    
    
}
