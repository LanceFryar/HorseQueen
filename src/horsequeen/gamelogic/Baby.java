package horsequeen.gamelogic;

/**
 *
 * Esta clase representa un Baby o Monkey
 * @author josue
 */
public class Baby extends Piece{

    /**
     * Constructor de la clase
     * @param color color de la Pieza
     */
    public Baby(int color) {
        super(color);
    }

    /**
     * Debido a que la clase abstracta Piece contiene el metodo getStack,
     * el Baby tambien lo tiene, devolviendo su stack que es 1.
     * @return numero de fichas apiladas
     */
    @Override
    public int getStack() {
        return 1;
    }
    
    @Override
    public Baby clone(){
        Baby baby = new Baby(color);
        baby.position=this.position.clone();
        return baby;
    }
    
    
    
}