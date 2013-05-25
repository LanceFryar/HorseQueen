package horsequeen.util;

/**
 * Clase que representa una posicion en el tablero.
 * @author josue
 */
public class Position implements Cloneable{
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
    
    /**
     * Calcula la distancia euclidea entre dos posiciones
     * @param position
     * @return la distancia entre la posicion actual y la pasada por parametro
     */
    public double distance(Position position){
        int x1=this.getRow();
        int x2=position.getRow();
        int y1=this.getCol();
        int y2=position.getCol();
        return Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
    }
    
    public Position move (int[] offset){
        return new Position(this.getCol()+offset[0], this.getRow()+offset[1]);
    }
    
    @Override
    public Position clone(){
        return new Position(col, row);
    }  
    
}
