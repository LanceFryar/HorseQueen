
package horsequeen.iu;

import horsequeen.gamelogic.HorseQueenGame;
import horsequeen.gamelogic.Movement;
import horsequeen.util.Position;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Tablero extends JPanel implements clickListener{
    
    private Celda [][]paneles;
    private int rows,columns;
    private int width,height;
    private Color color1,color2;
    private HorseQueenGame game;
    private Position lastMoved;
    private boolean movement;

    public Tablero(int rows, int columns, int width, int height, HorseQueenGame game) {
        this.movement=false;
        this.rows = rows;
        this.columns = columns;
        this.width = width;
        this.height = height;
        this.game=game;
        this.setSize(new Dimension(width,height));
        this.setMaximumSize(new Dimension(width,height));
        this.setMinimumSize(new Dimension(width,height));
        this.setPreferredSize(new Dimension(width,height));
        paneles= new Celda[rows][columns];
        color1=Color.blue;
        color2=Color.white;
        this.setLayout(new GridLayout(rows, columns));
        int dx=width/rows,dy=height/columns;
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                if((j+i)%2==0) paneles[i][j] = new Celda(i,j,dx,dy,color1);
                else paneles[i][j] = new Celda(i,j,dx,dy,color2);
                this.add(paneles[i][j]);
                paneles[i][j].AddOnClickListener(this);
                paneles[i][j].setBoton(new JButton("WQ 8"));
            }
        }
        paneles[0][columns/2].setBotonVisible(true);
        paneles[rows-1][columns/2].setBotonVisible(true);
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.black);
    }

    @Override
    public void onClick(Position pos) {
        if(!isMoving()){
            movement=true;
            List<Movement> moves =this.game.getActualStatus().getPosibleMovementsFor(pos);
            for(Movement move : moves){
                lastMoved=pos;
                paneles[move.getDestination().getRow()][move.getDestination().getCol()].setPosibleMovement(Color.red);
                paneles[move.getDestination().getRow()][move.getDestination().getCol()].setBotonVisible(true);
                this.repaint();
            }
        }else{
            movement=false;
            List<Movement> moves =this.game.getActualStatus().getPosibleMovementsFor(lastMoved);
            game.getActualStatus().move(new Movement(lastMoved, pos));
            paneles[pos.getRow()][pos.getCol()].unsetPosibleMovement();
            for(Movement move : moves){
                if(!pos.equals(move.getDestination())){
                    paneles[move.getDestination().getRow()][move.getDestination().getCol()].unsetPosibleMovement();
                    paneles[move.getDestination().getRow()][move.getDestination().getCol()].setBotonVisible(false);
                    this.repaint();
                }
            }
        }
    }

    private boolean isMoving() {
        return movement;
    }
}
