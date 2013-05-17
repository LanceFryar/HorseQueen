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
public class Board {
    
    private Cell cells[][];

    public Board(int rows, int cols) {
        cells = new Cell[rows][cols];
        for (int i=0; i<rows; i++){
            for (int j=0; j<cols; j++){
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
        return cells[position.getCol()][position.getRow()].getPiece();
    }
    
    /**
     * Coloca una pieza en la posicion pasada por parametro
     * @param position
     * @param piece 
     */
    public void setPieceAt(Position position, Piece piece){
        if (piece!=null)
            piece.setPosition(position);
        cells[position.getCol()][position.getRow()].setPiece(piece);
    }
    
    
    
    
}
