package horsequeen.gamelogic;
import horsequeen.util.Position;

/**
 * Clase abstracta que representa una Pieza en general
 * @author josue
 */
public abstract class Piece implements Cloneable{
    
    protected int color;
    protected Position position;

    /**
     * Constructor de la clase
     * @param color color de la pieza
     */
    public Piece(int color) {
        this.color = color;
    }

    /**
     * Devuelve el color de la pieza
     * @return color de la pieza
     */
    public int getColor() {
        return color;
    }

    /**
     * Devueve el lugar donde se encuentra la pieza. Necesario para algunos
     * calculos en el Board
     * @return Posicion donde se encuentra la pieza
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Establece una posicion a la pieza.
     * @param position posicion donde se encuentra la pieza
     */
    public void setPosition(Position position) {
        this.position = position;
    }
    
    public abstract int getStack();
    
    @Override
    public abstract Piece clone();
    
    
    
}