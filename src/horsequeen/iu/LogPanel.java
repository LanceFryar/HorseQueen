package horsequeen.iu;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;

/**
 *
 * @author josue
 */

public class LogPanel extends JPanel{
    private JTextArea logText;

    
    public LogPanel(int width, int height) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setSize(new Dimension(width,height));
        this.setMaximumSize(new Dimension(width,height));
        this.setMinimumSize(new Dimension(width,height));
        this.setPreferredSize(new Dimension(width,height));
        setBackground(Color.white);
    }
    
    public void setLogText() {
        logText = new JTextArea();
        logText.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(logText);
        logScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(logScrollPane);
        

    }
    
    public void writeLog(String text){
        logText.setText(logText.getText()+ text);
    }
    
    public void cleanLog(){
        logText.setText("");
    }
    
    
    
}
