/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horsequeen.gamelogic;


/**
 * Clase que representa a una reina
 * @author josue
 * @param stack Es la cantidad de piezas apiladas en la reina
 */
public class Queen extends Piece {
    
    private int stack;
    public static final int STACK=7;

    public Queen(int color) {
        super(color);
        stack=STACK;
    }
    
    private Queen (int color, int stack){
        super(color);
        this.stack=stack;
    }

    @Override
    public int getStack() {
        return stack;
    }
    
    /**
     * Reduce en uno la cantidas de piezas apiladas en la reina
     */
    public void reduceStack(){
        stack--;
    }
    
    
    @Override
    public Queen clone(){
        Queen queen = new Queen(color, stack);
        queen.position = this.position.clone();
        return queen;
    }
    
}
