/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horsequeen.gamelogic;

/**
 *
 * @author josue
 */
public class Baby extends Piece{

    public Baby(int color) {
        super(color);
    }

    @Override
    public int getStack() {
        return 1;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Baby clone(){
        Baby baby = new Baby(color);
        baby.position=this.position.clone();
        return baby;
    }
    
    
    
}
