package horsequeen.util;

/**
 * Clase que representa una posicion en el tablero.
 * @author josue
 */
public class Position {
    private int col;
    private int row;

    /**
     * 
     * @param col columna en el tablero
     * @param row fila en el tablero
     */
    public Position(int col, int row) {
        this.col = col;
        this.row = row;
    }

    /**
     * 
     * @return la columna a la que apunta el objeto 
     */
    public int getCol() {
        return col;
    }

    /**
     * 
     * @param col valor que poner al parametro <code>col</code> 
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * 
     * @return la fila a la que apunta el objeto
     */
    public int getRow() {
        return row;
    }

    /**
     * 
     * @param row valor que poner al parametro <code>row</code> 
     */
    public void setRow(int row) {
        this.row = row;
    }
    
    
    
    
}
