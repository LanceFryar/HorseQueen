
package horsequeen.iu;

import horsequeen.ia.Heuristic;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OptionPanel extends JPanel{

    private JButton reset, makeDecision, play;
    private JComboBox whiteHeuristic, blackHeuristic, whiteDepth, blackDepth;
    private JLabel desicionLabel;

    public void setDesicionLabel() {
        desicionLabel=new JLabel("Decidiendo...");
        desicionLabel.setVisible(false);
    }
    
    public Heuristic getWhiteHeuristic(){
        return (Heuristic) whiteHeuristic.getSelectedItem();
    }
    
    public Heuristic getBlackHeuristic(){
        return (Heuristic) blackHeuristic.getSelectedItem();
    }

    public int getWhiteDepth() {
        return (int)whiteDepth.getSelectedItem();
    }

    public void setWhiteDepth(JComboBox whiteDepth) {
        this.whiteDepth = whiteDepth;
        this.add(whiteDepth);
    }

    public int getBlackDepth() {
        return (int) blackDepth.getSelectedItem();
    }

    public void setBlackDepth(JComboBox blackDepth) {
        this.blackDepth = blackDepth;
        this.add(blackDepth);
    }

    
    
    public void setWhiteHeuristicComboBox(JComboBox whiteHeuristic) {
        JPanel whiteHeuristicPanel = new JPanel(new FlowLayout());
        whiteHeuristicPanel.add(new JLabel("White"));
        whiteHeuristicPanel.add(whiteHeuristic);
        this.whiteHeuristic = whiteHeuristic;
        this.add(whiteHeuristicPanel);
    }

    public void setBlackHeuristicComboBox(JComboBox blackHeuristic) {
        JPanel blackHeuristicPanel = new JPanel(new FlowLayout());
        blackHeuristicPanel.add(new JLabel("Black"));
        blackHeuristicPanel.add(blackHeuristic);
        this.blackHeuristic = blackHeuristic;
        this.add(blackHeuristicPanel);
    }
            
    public OptionPanel(int width, int height) {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
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
    
    public void setPlayButton(JButton play) {
        this.play = play;
        this.add(play);
    }

    public void setMakeDecisionButton(JButton makeDecision) {
        this.makeDecision = makeDecision;
        this.add(makeDecision);
    }
    
    
    
    
    
   
}
