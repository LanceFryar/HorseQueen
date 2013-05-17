/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horsequeen.gamelogic;

/**
 * Esta clase representa una celda del tablero
 * @author josue
 */
public class Cell {
    
    private Piece piece;
    
    public Cell() {
    }

    /**
     * Devuelve la pieza en la celda actual
     * @return 
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Coloca una pieza en la celda actual
     * @param piece 
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    
    
}
