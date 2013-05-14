/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horsequeen.gamelogic;
import horsequeen.util.Position;

/**
 *
 * @author josue
 */
public class Board {
    
    private Cell cells[][];

    public Board(int rows, int cols) {
        cells = new Cell[rows][cols];
    }
    
    public Piece getPieceAt (Position position){
        return cells[position.getCol()][position.getRow()].getPiece();
    }
    
    public void setPieceAt(Position position, Piece piece){
        piece.setPosition(position);
        cells[position.getCol()][position.getRow()].setPiece(piece);
    }
    
    
    
    
}
