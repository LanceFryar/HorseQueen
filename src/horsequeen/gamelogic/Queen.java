/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horsequeen.gamelogic;


/**
 *
 * @author josue
 */
public class Queen extends Piece {
    
    private int stack;
    public static final int STACK=7;

    public Queen(int color) {
        super(color);
        stack=STACK;
    }

    public int getStack() {
        return stack;
    }
    
    public void reduceStack(){
        stack--;
    }
    
    
}
