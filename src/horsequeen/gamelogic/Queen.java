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

    /**
     * Constructor de la clase. Inicializa tambien el stack
     * @param color color de la pieza
     */
    public Queen(int color) {
        super(color);
        stack=STACK;
    }
    
    /**
     * Constructor usado para clonar una instancia de Queen
     * @param color color de la pieza
     * @param stack stack de la pieza
     */
    private Queen (int color, int stack){
        super(color);
        this.stack=stack;
    }

    /**
     * Devuelve el numero de fichas apiladas en la Reina
     * @return numero de fichas apiladas
     */
    @Override
    public int getStack() {
        return stack;
    }
    
    /**
     * 
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
