
package horsequeen.iu;

import horsequeen.util.Position;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class TabletopCell extends JPanel{
    private int x,y;
    private Color color;
    private JButton boton;
    private clickListener listener;
    
    public TabletopCell(int x,int y,int width, int height,Color color) {
        this.x=x;
        this.y=y;
        this.color=color;
        this.setBackground(color);
        this.setSize(new Dimension(width,height));
        this.setMaximumSize(new Dimension(width,height));
        this.setMinimumSize(new Dimension(width,height));
        this.setPreferredSize(new Dimension(width,height));
        this.setVisible(true);
    }
    
    public void setBoton(JButton boton){
        this.add(boton);
        this.boton=boton;
        boton.setVisible(false);
        if(boton!=null){
            this.boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        listener.onClick(new Position(getXPos(), getYPos()));
                }
            });
        }
    }
    
    public void AddOnClickListener(clickListener listener){
        this.listener=listener;
    }

    public JButton getBoton() {
        return boton;
    }
    
    
    public void paint(Graphics g){
        super.paint(g);
    }
    
    public int getXPos(){
        return x;
    
    }    
    public int getYPos(){
        return y;
    }
    
    public void setPosibleMovement(Color color){
        this.setBackground(color);
    }
    
    public void unsetPosibleMovement(){
        this.setBackground(color);
    }
    
    public boolean isBotonVisible(){
        return this.boton.isVisible();
    }

}
