
package horsequeen.iu;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class OPanel extends JPanel{

    private JButton reset,create;
    private JTextField filas;
    private JLabel label;

    public OPanel() {
        this.setSize(new Dimension(500,100));
        this.setMaximumSize(new Dimension(500,100));
        this.setMinimumSize(new Dimension(500,100));
        this.setPreferredSize(new Dimension(500,100));
        
        reset= new JButton("Reset");
        reset.setVisible(true);
        reset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(reset.getParent(), "Reset Button");
            }
        });
        create = new JButton("Create");
        create.setVisible(true);
        label= new JLabel("Nº Rows");
        label.setVisible(true);
        filas=new JTextField("  ");
        filas.setVisible(true);
        //opciones.add(label);
        //opciones.add(filas);
        this.add(create);
        this.add(reset);
    }
    
    public void paint(Graphics g){
        super.paint(g);
    }
   
}
