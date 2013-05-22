
package horsequeen.iu;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Celda extends JPanel{
    private int x,y;
    private int width,height;
    private Color color;

    public Celda(int x, int y,int width, int height,Color color) {
        this.x=x;
        this.y=y;
        this.width = width;
        this.height = height;
        this.color=color;
    }
    
    public void draw(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, width, height);
        g.setColor(Color.black);
        g.drawRect(x, y, width, height);
    }

}
