package horsequeen;

import horsequeen.gamelogic.HorseQueenGame;
import horsequeen.iu.OPanel;
import horsequeen.iu.Tablero;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import javax.swing.JFrame;

/**
 *
 * @author josue
 */
public class Main extends JFrame{
    
    private Tablero tablero;
    private OPanel opciones;
    
    public Main() {
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(801, 601));
        this.setVisible(true);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        tablero= new Tablero(8, 8,600,600,new HorseQueenGame());
        tablero.setVisible(true);
        this.add(tablero);
        
        opciones= new OPanel(199,600);
        opciones.setVisible(true);
        opciones.setBackground(Color.lightGray);
        this.add(opciones);
        pack();
        
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
    }
    
    public static void main (String []args){
        Main main = new Main();
        
    }

}
