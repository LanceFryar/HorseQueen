package horsequeen;

import horsequeen.iu.OPanel;
import horsequeen.iu.Tablero;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author josue
 */
public class Main extends JFrame{
    
    private Tablero tablero;
    private OPanel opciones;
    
    public Main() {
        this.setSize(new Dimension(600, 800));
        this.setVisible(true);
        
        tablero= new Tablero(8, 8,600,600);
        tablero.setVisible(true); 
        this.add(tablero,BorderLayout.PAGE_END);
        
        opciones= new OPanel();
        opciones.setVisible(true);
        opciones.setBackground(Color.lightGray);
        this.add(opciones,BorderLayout.PAGE_START);
        
        pack();
        
    }
    
    public void paint(Graphics g){
        super.paint(g);
    }
    
    public static void main (String []args){
        new Main();
    }
      
}
