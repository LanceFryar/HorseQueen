
package horsequeen.iu;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OptionPanel extends JPanel{

    private JButton reset, makeDecision;
    private JComboBox whiteHeuristic, blackHeuristic;
    private JLabel desicionLabel;

    public void setDesicionLabel() {
        desicionLabel=new JLabel("Decidiendo...");
        desicionLabel.setVisible(false);
    }

    public JLabel getDesicionLabel() {
        return desicionLabel;
    }
    
    

    public void setWhiteHeuristicComboBox(JComboBox whiteHeuristic) {
        this.whiteHeuristic = whiteHeuristic;
        this.add(whiteHeuristic);
    }

    public void setBlackHeuristicComboBox(JComboBox blackHeuristic) {
        this.blackHeuristic = blackHeuristic;
        this.add(blackHeuristic);
    }
            
    public OptionPanel(int width, int height) {
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
