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
    
    public double distance(Position position){
        int x1 = this.getCol()*this.getCol();
        int x2 = position.getCol()*position.getCol();
        int y1 = this.getRow()*this.getRow();
        int y2 = position.getRow()*position.getRow();
        double x = Math.sqrt(x2-x1);
        double y = Math.sqrt(y2-y1);
        return x*x + y*y;
    }
    
    
    
    
}
