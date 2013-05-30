/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horsequeen.gamelogic;
import horsequeen.util.Position;

/**
 *
 * Esta clase representa un movimiento mediante una posicion fuente y una 
 * posicion destino.
 * @author josue
 */
public class Movement {
    
    private Position source;
    private Position destination;

    public Movement(Position source, Position destination) {
        this.source = source;
        this.destination = destination;
    }

    public Position getSource() {
        return source;
    }

    public Position getDestination() {
        return destination;
    }
    
    @Override
    public String toString(){
        return source.toString() + "----->" + destination.toString();
    }
    
}
