
package horsequeen.iu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Tablero extends JPanel{
    
    private Celda [][]paneles;
    private int rows,columns;
    private int width,height;
    private Color color1,color2;

    public Tablero(int rows, int columns, int width, int height) {
        this.rows = rows;
        this.columns = columns;
        this.width = width;
        this.height = height;
        this.setSize(new Dimension(width,height));
        this.setMaximumSize(new Dimension(width,height));
        this.setMinimumSize(new Dimension(width,height));
        this.setPreferredSize(new Dimension(width,height));
        paneles= new Celda[rows][columns];
        color1=Color.blue;
        color2=Color.white;
        int x=0,y=0;
        int dx=x+width/rows,dy=y+height/columns;
        for(int i=0;i<rows;i++){
            x=0;
            for(int j=0;j<columns;j++){
                if((j+i)%2==0) paneles[i][j] = new Celda(x,y,dx,dy,color1);
                else paneles[i][j] = new Celda(x,y,dx,dy,color2);
                x=x+dx;
            }
            y+=dy;
        }
    }
    
    @Override
    public void paint(Graphics g){
        g.setColor(Color.black);
        g.drawRect(0, 0, width,height); 
        for(Celda []row : paneles){
            for(Celda celda :row){
                celda.draw(g);
            }
        }
    }
}
