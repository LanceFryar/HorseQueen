
package horsequeen.iu;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JPanel;

public class OPanel extends JPanel{

    private JButton reset, makeDecision;
            
    public OPanel(int width, int height) {
        this.setSize(new Dimension(width,height));
        this.setMaximumSize(new Dimension(width,height));
        this.setMinimumSize(new Dimension(width,height));
        this.setPreferredSize(new Dimension(width,height));
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
    }

    public void setResetButton(JButton reset) {
        this.reset = reset;
        this.add(reset);
    }

    public void setMakeDecisionButton(JButton makeDecision) {
        this.makeDecision = makeDecision;
        this.add(makeDecision);
    }
    
    
    
    
    
   
}
