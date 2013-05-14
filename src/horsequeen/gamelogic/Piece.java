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
public class Piece {
    
    private int color;
    private Position position;

    public Piece(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
    
    
    
}
