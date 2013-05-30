package horsequeen.gamelogic;

/**
 * Esta clase representa una celda del tablero
 * @author josue
 */
public class Cell implements Cloneable{
    
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

    @Override
    public Cell clone(){
        Cell cell = new Cell();
        if (piece!=null)
            cell.setPiece(this.getPiece().clone());
        return cell;
    }
    
    
}
